package com.proyectoFinal.OMSA.config;

/**
 * Created by Dany on 06/09/2017.
 */
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


@Configurable
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.username, u.password, u.enabled from usuario u where u.username=?")
                .authoritiesByUsernameQuery("select r.username, r.rol from rol r where r.username=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/autobus/**","/parada/**", "/ruta/**", "/coordenada/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN").anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin()
                    .loginPage("/login")
//                    .defaultSuccessUrl("/home", true)
                      .successHandler(customAuthenticationSuccessHandler)
                    .failureUrl("/login?error")
                    .permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers( "/fonts/**","/font-awesome/**", "/css/**", "/js/**", "/images/**","/api/**");
    }

}