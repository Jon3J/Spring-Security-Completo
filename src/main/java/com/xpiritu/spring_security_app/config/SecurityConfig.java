package com.xpiritu.spring_security_app.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.xpiritu.spring_security_app.service.UserDetailServiceImpl;


@Configuration //Se trata de una clase de configuración de Spring
@EnableWebSecurity //Para habilitar la seguridad web en la aplicación
@EnableMethodSecurity //Nos permite usar anotaciones de seguridad a nivel de método, como @PreAuthorize o @Secured, para controlar el acceso a métodos específicos en nuestros controladores o servicios.
public class SecurityConfig {


    //Configurar la primera parte --> SECURITY FILTER CHAIN
    
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //este HttpSecurity es un objeto que nos permite configurar la seguridad de nuestra aplicación web, como las reglas de acceso, la autenticación, etc.
        //Pasa por todos los filtros, cuando hacemos un REQUEST, se hace un HttpSecurity en el filtro 1 y luego pasa al filtro 2, etc. hasta llegar al filtro final que es el que procesa la solicitud y devuelve la respuesta.
        return httpSecurity.csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(http -> {
            //Configurar los endpoints públicos
            http.requestMatchers(HttpMethod.GET, "/auth/hello").permitAll();
            //Configurar los endpoints privados
            http.requestMatchers(HttpMethod.GET,"/auth/hola_con_seguridad").hasAuthority("CREATE");

            //Configurar el resto de endpoints, que no estén configurados, NO ESPECIFICADOS
            http.anyRequest().denyAll();
        })
        .build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //este HttpSecurity es un objeto que nos permite configurar la seguridad de nuestra aplicación web, como las reglas de acceso, la autenticación, etc.
        //Pasa por todos los filtros, cuando hacemos un REQUEST, se hace un HttpSecurity en el filtro 1 y luego pasa al filtro 2, etc. hasta llegar al filtro final que es el que procesa la solicitud y devuelve la respuesta.
        return httpSecurity.csrf(csrf -> csrf.disable())
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }


    //Este AuthenticationManager lo tenemos que crear a partir de un objeto que ya existe en Spring Security.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    /*@Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance(); //NoOpPasswordEncoder es una implementación de PasswordEncoder que no realiza ninguna codificación en las contraseñas. Es decir, las contraseñas se almacenan y comparan en texto plano. Esto es útil para propósitos de desarrollo o pruebas, pero no se recomienda para entornos de producción debido a problemas de seguridad.
        //recomendable sólamente para pruebas, no para producción. De momento sin encriptar las contraseñas.
    }*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("1234"));
    }

    /*@Bean
    public UserDetailsService userDetailsService(){
       List<UserDetails> userDetailsList = new ArrayList<>();
       userDetailsList.add(User.withUsername("Papillu").password("1234").roles("ADMIN").authorities("READ","CREATE").build());
       userDetailsList.add(User.withUsername("Danieeel").password("4321").roles("USER").authorities("READ").build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/

}
