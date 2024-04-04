package com.issue.issuefeedbacksystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issue.issuefeedbacksystem.config.AllowApiConfig;
import com.issue.issuefeedbacksystem.utils.JwtUtil;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final RoleState roleState;
    private final AllowApiConfig allowApi;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/api/user") || request.getRequestURI().startsWith("/api/auth")){
            filterChain.doFilter(request, response);
            return;
        }

        // 如果token不存在或格式不正确，返回错误
        if (token == null || !token.startsWith("Bearer ")) {
           notAuth(response);
           return;
        }

        try {
            // 解析token
            String tokenBody = token.substring(7);
            Claims claims = jwtUtil.extractClaims(tokenBody);

            // 检查token是否过期
            Date expirationDate = claims.getExpiration();
            if (expirationDate.before(new Date())) {
                notAuth(response);
                return;
            }

            // 检查权限
            RoleName roleName = roleState.getRole((Integer)claims.get("roleId"));
            switch (roleName){
                case STUDENT -> {
                    if (allowApi.getStudent().stream().noneMatch(api -> request.getRequestURI().startsWith(api))) {
                        forbidden(response);
                        return;
                    }
                }
                case TEACHER -> {
                    if (allowApi.getTeacher().stream().noneMatch(api -> request.getRequestURI().startsWith(api))) {
                        forbidden(response);
                        return;
                    }
                }
                case DEPT_LEADER -> {
                    if (allowApi.getDeptLeader().stream().noneMatch(api -> request.getRequestURI().startsWith(api))) {
                        forbidden(response);
                        return;
                    }
                }
                case SCHOOL_LEADER -> {
                    if (allowApi.getSchoolLeader().stream().noneMatch(api -> request.getRequestURI().startsWith(api))) {
                        forbidden(response);
                        return;
                    }
                }
                case ADMIN -> {
                    if (allowApi.getAdmin().stream().noneMatch(api -> request.getRequestURI().startsWith(api))) {
                        forbidden(response);
                        return;
                    }
                }
            }
            // 如果权限检查通过，继续请求
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException ex) {
            // Token已过期
            notAuth(response);
            log.error(String.valueOf(ex));
        } catch (Exception e) {
            notAuth(response);
            log.error(String.valueOf(e));
        }

    }

    private void notAuth(HttpServletResponse response){
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8"); // 设置字符编码为 UTF-8
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(MsgResult.notAuth()));
        } catch (IOException e) {
            logger.error("授权凭证有误时写入响应发生异常: " + e);
        }
    }

    private void forbidden(HttpServletResponse response){
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding("UTF-8"); // 设置字符编码为 UTF-8
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(MsgResult.forbidden()));
        } catch (IOException e) {
            logger.error("暂无权限时写入响应发生异常: " + e);
        }
    }
}
