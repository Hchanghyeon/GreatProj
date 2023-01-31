package com.great.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.great.domain.KakaoVO;
import com.great.domain.LimitFoodListVO;
 
@Service("KakaoAPI")
public class KakaoAPI {
    
	 public KakaoVO getAccessToken (String authorize_code) {
	        String access_Token = "";
	        String refresh_Token = "";
	        String reqURL = "https://kauth.kakao.com/oauth/token";
	        KakaoVO kakao = new KakaoVO();
	        
	        try {
	            URL url = new URL(reqURL);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            
	            //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
	            conn.setRequestMethod("POST");
	            conn.setDoOutput(true);
	            
	            //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
	            StringBuilder sb = new StringBuilder();
	            sb.append("grant_type=authorization_code");
	            sb.append("&client_id=");
	            sb.append("&redirect_uri=");
	            sb.append("&code=" + authorize_code);
	            bw.write(sb.toString());
	            bw.flush();
	            
	            //    결과 코드가 200이라면 성공
	            int responseCode = conn.getResponseCode();
	            System.out.println("responseCode : " + responseCode);
	 
	            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String line = "";
	            String result = "";
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println("response body : " + result);
	            
	            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
	            JsonParser parser = new JsonParser();
	            JsonElement element = parser.parse(result);
	            
	            access_Token = element.getAsJsonObject().get("access_token").getAsString();
	            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
	           
	            
	            kakao.setAccess_token(access_Token);
	            kakao.setRefresh_token(refresh_Token);
	            
	            System.out.println("access_token : " + access_Token);
	            System.out.println("refresh_token : " + refresh_Token);
	            
	            br.close();
	            bw.close();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	        
	        return kakao;
	    }

    
    public HashMap<String, String> getUserInfo (String access_Token) {
        
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, String> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            String id =  element.getAsJsonObject().get("id").getAsString();
            
            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
  
            userInfo.put("nickname", nickname);
            userInfo.put("id", id);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return userInfo;
    }
    
    public void kakaoLogout(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v1/user/logout";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String result = "";
            String line = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
   public void getfriend (String access_Token) {
        
        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        String reqURL = "https://kapi.kakao.com/v1/api/talk/friends";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            
            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
  
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            String line = "";
            String result = "";
            
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
        
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
   
public void sendmsg (String access_Token, String username, List<LimitFoodListVO> vo) {
       
       //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
       String reqURL = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";
       try {
           URL url = new URL(reqURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("POST");
           conn.setDoOutput(true);
           //    요청에 필요한 Header에 포함될 내용
           conn.setRequestProperty("Authorization", "Bearer " + access_Token);
           
           
           //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
           StringBuilder sb = new StringBuilder();
           
           JsonObject json = new JsonObject();
           
           String receiver = "";
           if(username.equals("강우진")) {
        	  receiver = "receiver_uuids=[\"]";
           } else {
        	   receiver = "receiver_uuids=[\"]";
           }
           if(vo.size() >= 2) {
        	   json.addProperty("object_type", "list");
               json.addProperty("header_title", username + "님의 유통기한 알림");
               JsonObject link = new JsonObject();
               link.addProperty("web_url", "");
               link.addProperty("mobile_web_url", "");
               link.addProperty("android_execution_params", "main");
               link.addProperty("ios_execution_params","main");
               json.add("header_link", link);
               JsonArray content = new JsonArray();
               for(int i = 0; i < vo.size(); i++) {
            	   JsonObject contentin = new JsonObject();
            	   int answer = (Integer.parseInt(vo.get(i).getDate()));
            	   String answer2 = "";
            	   if(answer < 0) {
            		   answer2 = Integer.toString(Math.abs(answer)) + "일 지남";
            	   } else {
            		   answer2 = Integer.toString(answer) + "일 남음";
            	   }
            	   
            	   contentin.addProperty("title", vo.get(i).getCls());
            	   contentin.addProperty("description", answer2);
            	   contentin.addProperty("image_url", "");
            	   contentin.addProperty("image_width", 640);
            	   contentin.addProperty("image_height", 640);
            	   JsonObject link2 = new JsonObject();
                   link2.addProperty("web_url", "");
                   link2.addProperty("mobile_web_url", "");
                   link2.addProperty("android_execution_params", "main");
                   link2.addProperty("ios_execution_params","main");
                   contentin.add("link", link2);
            	   content.add(contentin);
               }
               json.add("contents", content);
               JsonArray button = new JsonArray();
               JsonObject button1 = new JsonObject();
               button1.addProperty("title","사이트에서 확인");
               JsonObject url1 = new JsonObject();
               url1.addProperty("web_url", "");
               url1.addProperty("mobile_web_url", "");
               button1.add("link", url1);
               
               JsonObject button2 = new JsonObject();
               button2.addProperty("title","앱에서 확인");
               JsonObject url2 = new JsonObject();
               url2.addProperty("android_execution_params", "main");
               url2.addProperty("ios_execution_params","main");
               button2.add("link", url2);
               
               button.add(button1);
               button.add(button2);
               
               json.add("buttons", button);
           } else {
        	   int answer = (Integer.parseInt(vo.get(0).getDate()));
        	   String answer2 = "";
        	   if(answer < 0) {
        		   answer2 = Integer.toString(answer) + "일 지났습니다";
        	   } else {
        		   answer2 = Integer.toString(answer) + "일 남았습니다";
        	   }
               json.addProperty("object_type", "text");
               json.addProperty("text",username + "님 가족 냉장고 유통기한 알림입니다. " + vo.get(0).getCls() + "가 " + answer2);
               
               JsonObject link = new JsonObject();
               link.addProperty("web_url", "");
               link.addProperty("mobile_web_url", "");
               link.addProperty("button_title", "사이트에서 확인하기");
               json.add("link", link.getAsJsonObject());
           }
           
           
           sb.append(receiver);
           sb.append("&template_object=" + json);
         
           bw.write(sb.toString());
           System.out.println(sb.toString());
           bw.flush();
 
           int responseCode = conn.getResponseCode();
           System.out.println("responseCode : " + responseCode);
           
           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           
           String line = "";
           String result = "";
           
           while ((line = br.readLine()) != null) {
               result += line;
           }
           System.out.println("response body : " + result);
       
           
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }

   }


public void sendmsgallergy (String access_Token, String text) {
    
    //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
    String reqURL = "https://kapi.kakao.com/v1/api/talk/friends/message/default/send";
    try {
        URL url = new URL(reqURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //    요청에 필요한 Header에 포함될 내용
        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
        
        
        //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        StringBuilder sb = new StringBuilder();
        
        JsonObject json = new JsonObject();
        
        String textmsg = "테스트입니다";
        String receiver = "";

     	   receiver = "receiver_uuids=[\"]";
     
            json.addProperty("object_type", "text");
            json.addProperty("text",text);
            
            JsonObject link = new JsonObject();
            link.addProperty("web_url", "");
            link.addProperty("mobile_web_url", "");
            link.addProperty("button_title", "사이트에서 확인하기");
            json.add("link", link.getAsJsonObject());
        
   
        
        sb.append(receiver);
        sb.append("&template_object=" + json);
      
        bw.write(sb.toString());
        System.out.println(sb.toString());
        bw.flush();

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        
        String line = "";
        String result = "";
        
        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("response body : " + result);
    
        
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

}
   public KakaoVO refreshToken (String refresh) {

       String reqURL = "https://kauth.kakao.com/oauth/token";
       KakaoVO kakao = new KakaoVO();
       
       try {
           URL url = new URL(reqURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           
           //    POST 요청을 위해 기본값이 false인 setDoOutput을 true로
           conn.setRequestMethod("POST");
           conn.setDoOutput(true);
           
           //    POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
           StringBuilder sb = new StringBuilder();
           sb.append("grant_type=refresh_token");
           sb.append("&client_id=");
           sb.append("&refresh_token="+refresh);
   
           bw.write(sb.toString());
           bw.flush();
          
           //    결과 코드가 200이라면 성공
           int responseCode = conn.getResponseCode();
           System.out.println("responseCode : " + responseCode);

           //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           String line = "";
           String result = "";
           
           while ((line = br.readLine()) != null) {
               result += line;
           }
           System.out.println("response body : " + result);
         
           //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
           JsonParser parser = new JsonParser();
           JsonElement element = parser.parse(result);
           
           String access_Token = element.getAsJsonObject().get("access_token").getAsString();
          
           
           kakao.setAccess_token(access_Token);
           
           System.out.println("access_token : " + access_Token);
           br.close();
           bw.close();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } 
       return kakao;

   }

}
