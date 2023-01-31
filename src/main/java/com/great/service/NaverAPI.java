package com.great.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Service("NaverAPI")
public class NaverAPI {
    public String getAccessToken (String code, String state) throws UnsupportedEncodingException {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "";//애플리케이션 클라이언트 시크릿값";
        String redirectURI = URLEncoder.encode("", "UTF-8");
        String apiURL;
        apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
        apiURL += "client_id=" + clientId;
        apiURL += "&client_secret=" + clientSecret;
        apiURL += "&redirect_uri=" + redirectURI;
        apiURL += "&code=" + code;
        apiURL += "&state=" + state;
        String access_token = "";
        String refresh_token = "";
        try {
          URL url = new URL(apiURL);
          HttpURLConnection con = (HttpURLConnection)url.openConnection();
          con.setRequestMethod("GET");
          int responseCode = con.getResponseCode();
          BufferedReader br;
          if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
          } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
          }
          String inputLine;
          StringBuffer res = new StringBuffer();
          while ((inputLine = br.readLine()) != null) {
            res.append(inputLine);
          }
          br.close();
          
          String result = res.toString();
          if(responseCode==200) { 
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            String access_Token = element.getAsJsonObject().get("access_token").getAsString();
            String refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            
            return access_Token;
          }
        } catch (Exception e) {
          System.out.println(e);
        }
        return null;
    }
    
    public HashMap<String, String> naverProfile(String token) {
    	HashMap<String, String> userInfo = new HashMap<>();
    	String header = "Bearer " + token; // Bearer 다음에 공백 추가 
    	try {
    		String apiURL = "https://openapi.naver.com/v1/nid/me";
    		URL url = new URL(apiURL);
    		HttpURLConnection con = (HttpURLConnection)url.openConnection(); 
    		con.setRequestMethod("GET"); 
    		con.setRequestProperty("Authorization", header);
    		int responseCode = con.getResponseCode(); 
    		BufferedReader br; 
    		if(responseCode==200) {// 정상 호출 
    			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
    		} else { // 에러 발생 
    			br = new BufferedReader(new InputStreamReader(con.getErrorStream())); 
    		} String inputLine; 
    		StringBuffer response = new StringBuffer();
    		while ((inputLine = br.readLine()) != null) 
    		{ 
    			response.append(inputLine);
    			
    		} 
    		br.close();
    		String result = response.toString();
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            JsonObject re = element.getAsJsonObject().get("response").getAsJsonObject();
            String nickname = re.getAsJsonObject().get("name").getAsString();
            String id = re.getAsJsonObject().get("id").getAsString();
    		userInfo.put("nickname", nickname);
    		userInfo.put("id", id);
    		} catch (Exception e) { 
    			System.out.println(e); 
    		} 
    		return userInfo;
    	}
}
