package nuevo.grupo.spring.boot.proyecto.sowad.security;

import nuevo.grupo.spring.boot.proyecto.sowad.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final JpaUserDetailsService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     JpaUserDetailsService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticatedProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticatedProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                //INSITUCIONES
                .antMatchers(HttpMethod.DELETE,"/api/v1/QW/instituciones/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/QW/instituciones/**").hasAnyRole("EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/instituciones").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/instituciones/busqueda").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/instituciones/**").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/QW/instituciones/**").hasAnyRole("EMPLEADO","ADMIN")
                //PRODUCTO
                .antMatchers(HttpMethod.DELETE,"/api/v1/QW/productos/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/QW/productos/**").hasAnyRole("EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/productos").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/productos/busqueda").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/productos/**").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/QW/productos/**").hasAnyRole("EMPLEADO","ADMIN")
                //AYUDA
                .antMatchers(HttpMethod.DELETE,"/api/v1/QW/ayudas/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/v1/QW/ayudas/**").hasAnyRole("EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/ayudas").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/ayudas/busqueda").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/ayudas/**").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/QW/ayudas/**").hasAnyRole("EMPLEADO","ADMIN")
                //USUARIOS
                .antMatchers(HttpMethod.GET,"/api/v1/QW/").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/v1/QW/usuario/**").hasAnyRole("INVITADO","EMPLEADO","ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                ;
    }
}
