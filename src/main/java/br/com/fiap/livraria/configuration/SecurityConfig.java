package br.com.fiap.livraria.configuration;

import br.com.fiap.livraria.security.JwtAuthenticationEntrypoint;
import br.com.fiap.livraria.security.JwtFilter;
import br.com.fiap.livraria.security.JwtUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // ***

    private final JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;
    private final JwtUserDetailService jwtUserDetailService;
    private final JwtFilter jwtFilter;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(
            JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint,
            JwtUserDetailService jwtUserDetailService,
            JwtFilter jwtFilter,
            PasswordEncoder passwordEncoder
    ){
        this.jwtAuthenticationEntrypoint = jwtAuthenticationEntrypoint;
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtFilter = jwtFilter;
        this.passwordEncoder = passwordEncoder;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    // ***

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService)
                        .passwordEncoder(passwordEncoder);
        /*
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN","USER")
                .and()
                .withUser("user").password("{noop}senha123").roles("USER");
        */
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* ** Versão antiga, sem o JWT
        http.httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/livros", "/livros/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin();

        */


        /* Versão do professor, no vídeo
        http.csrf().disable()
                        .authorizeRequests()
                                .antMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .anyRequest().authenticated().and()
                        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntrypoint)
                        .and().sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

         */
        /** Versão do pdf e html **/
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .anyRequest().authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntrypoint)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .formLogin().disable();
                /** */

    }

}
