package com.great.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.great.domain.FamilyVO;
import com.great.domain.ProfileVO;

public class ProfileInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("[인터셉터로그] 프로필 세션 확인중");
        HttpSession session =  request.getSession();
        
        ProfileVO profile = (ProfileVO) session.getAttribute("profile");
        // 프로필 세션이 있을 경우 true 반환하여 요청한 페이지 그대로 넘어가게함(메인 페이지로 넘어감)
        if(profile != null) {
        	return true;
        } 
		// 프로필 세션이 없을 경우 프로필 선택화면으로 넘어가게함
        response.sendRedirect("/profile");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
