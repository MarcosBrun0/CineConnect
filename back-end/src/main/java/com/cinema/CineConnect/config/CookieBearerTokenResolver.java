package com.cinema.CineConnect.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.web.util.WebUtils;
import java.util.Optional;


public class CookieBearerTokenResolver implements BearerTokenResolver {
    private final String cookieName = "token";

        @Override
        public String resolve(HttpServletRequest request) {
            return Optional.ofNullable(WebUtils.getCookie(request, this.cookieName))
                    .map(Cookie::getValue)
                    .orElse(null);
        }
    }

