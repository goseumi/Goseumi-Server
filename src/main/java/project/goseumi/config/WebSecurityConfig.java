package project.goseumi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        //csrf, cors disable
        http
                .csrf((csrf) -> csrf.disable())
                .cors((cors) -> cors.disable())

                //formLogin, httpBasic disable
                .formLogin((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable())

                //api 접근권한 설정
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().permitAll())

                //jwt 세션 stateless
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
