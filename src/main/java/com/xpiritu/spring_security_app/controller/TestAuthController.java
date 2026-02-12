package com.xpiritu.spring_security_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")

public class TestAuthController {

    /*@GetMapping("/hola")
    @PreAuthorize("permitAll()")
    public String hola(){
        return "Hola Mundo";
    }

    @GetMapping("/hola_con_seguridad")
    @PreAuthorize("hasAuthority('READ')") //Solo los usuarios con la autoridad "READ" pueden acceder a este endpoint. OJO con comillas.
    public String hola_con_seguridad(){
        return "Hola Mundo con Seguridad";
    }

    @GetMapping("/hola_con_seguridad2")
    @PreAuthorize("hasAuthority('CREATE')") //Solo los usuarios con la autoridad "CREATE" pueden acceder a este endpoint. OJO con comillas.
    public String hola_con_seguridad2(){
        return "Hola Mundo con Seguridad 2";
    }/* */

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('READ')") //Solo los usuarios con la autoridad "READ" pueden acceder a este endpoint. OJO con comillas.
    public String helloGet(){
        return "Hola Mundo con GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('CREATE')") //Solo los usuarios con la autoridad "CREATE" pueden acceder a este endpoint. OJO con comillas.
    public String helloPost(){
        return "Hola Mundo con POST";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('UPDATE')") //Solo los usuarios con la autoridad "UPDATE" pueden acceder a este endpoint. OJO con comillas.
    public String helloPut(){
        return "Hola Mundo con PUT";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')") //Solo los usuarios con la autoridad "DELETE" pueden acceder a este endpoint. OJO con comillas.
    public String helloDelete(){
        return "Hola Mundo con DELETE";
    }

}
