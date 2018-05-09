package com.jp.springbootlesson15security.security;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO:
 *
 * @author <a href="mailto:zhangtylord@gmail.com>张俊鹏</a>
 * @since 5/9/2018
 */
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/iframe-parent" ).setViewName( "iframe-parent" );
        registry.addViewController( "/iframe-child.html" ).setViewName( "iframe-child" );
        registry.addViewController( "/error" ).setViewName( "error" );
    }
}
