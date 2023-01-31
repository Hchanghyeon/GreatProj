package com.great.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.util.Base64Utils;

import com.google.gson.Gson;
import com.great.domain.AllergyVO;
import com.great.domain.FamilyVO;
import com.great.domain.FoodInfoVO;
import com.great.domain.FoodListVO;
import com.great.domain.LimitFoodListVO;
import com.great.domain.MileageVO;
import com.great.domain.ProfileVO;
import com.great.domain.RecipeVO;
import com.great.domain.SearchVO;
import com.great.service.FoodListService;
import com.great.service.KakaoAPI;


@Controller
public class FoodListController {
	
    @Autowired
    private FoodListService foodlistService;
    @Autowired
    ServletContext servletContext;
    @Autowired
    KakaoAPI kakaoapi;
    
    Map<String,String> map = new HashMap<String, String>();
    {
	    map.put("Pumpkin", "호박");
	    map.put("Carrot", "당근");
	    map.put("Pepper", "고추");
	    map.put("Eggplant", "가지");
	    map.put("Onion", "양파");
	    map.put("Welsh Onion", "대파");
	    map.put("Bell Pepper", "피망");
	    map.put("Radish", "무");
	    map.put("Broccoli", "브로콜리");
	    map.put("Cabbage", "배추");
	    map.put("Cucumber", "오이");
	    map.put("Potato", "감자");
	    map.put("Apple", "사과");
	    map.put("Pear", "배");
	    map.put("Mandarin", "귤");
	    map.put("Banana", "바나나");
	    map.put("PineApple", "파인애플");
	    map.put("Lemon", "레몬");
	    map.put("Persimmon", "감");
	    map.put("Mango", "망고");
	    map.put("Peach", "복숭아");
	    map.put("Liquor", "주류");
    }
    
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }

    @GetMapping("/insideView")
    public String getinsideView(Model model, HttpServletRequest request) {
    	// 세션에 저장된 유저 정보 가져오기
    	HttpSession session = request.getSession();
    	FamilyVO user = (FamilyVO)session.getAttribute("user");
    	
    	try {
			Jsoup.connect("").get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	String detect = null;
    	// Flask /predict (Yolo 인식 데이터)
    	try {
			detect = (Jsoup.connect("").ignoreContentType(true).get()).select("body").text().toString();
			//System.out.println(detect);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	JSONParser parser = new JSONParser();
    	JSONArray ja = null;
    	try {
    		ja = (JSONArray)parser.parse(detect);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List<FoodListVO> detectList = new ArrayList<FoodListVO>();
    	for (int i = 0; i < ja.size(); i++) {
    		FoodListVO vo = new FoodListVO();
    		vo.setId("null");
    		vo.setLTX(Float.parseFloat(((JSONObject)ja.get(i)).get("LTX").toString()));
    		vo.setLTY(Float.parseFloat(((JSONObject)ja.get(i)).get("LTY").toString()));
    		vo.setRDX(Float.parseFloat(((JSONObject)ja.get(i)).get("RDX").toString()));
    		vo.setRDY(Float.parseFloat(((JSONObject)ja.get(i)).get("RDY").toString()));
    		vo.setX(Integer.parseInt(((JSONObject)ja.get(i)).get("X").toString()));
    		vo.setY(Integer.parseInt(((JSONObject)ja.get(i)).get("Y").toString()));
    		//vo.setCls(((JSONObject)ja.get(i)).get("cls").toString());
    		vo.setCls(map.get(((JSONObject)ja.get(i)).get("cls")));
    		vo.setImg("null");
    		detectList.add(vo);
		}
    	
    	//System.out.println(user);
    	//System.out.println(detectList);
    	List<ProfileVO> allergyinfo = foodlistService.getAllergyList(user.getId().toString());
    	ArrayList<String> allergyList = new ArrayList<String>();
    	
    	for (ProfileVO vo : allergyinfo) {
			String[] strList = vo.getAllergyinfo().split(":");
			for (String str : strList) {
				allergyList.add(str);
			}
		}
    	
    	//System.out.println(allergyList);
    	
    	model.addAttribute("detectList", detectList);
    	model.addAttribute("allergyList", allergyList);
    	
        return "insideView";
    }
    
    
	@PostMapping("/foodlist/search")
	@ResponseBody
	public Object getRecipe(@RequestParam(value="text", defaultValue="") String text, HttpSession session) throws ParseException, java.text.ParseException {
		FamilyVO family = (FamilyVO)session.getAttribute("user");
		List<FoodListVO> foodlist = new ArrayList<FoodListVO>();
		List<LimitFoodListVO> newFoodList = new ArrayList<LimitFoodListVO>();

		
		Gson gson = new Gson();
		JSONParser jsonParser = new JSONParser();
		Object jarry = new JSONArray();

		
		if(text.equals("")) {
			foodlist = foodlistService.getFoodList(family);
			
	    	// 오늘 날짜 생성
	    	Date today = new Date();
	    	// 날짜 포멧 정하기
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
	    	String date1 = format.format(today);
	    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
	    	Date FirstDate = format.parse(date1);
	    	
	        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
	    	for(FoodListVO food: foodlist) {
	    		
	    		LimitFoodListVO newfood = new LimitFoodListVO();
	    		String date2 = food.getEndDate();
	    		Date SecondDate = format.parse(date2);
	    		
	            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
	    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	            // 나온 결과 값에서 일 수로 전환 하기
	    		long calDateDays = calDate / ( 24*60*60*1000); 
	    		newfood.setNum(food.getNum());
	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
	    		newfood.setCls(food.getCls());
	    		newfood.setDate(Long.toString(calDateDays));
	    		newfood.setEndDate(food.getEndDate());
	    		newfood.setId(food.getId());
	    		newfood.setImg(food.getImg());
	    		newfood.setInDate(food.getInDate());
	    		newfood.setLTX(food.getLTX());
	    		newfood.setLTY(food.getLTY());
	    		newfood.setRDX(food.getRDX());
	    		newfood.setRDY(food.getRDY());
	    		newfood.setX(food.getX());
	    		newfood.setY(food.getY());
	    		newFoodList.add(newfood);
	    	}
			
		} else if(text.equals("due")) {
			foodlist = foodlistService.getFoodList(family);
			
			// 오늘 날짜 생성
	    	Date today = new Date();
	    	// 날짜 포멧 정하기
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
	    	String date1 = format.format(today);
	    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
	    	Date FirstDate = format.parse(date1);
	    	
	        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
	    	for(FoodListVO food: foodlist) {
	    		
	    		LimitFoodListVO newfood = new LimitFoodListVO();
	    		String date2 = food.getEndDate();
	    		Date SecondDate = format.parse(date2);
	    		
	            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
	    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	            // 나온 결과 값에서 일 수로 전환 하기
	    		long calDateDays = calDate / ( 24*60*60*1000); 
	    		// 만약 3일 이내로 남았을 경우
	    		if(calDateDays >= -3) {
	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
		    		newfood.setNum(food.getNum());
	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
	    		newfood.setCls(food.getCls());
	    		newfood.setDate(Long.toString(calDateDays));
	    		newfood.setEndDate(food.getEndDate());
	    		newfood.setId(food.getId());
	    		newfood.setImg(food.getImg());
	    		newfood.setInDate(food.getInDate());
	    		newfood.setLTX(food.getLTX());
	    		newfood.setLTY(food.getLTY());
	    		newfood.setRDX(food.getRDX());
	    		newfood.setRDY(food.getRDY());
	    		newfood.setX(food.getX());
	    		newfood.setY(food.getY());
	    		newFoodList.add(newfood);
	    		}
	    	}
			
		} else if(text.equals("allergy")) {
			
			foodlist = foodlistService.getFoodList(family);
			
	    	List<ProfileVO> allergyinfo = foodlistService.getAllergyList(family.getId().toString());
	    	ArrayList<AllergyVO> allergyList = new ArrayList<AllergyVO>();
	    	
	    	for (ProfileVO vo : allergyinfo) {
	    		AllergyVO foodall = new AllergyVO();
				String[] strList = vo.getAllergyinfo().split(":");
				for (String str : strList) {
					foodall.setFood(str);
					foodall.setUsername(vo.getUsername());
					allergyList.add(foodall);
				}
			}
	    	
	    	// 오늘 날짜 생성
	    	Date today = new Date();
	    	// 날짜 포멧 정하기
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
	    	String date1 = format.format(today);
	    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
	    	Date FirstDate = format.parse(date1);
	    	
	        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
	    	for(FoodListVO food: foodlist) {
	    		for(AllergyVO allergy: allergyList) {
	    			if(allergy.getFood().equals(food.getCls())) {
	    	    		LimitFoodListVO newfood = new LimitFoodListVO();
	    	    		String date2 = food.getEndDate();
	    	    		Date SecondDate = format.parse(date2);
	    	    		
	    	            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
	    	    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	    	            // 나온 결과 값에서 일 수로 전환 하기
	    	    		long calDateDays = calDate / ( 24*60*60*1000);
	    	    		newfood.setUsername(allergy.getUsername());
	    	    		newfood.setNum(food.getNum());
	    	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
	    	    		newfood.setCls(food.getCls());
	    	    		newfood.setDate(Long.toString(calDateDays));
	    	    		newfood.setEndDate(food.getEndDate());
	    	    		newfood.setId(food.getId());
	    	    		newfood.setImg(food.getImg());
	    	    		newfood.setInDate(food.getInDate());
	    	    		newfood.setLTX(food.getLTX());
	    	    		newfood.setLTY(food.getLTY());
	    	    		newfood.setRDX(food.getRDX());
	    	    		newfood.setRDY(food.getRDY());
	    	    		newfood.setX(food.getX());
	    	    		newfood.setY(food.getY());
	    	    			newFoodList.add(newfood);
	    			}
	    		}
	    	}
	 
	    	
	    	
		} else {
			SearchVO search = new SearchVO();
			
			search.setId(family.getId());
			search.setText(text);
			
			foodlist = foodlistService.getSearchFoodList(search);
			
	    	// 오늘 날짜 생성
	    	Date today = new Date();
	    	// 날짜 포멧 정하기
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
	    	String date1 = format.format(today);
	    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
	    	Date FirstDate = format.parse(date1);
	    	
	        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
	    	for(FoodListVO food: foodlist) {
	    		
	    		LimitFoodListVO newfood = new LimitFoodListVO();
	    		String date2 = food.getEndDate();
	    		Date SecondDate = format.parse(date2);
	    		
	            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
	    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	            // 나온 결과 값에서 일 수로 전환 하기
	    		long calDateDays = calDate / ( 24*60*60*1000); 
	    		newfood.setNum(food.getNum());
	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
	    		newfood.setCls(food.getCls());
	    		newfood.setDate(Long.toString(calDateDays));
	    		newfood.setEndDate(food.getEndDate());
	    		newfood.setId(food.getId());
	    		newfood.setImg(food.getImg());
	    		newfood.setInDate(food.getInDate());
	    		newfood.setLTX(food.getLTX());
	    		newfood.setLTY(food.getLTY());
	    		newfood.setRDX(food.getRDX());
	    		newfood.setRDY(food.getRDY());
	    		newfood.setX(food.getX());
	    		newfood.setY(food.getY());
	    			newFoodList.add(newfood);
	    	}
	 
			
		}
		
		

		jarry = jsonParser.parse(gson.toJson(newFoodList));

	
	  	return jarry;
	}
	  
    
    
    @PostMapping("/addfoodlist")
    public String addFoodList(Model model, HttpServletRequest request) throws IOException, java.text.ParseException {
    	// 세션에 저장된 유저 정보 가져오기
    	HttpSession session = request.getSession();
    	FamilyVO user = (FamilyVO)session.getAttribute("user");
    	
    	// insideView.jsp에서 사용자가 선택한 재료 리스트
    	String[] selectlist = request.getParameter("selectlist").toString().split(":"); 
    	
    	for (String item : selectlist) {
			String[] vo = item.split(" ");
			FoodListVO foodVO = new FoodListVO();
			
			// resources 경로 가져오기
			String path = servletContext.getRealPath("/resources");
			// 이미지 로드
			//System.out.println(vo[6].toString().split("_")[0]);
			FileInputStream imageFile = new FileInputStream(path + "/detect/" + getKey(map, vo[6].toString().split("_")[0]) + "_" + vo[6].toString().split("_")[1] + ".png");
			// img to byte
			byte[] b = new byte[imageFile.available()];
			imageFile.read(b);
			
			// byte to base64 to string
			byte[] encoded = Base64Utils.encode(b);
			String base64Str = new String(encoded);
			
			//System.out.println(base64Str);
			
			foodVO.setLTX(Float.parseFloat(vo[0]));
			foodVO.setLTY(Float.parseFloat(vo[1]));
			foodVO.setRDX(Float.parseFloat(vo[2]));
			foodVO.setRDY(Float.parseFloat(vo[3]));
			foodVO.setX(Integer.parseInt(vo[4]));
			foodVO.setY(Integer.parseInt(vo[5]));
			foodVO.setCls(vo[6].split("_")[0]);
			foodVO.setImg("data:image/png;base64," + base64Str);
			foodVO.setId(user.getId());
			
			FoodInfoVO exp = foodlistService.getfoodexp(vo[6].split("_")[0]);
			
			SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
			
			Date date1 = new Date();
			Date date2 = new Date();
					
			String time1 = format.format(date1);
			
			date2 = format.parse(time1);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);
			cal.add(Calendar.DATE, Integer.parseInt(exp.getFoodexp ()));
			
			String fdate = format.format(cal.getTime());
			//System.out.println(fdate);
			
			foodVO.setInDate(time1);
			foodVO.setEndDate(fdate);
			
			foodlistService.insert_foodlist(foodVO);
			//foodlist.add(foodVO);
		}
    	
    	
    	
    	//System.out.println();
    	//System.out.println(user);
    	return "redirect:/foodlist";
    }
    
    @GetMapping("/insideAdd")
    public String getinsideAdd() {
    	return "insideAdd";
    }
    
    @PostMapping("/insideAdd")
    public String insideAdd(FoodListVO vo, Model model, HttpServletRequest request) {
    	
    	HttpSession session = request.getSession();
    	FamilyVO user = (FamilyVO)session.getAttribute("user");
    	vo.setId(user.getId());
    	System.out.println(vo);
    	foodlistService.insert_foodlist(vo);
    	return "redirect:/insideView";
    }
    
   
 	@RequestMapping(value="/foodlist/edit", produces="application/text; charset=utf8")
 	@ResponseBody
 	public String foodedit(FoodListVO vo){
 		foodlistService.updatefood(vo);
 		return "success";
 	}
 	
 	@RequestMapping(value="/foodlist/delete", produces="application/text; charset=utf8")
 	@ResponseBody
 	public String fooddelete(FoodListVO vo){
 		foodlistService.deletefood(vo);
 		return "success";
 	}
 	
    
    @GetMapping("/foodlist")
    public String getFoodlist(Model model, HttpSession session) throws java.text.ParseException {
    	
    	FamilyVO user = (FamilyVO)session.getAttribute("user");
    	List<FoodListVO> foodlist = foodlistService.getFoodList(user);
    	
    	// 유통기한이 3일 내로 남은 것들만 담을 리스트 생성
    	List<LimitFoodListVO> newFoodList = new ArrayList<LimitFoodListVO>();
    	// 오늘 날짜 생성
    	Date today = new Date();
    	// 날짜 포멧 정하기
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
    	String date1 = format.format(today);
    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
    	Date FirstDate = format.parse(date1);
    	
        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
    	for(FoodListVO food: foodlist) {
    		
    		LimitFoodListVO newfood = new LimitFoodListVO();
    		String date2 = food.getEndDate();
    		Date SecondDate = format.parse(date2);
    		
            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
            // 나온 결과 값에서 일 수로 전환 하기
    		long calDateDays = calDate / ( 24*60*60*1000); 
    		newfood.setNum(food.getNum());
    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
    		newfood.setCls(food.getCls());
    		newfood.setDate(Long.toString(calDateDays));
    		newfood.setEndDate(food.getEndDate());
    		newfood.setId(food.getId());
    		newfood.setImg(food.getImg());
    		newfood.setInDate(food.getInDate());
    		newfood.setLTX(food.getLTX());
    		newfood.setLTY(food.getLTY());
    		newfood.setRDX(food.getRDX());
    		newfood.setRDY(food.getRDY());
    		newfood.setX(food.getX());
    		newfood.setY(food.getY());
    			newFoodList.add(newfood);
    	}
 
    	
    	model.addAttribute("foodlist",newFoodList);
    	return "foodlist";
    }
    
    @GetMapping("/limitfoodlist")
    public String getlimitFoodlist(Model model, HttpSession session) throws java.text.ParseException {
    	
    	FamilyVO user = (FamilyVO)session.getAttribute("user");
    	// 모든 재료 리스트 가져오
    	List<FoodListVO> foodlist = foodlistService.getFoodList(user);
    	// 유통기한이 3일 내로 남은 것들만 담을 리스트 생성
    	List<LimitFoodListVO> limitfoodlist = new ArrayList<LimitFoodListVO>();
    	// 오늘 날짜 생성
    	Date today = new Date();
    	// 날짜 포멧 정하기
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	
    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
    	String date1 = format.format(today);
    	// String 값으로 변환시킨 날짜를 계산하기 쉽게 Date 객체로 변경하기  
    	Date FirstDate = format.parse(date1);
    	
        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
    	for(FoodListVO food: foodlist) {
    		
    		LimitFoodListVO limitfood = new LimitFoodListVO();
    		String date2 = food.getEndDate();
    		Date SecondDate = format.parse(date2);
    		
            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
            // 나온 결과 값에서 일 수로 전환 하기
    		long calDateDays = calDate / ( 24*60*60*1000); 
    		// 만약 3일 이내로 남았을 경우
    		if(calDateDays >= -3) {
    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
    			limitfood.setCls(food.getCls());
    			limitfood.setDate(Long.toString(calDateDays));
    			limitfood.setEndDate(food.getEndDate());
    			limitfood.setId(food.getId());
    			limitfood.setImg(food.getImg());
    			limitfood.setInDate(food.getInDate());
    			limitfood.setLTX(food.getLTX());
    			limitfood.setLTY(food.getLTY());
    			limitfood.setRDX(food.getRDX());
    			limitfood.setRDY(food.getRDY());
    			limitfood.setX(food.getX());
    			limitfood.setY(food.getY());
    			limitfoodlist.add(limitfood);
    		}
    	}
 
    	model.addAttribute("limitfoodlist",limitfoodlist);
    	return "limitfoodlist";
    }
    

    @GetMapping("/used")
    public String getUsedFood(Model model, HttpServletRequest request) {
    	// 세션에 저장된 유저 정보 가져오기
    	//HttpSession session = request.getSession();
    	//FamilyVO user = (FamilyVO)session.getAttribute("user");
    	
    	//HttpSession session = request.getSession();
    	//String access_token = (String) session.getAttribute("access_Token");
    	//System.out.println("토근" + access_token);
    	//FamilyVO user = (FamilyVO)session.getAttribute("user");
    	
    	//System.out.println(user.getNickname());
    	
    	// 오늘 날짜 생성
    	Date today = new Date();
    	// 날짜 포멧 정하기
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	// 오늘 날짜를 위에 설정해둔 포멧으로 변경해서 String 값으로 저장하기
    	String date1 = format.format(today);
    	
    	String userID = request.getParameter("id");
    	
    	System.out.println("ID : " + userID);
    	
    	String usedFoodList = null;
    	try {
			usedFoodList = (Jsoup.connect("" + userID).ignoreContentType(true).get()).select("body").text().toString();
			//System.out.println(detect);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	JSONParser parser = new JSONParser();
    	JSONArray ja = null;
    	try {
    		ja = (JSONArray)parser.parse(usedFoodList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(ja);
    	
    	String kakaoText = "";
    	int mileage = 0;
    	for (int i = 0; i < ja.size() - 1; i++) {
    		String flag = ((JSONObject)ja.get(i)).get("flag").toString();
    		if (flag.equals("1")) {
    			//System.out.println(((JSONObject)ja.get(i)).get("cls").toString());
    			kakaoText += " " + ((JSONObject)ja.get(i)).get("cls").toString();
			}
    		//delete food
    		//FoodListVO foodVO = new FoodListVO();
    		//foodVO.setNum(Integer.parseInt(((JSONObject)ja.get(i)).get("num").toString()));
    		//foodlistService.deletefood(foodVO);
    		
    		//add mileage
    		mileage += Integer.parseInt(((JSONObject)ja.get(i)).get("mileage").toString());
		}
    	
    	mileage /= ja.size();
    	//insert mileage
    	//row가 없으면 새로 insert 있으면 update
    	//24시간을 3등분하고 지금 시간에 맞춰 query
    	Calendar cal = Calendar.getInstance();
    	int hour = cal.get(Calendar.HOUR_OF_DAY);
    	
    	MileageVO mileageVO = new MileageVO();
    	
    	mileageVO.setDate(date1);
    	mileageVO.setId(userID);
    	
    	if(hour <= 7) {
			//row가 있는지
    		mileageVO.setTime(1);
    		MileageVO result = foodlistService.getMileage(mileageVO);
    		if(result == null) {
    			//없으면 새로운 row 추가
    			foodlistService.insert_mileage(mileageVO);
    		}
    		else {
    			//있으면 기존 row에 현재 획득한 마일리지 더하고 평균내고 update
    			mileage = (mileage + result.getMileage()) / 2;
    			mileageVO.setMileage(mileage);
    			foodlistService.updateMileage(mileageVO);
    		}
		}
		else if(hour <= 15) {
			//row가 있는지
    		mileageVO.setTime(2);
    		MileageVO result = foodlistService.getMileage(mileageVO);
    		if(result == null) {
    			//없으면 새로운 row 추가
    			foodlistService.insert_mileage(mileageVO);
    		}
    		else {
    			//있으면 기존 row에 현재 획득한 마일리지 더하고 평균내고 update
    			mileage = (mileage + result.getMileage()) / 2;
    			mileageVO.setMileage(mileage);
    			foodlistService.updateMileage(mileageVO);
    		}
		}
		else if(hour <= 23) {
			//row가 있는지
    		mileageVO.setTime(3);
    		MileageVO result = foodlistService.getMileage(mileageVO);
    		if(result == null) {
    			//없으면 새로운 row 추가
    			foodlistService.insert_mileage(mileageVO);
    		}
    		else {
    			//있으면 기존 row에 현재 획득한 마일리지 더하고 평균내고 update
    			mileage = (mileage + result.getMileage()) / 2;
    			mileageVO.setMileage(mileage);
    			foodlistService.updateMileage(mileageVO);
    		}
		}
    	
    	//System.out.println(((JSONObject)ja.get(ja.size() - 1)).get("access_Token").toString());
    	String access_token = ((JSONObject)ja.get(ja.size() - 1)).get("access_Token").toString();
    	kakaoapi.sendmsgallergy(access_token, "알레르기를 유발할 수 있는 재료 [" + kakaoText + " ]가 사용되었습니다.");
    	
        return "1";
    }
    
}