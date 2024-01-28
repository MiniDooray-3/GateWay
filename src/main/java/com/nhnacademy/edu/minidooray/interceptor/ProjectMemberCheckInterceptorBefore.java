package com.nhnacademy.edu.minidooray.interceptor;

import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProjectMemberCheckInterceptorBefore implements HandlerInterceptor {

    private final ApplicationContext applicationContext;

    public ProjectMemberCheckInterceptorBefore(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        String userId = request.getSession().getAttribute("LOGIN_ID").toString();
        Long projectId = Long.parseLong(request.getSession().getAttribute("projectId").toString());

        boolean isMember = Objects.nonNull(memberService.getMember(userId, projectId));

        if (!isMember) {
            response.sendRedirect("/access-denied");
        }
    }
}
