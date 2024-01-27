package com.nhnacademy.edu.minidooray.interceptor;

import com.nhnacademy.edu.minidooray.service.MemberService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

public class ProjectMemberCheckInterceptor implements HandlerInterceptor {

    private final ApplicationContext applicationContext;

    public ProjectMemberCheckInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        MemberService memberService = applicationContext.getBean(MemberService.class);
        String userId = request.getSession().getAttribute("LOGIN_ID").toString();
        Object tmpProjectId = request.getSession().getAttribute("projectId");
        if (Objects.isNull(tmpProjectId)) {
            return false;
        }

        Long projectId = Long.parseLong(tmpProjectId.toString());
        boolean isMember = Objects.nonNull(memberService.getMember(userId, projectId));

        if (!isMember) {
            response.sendRedirect("/access-denied");
            return false;
        }
        return true;
    }
}