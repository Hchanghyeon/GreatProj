package com.great.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.great.domain.FamilyVO;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("[인터셉터로그] 사용자가 로그인 상태에서 /login 페이지로가는 요청");
        HttpSession session =  request.getSession();
        FamilyVO user = (FamilyVO) session.getAttribute("user");
        // 로그인 세션이 있을 경우 true 반환하여 요청한 페이지 그대로 넘어가게함
        if(user == null) {
        	return true;
        } 
		
        // 로그인 세션이 없을 경우 로그인페이지로 sendRedirect로 넘어가게함
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
