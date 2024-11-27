//package com.example.demo.config;
//
//import cn.hutool.crypto.SecureUtil;
//import com.example.demo.entity.UserEntity;
//import com.example.demo.mapper.IAdmMapper;
//import com.example.demo.mapper.IUserMapper;
//import com.example.demo.mapper.Impl.UserMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.stereotype.Service;
//
//import javax.sql.DataSource;
//import java.util.Collections;
//
///*
//    新版本SpringSecurity需要加上@Configuration注解，否则会报错
//    也别忘了 @EnableWebSecurity注解
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    // 导入数据库用户详情服务
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new UserDetailServiceImpl();
//    }
//
//    // 自定义密码加密器(hutool的md5加盐)
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new MyPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/", "/welcome", "/user_login","/adm_login" ,"/register", "/pic/**", "/videos/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/user_login")
//                        .loginProcessingUrl("/user/login")
//                        .usernameParameter("account")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/user/home")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/", "/welcome", "/adm_login","/user_login", "/register", "/pic/**", "/videos/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/adm_login")
//                        .loginProcessingUrl("/adm/login")
//                        .usernameParameter("account")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/adm/home")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//}
//
//// 自定义密码加密器
//class MyPasswordEncoder implements PasswordEncoder{
//
//    @Override
//    public String encode(CharSequence rawPassword) {
//        // 对输入的密码进行 MD5 编码，并附加盐值
//        return SecureUtil.md5(rawPassword.toString() + "nuist");
//    }
//
//    @Override
//    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        // 检查输入的密码是否与数据库中存储的编码后的密码匹配
//        String candidatePassword = SecureUtil.md5(rawPassword.toString() + "nuist");
//        return encodedPassword.equals(candidatePassword);
//    }
//}
//
//// 自定义用户详情服务
//@Service
//class UserDetailServiceImpl implements UserDetailsService {
//    @Autowired
//    DataSource dataSource;
//    @Autowired
//    IUserMapper userMapper;
//    @Autowired
//    IAdmMapper admMapper;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userMapper.selectUser(username);
//        if (userEntity != null){
//            return new User(username,
//                    userEntity.getUserPassword(),
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            );
//        }
//
//        return null;
//    }
//}