package com.huneth.hams.common.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        // Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
        String uri = "/";


        // 있을 경우 URI 등 정보를 가져와서 사용
        if (savedRequest != null) {
            uri = savedRequest.getRedirectUrl();

            // 세션에 저장된 객체를 다 사용한 뒤에는 지워줘서 메모리 누수 방지
            requestCache.removeRequest(httpServletRequest, httpServletResponse);

            System.out.println(uri);
        }

        httpServletResponse.sendRedirect(uri);
    }
}
