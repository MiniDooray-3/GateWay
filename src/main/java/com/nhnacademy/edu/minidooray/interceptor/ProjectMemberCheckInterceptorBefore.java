package com.nhnacademy.edu.minidooray.interceptor;

import com.nhnacademy.edu.minidooray.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

public class ProjectMemberCheckInterceptorBefore implements HandlerInterceptor {

    private final ApplicationContext applicationContext;

    public ProjectMemberCheckInterceptorBefore(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        HttpSession session = request.getSession(false);

        String userId = String.valueOf(session.getAttribute("LOGIN_ID"));

        String[] temps = request.getRequestURI().split("/");
        Long projectId = Long.parseLong(temps[temps.length - 1]);
        memberService.getMember(userId, projectId);

        return true;
    }
}
