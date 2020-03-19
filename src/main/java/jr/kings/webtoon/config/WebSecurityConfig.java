package jr.kings.webtoon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import jr.kings.webtoon.security.CustomAccessDeniedHandler;
import jr.kings.webtoon.security.CustomLoginSuccessHandler;
import jr.kings.webtoon.security.CustomLogoutSuccessHandler;
import jr.kings.webtoon.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // @Autowired
    // private CustomUserDetailsService userDetailsService;

    // AuthenticationManagerBuilder: 유저 인증정보 설정
    // WebSecurity: security 전역설정 -> HttpSecurity보다 우선시되며, static인증이 필요없는 리소스는 이곳에서
    // 설정 가능.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("user") // user 계정을 생성했다. 이부분에 로그인아이디가
        // 된다.
        // .password("$2a$10$X5ISSje9qplha5nLI9ZHWumvmH7I1mnq2xUHKeI15Gw91UnMC/4AW") //
        // passwordEncoder 로 등록 한 인코더로 1234 를 암호화한다.
        // .roles("USER"); // 유저에게 USER 라는 역할을 제공한다.
        // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css", "/js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http // HttpSecurity 객체를 설정한다.
                 .authorizeRequests() // 권한요청 처리 설정 메서드이다.
                // .antMatchers("/noLangToon/myPage").hasAnyRole("USER")
                // .antMatchers("/noLangToon/webtoonList").access("hasRole('ROLE_USER')")
                // .antMatchers("/test/admin").access("hasRole('ROLE_USER')")
                .anyRequest().permitAll(); // 다른 요청은 누구든지 접근 할수 있다.

        // http.exceptionHandling().accessDeniedHandler(accessDeniedHandler()); // 예외 발생
        http.formLogin().loginPage("/noLangToon/login").successHandler(loginSuccessHandler()); // 로그인 성공 시

        http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler())
        .invalidateHttpSession(false).deleteCookies("remember-me", "JSESSION_ID");

        http.rememberMe().key("webtoon").tokenRepository(persistentTokenRepository()).tokenValiditySeconds(6040800);
    }
    
    // PasswordEncoder를 설정해준다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }

}