package com.spring.postme.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AdminInterceptor implements HandlerInterceptor{

	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	    HttpSession session = request.getSession(false);

	    // 관리자인지 확인
	    if (session.getAttribute("isAdmin") == null || session.getAttribute("isAdmin").equals(false) || session.getAttribute("isAdmin").equals(0)) {
	        // 관리자가 아닌 경우 로그인 페이지로 리다이렉트
	        response.sendRedirect("/login");
	        return false;
	    }

	    return true;
	}
	@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        

	
        
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
   
        
        
    }    
}
