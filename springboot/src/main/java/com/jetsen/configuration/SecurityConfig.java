package com.jetsen.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author NICKEL
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home", "/elastic/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login").permitAll().and()
                .logout().permitAll();
        http.csrf().disable(); //关闭CSRF，否则POST等操作会被spring security抛403错误。 正式环境不能直接关闭，官方有其他解决方法
    }

    //密码编码器
 @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //定义用户信息服务（查询用户信息）
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("nickel").password("ironpearl").roles("USER").build();
//        return new InMemoryUserDetailsManager(user);

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("nickel").password("ironpearl").roles("USER").build());
        return manager;
    }
}
