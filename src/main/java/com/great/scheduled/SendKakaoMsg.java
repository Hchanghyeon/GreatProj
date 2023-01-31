package com.great.scheduled;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.great.domain.FamilyVO;
import com.great.domain.FoodListVO;
import com.great.domain.KakaoVO;
import com.great.domain.LimitFoodListVO;
import com.great.domain.ProfileVO;
import com.great.service.FoodListService;
import com.great.service.KakaoAPI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.mail.Session;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;


@Component
public class SendKakaoMsg{
	private ThreadPoolTaskScheduler scheduler;
	private String cron = "";
    
	@Autowired
	FoodListService foodlistService;
	
    @Autowired
    KakaoAPI kakao;
    
	public void startScheduler(HttpSession session) {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// scheduler setting 
		scheduler.schedule(getRunnable(session), getTrigger());
	}

	public void changeCronSet(String cron) {
		System.out.println("카카오톡 알림 세팅 값 cron : " + cron);
		this.cron = cron;
	}

	public void stopScheduler() {
		scheduler.shutdown();
	}

	private Runnable getRunnable(HttpSession session) {
		// do something
		return () -> {
			Date date = new Date();
			System.out.println("[스케쥴] 카카오톡 메시지 발송 시간 : " + date);
			
			FamilyVO user = (FamilyVO)session.getAttribute("user");
			ProfileVO profile = (ProfileVO)session.getAttribute("profile");
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
	    	Date FirstDate = null;
			try {
				FirstDate = format.parse(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	        // 식재료 리스트 안에 있는 재료들을 반복문으로 돌리기 
	    	for(FoodListVO food: foodlist) {
	    		
	    		LimitFoodListVO limitfood = new LimitFoodListVO();
	    		String date2 = food.getEndDate();
	    		Date SecondDate = null;
				try {
					SecondDate = format.parse(date2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	            // 오늘 날짜에서 식재료 리스트안에 들어있는 날짜 빼기 
	    		long calDate = FirstDate.getTime() - SecondDate.getTime(); 
	            // 나온 결과 값에서 일 수로 전환 하기
	    		long calDateDays = calDate / ( 24*60*60*1000); 
	    		// 만약 3일 이내로 남았을 경우
	    		if(calDateDays >= -3) {
	    			// 기존에 사용하던 foodlistVO에 유통기한 남은 날짜를 저장할 수 없어 새로운 limitfoodlistVO를 만들어 수동으로 저장했
	    			limitfood.setCls(food.getCls());
	    			limitfood.setDate(Long.toString(calDateDays));
	    			limitfoodlist.add(limitfood);
	    		}
	    	}
	    	String username = user.getNickname();
	    	String msg = username + "가족 냉장고의 유통기한 및 취급주의 재료 안내입니다. ";
	    	
	    	for(int i = 0; i < limitfoodlist.size(); i++) {
	    		msg = msg + limitfoodlist.get(i).getCls() + " : " + limitfoodlist.get(i).getDate() + "일";
	    	}	
	    
	    	kakao.sendmsg((String)session.getAttribute("access_Token"), profile.getUsername(), limitfoodlist);
		};
	}


	private Trigger getTrigger() {
		// cronSetting
		return new CronTrigger(cron);
	}

	@PostConstruct
	public void init() {
		
	}

	@PreDestroy
	public void destroy() {
		stopScheduler();
	}
}