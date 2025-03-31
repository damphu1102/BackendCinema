package com.example.backendcinema.config;

import com.example.backendcinema.Utils.JWTTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         //        Lấy đc giá trị token
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)){
            token = token.replace("Bearer", "");
        }

        //        Giải mã token để lấy ra user
        UsernamePasswordAuthenticationToken userToken = jwtTokenUtils.parseAccessToken(token);

        //        Lấy user để xác thực phân quyền dùng trong bước sau
        SecurityContextHolder.getContext().setAuthentication(userToken); // xác thực thành công
        filterChain.doFilter(request, response); //hàm quyết đinh request có được đi tiếp hay k


    }
}
