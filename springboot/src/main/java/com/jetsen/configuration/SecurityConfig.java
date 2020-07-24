package com.jetsen.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NICKEL
 */
@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER) //通过order可以定义多个adapter，order越小优先级越高
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/home", "/elastic/**").permitAll()
                .anyRequest().authenticated().and()
                .formLogin()
                //.loginPage("/login")
                .loginProcessingUrl("/login/process")
                .successForwardUrl("/login/success").failureForwardUrl("/login/failure")
                .permitAll().and()
                .logout().permitAll();
        http.csrf().disable(); //关闭CSRF，否则POST等操作会被spring security抛403错误。 正式环境不能直接关闭，官方有其他解决方法
    }

    //密码编码器
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

    //定义用户信息服务（查询用户信息）
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetailsRepository manager = new UserDetailsRepository();
        manager.createUser(User.withUsername("nickel").password("{bcrypt}$2a$10$nRjuzbV2PgMxUeBGR1FPWux802JwaYb4Xddqfz2p8uRilPDelLmxm").roles("USER").build());
        return manager;
    }

    /*
     * 自定义UserDetailsManager
     * */
    class UserDetailsRepository implements UserDetailsManager {
        private Map<String, UserDetails> users = new HashMap<>();

        @Override
        public void createUser(UserDetails user) {
            users.putIfAbsent(user.getUsername(), user);
        }

        @Override
        public void updateUser(UserDetails user) {
            users.put(user.getUsername(), user);
        }

        @Override
        public void deleteUser(String username) {
            users.remove(username);
        }

        @Override
        public void changePassword(String oldPassword, String newPassword) {
            Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
            if (currentUser == null) {
                throw new AccessDeniedException("Can't change password as no Authentication object found in context for current user.");
            }
            String username = currentUser.getName();
            UserDetails user = users.get(username);
            //TODO 执行修改密码的逻辑
        }

        @Override
        public boolean userExists(String username) {
            return users.containsKey(username);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return users.get(username);
        }
    }
}
