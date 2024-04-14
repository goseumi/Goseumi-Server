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

                //Form 로그인 방식 disable
                .formLogin((auth) -> auth.disable())

                //http basic 인증 방식 disable
                .httpBasic((auth) -> auth.disable())

                //경로별 인가 작업
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/", "/join").permitAll() //전체 허용
                        .requestMatchers("/admin").hasRole("ADMIN") ///admin 경로는 어드민 권환만 허용
                        .anyRequest().authenticated()) //그 외 요청은 로그인 한 사용자

                //세션 설정(jwt 방식에서는 세션을 stateless 상태로 관리해야한다.)
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
