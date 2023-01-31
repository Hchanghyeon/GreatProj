package com.great.scheduled;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.great.domain.KakaoVO;
import com.great.service.FoodListService;
import com.great.service.KakaoAPI;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;


@Component
public class KakaoTokenRefresh{
	private ThreadPoolTaskScheduler scheduler;
	private String cron = "0 0 0/1 * * *";
    
    @Autowired
    KakaoAPI kakao;
    
	public void startScheduler(HttpSession session) {
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		// scheduler setting 
		scheduler.schedule(getRunnable(session), getTrigger());
	}

	public void changeCronSet(String cron) {
		System.out.println("cron : " + cron);
		this.cron = cron;
	}

	public void stopScheduler() {
		scheduler.shutdown();
	}

	private Runnable getRunnable(HttpSession session) {
		// do something
		return () -> {
				if(session.getAttribute("refresh_Token")!=null) {
					String refresh_token = (String)session.getAttribute("refresh_Token");
					Date date = new Date();
					System.out.println("[스케쥴] 리프레시 토근 갱신시간 : " + date);
					System.out.println("refresh된 토큰 : " + refresh_token);
					KakaoVO token = kakao.refreshToken(refresh_token);
					session.setAttribute("access_Token", token.getAccess_token());
					System.out.println("새로 발급된 access_Token: " + token.getAccess_token());
				}
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