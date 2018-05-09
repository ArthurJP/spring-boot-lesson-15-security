package com.jp.springbootlesson15security.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO:
 *
 * @author <a href="mailto:zhangtylord@gmail.com>张俊鹏</a>
 * @see
 * @since 5/7/2018
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        CSRF Cross-site Request Forgery
        http.csrf().csrfTokenRepository( new CookieCsrfTokenRepository() ).requireCsrfProtectionMatcher(
                request -> request.getMethod().equals( "POST" ) && request.getRequestURI().startsWith( "login" )
        );

//        CSP Content Security Policy
        http.headers().contentSecurityPolicy( "script-src https://code.jquery.com/" );

//        X-Frame-Options header iframe的加载控制
//        相同域名允许
        http.headers().frameOptions().sameOrigin();

//        指定白名单可以访问
//        http.headers().addHeaderWriter( new XFrameOptionsHeaderWriter( new AllowFromStrategy() {
////            @Override
////            public String getAllowFromValue(HttpServletRequest request) {
////                return "url example:www.baidu.com";
////            }
////        } ) )

//        XSS header
        http.headers().xssProtection().block( true );

        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and()
                .formLogin().usernameParameter( "name" )
                .passwordParameter( "pwd" )
                .loginPage( "/login" )
                .loginProcessingUrl( "/loginAction" )
                .failureUrl( "/error" )
                .permitAll()
                .and().logout().permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser( "jp" ).password( "1234" ).roles( "ADMIN " )
                .and().withUser( "刘德华" ).password( "123456" ).roles( "USER" );
    }
}
