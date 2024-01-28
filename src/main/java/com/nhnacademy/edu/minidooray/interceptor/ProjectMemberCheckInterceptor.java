package com.nhnacademy.edu.minidooray.interceptor;

import com.nhnacademy.edu.minidooray.service.MemberService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class ProjectMemberCheckInterceptor implements HandlerInterceptor {

    private final ApplicationContext applicationContext;

    public ProjectMemberCheckInterceptor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        MemberService memberService = applicationContext.getBean(MemberService.class);
        HttpSession session = request.getSession(false);

        String userId = String.valueOf(session.getAttribute("LOGIN_ID"));

        Object temp = session.getAttribute("projectId");

        if (Objects.isNull(temp)) {
            response.sendRedirect("/access-denied");
            return false;
        }

        Long projectId = Long.parseLong(String.valueOf(temp));

        memberService.getMember(userId, projectId);
        return true;
    }
}
