//package com.nvm.lession4.config;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.DeferredSecurityContext;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.web.context.HttpRequestResponseHolder;
//import org.springframework.security.web.context.SecurityContextRepository;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomSecurityContextRepository implements SecurityContextRepository {
//
//    private final HttpSession httpSession;
//
//    @Override
//    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
//
//        return null;
//    }
//
//    @Override
//    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request) {
//        return SecurityContextRepository.super.loadDeferredContext(request);
//    }
//
//    @Override
//    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
//
//    }
//
//    @Override
//    public boolean containsContext(HttpServletRequest request) {
//        return false;
//    }
//
//
//}
//
