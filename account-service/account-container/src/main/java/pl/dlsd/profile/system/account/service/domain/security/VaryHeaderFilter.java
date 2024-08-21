package pl.dlsd.profile.system.account.service.domain.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class VaryHeaderFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
            @Override
            public void addHeader(String name, String value) {
                if (!HttpHeaders.VARY.equals(name) && !HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN.equals(name)) {
                    super.addHeader(name, value);
                }
            }

            @Override
            public void setHeader(String name, String value) {
                if (!HttpHeaders.VARY.equals(name) && !HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN.equals(name)) {
                    super.setHeader(name, value);
                }
            }
        });
    }

}