package com.xpiritu.spring_security_app;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import com.xpiritu.spring_security_app.persistence.entity.PermissionEntity;
import com.xpiritu.spring_security_app.persistence.entity.RoleEnum;
import com.xpiritu.spring_security_app.persistence.entity.RolesEntity;
import com.xpiritu.spring_security_app.persistence.entity.UserEntity;
import com.xpiritu.spring_security_app.persistence.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityAppApplication {

    private final AuthenticationManager authenticationManager;

    SpringSecurityAppApplication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			//1) Crear permisos
			PermissionEntity createPermission = PermissionEntity.builder().name("CREATE").build(); //Creamos un permiso de CREAR
			PermissionEntity readPermission = PermissionEntity.builder().name("READ").build(); //Creamos un permiso de LEER
			PermissionEntity updatePermission = PermissionEntity.builder().name("UPDATE").build(); //Creamos un permiso de ACTUALIZAR
			PermissionEntity deletePermission = PermissionEntity.builder().name("DELETE").build(); //Creamos un permiso de ELIMINAR
			//2) Crear roles
			RolesEntity adminRole = RolesEntity.builder().rolenum(RoleEnum.ADMIN).permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission)).build(); //Creamos un rol de ADMIN con todos los permisos
			RolesEntity userRole = RolesEntity.builder().rolenum(RoleEnum.USER).permissions(Set.of(readPermission,createPermission)).build(); //Creamos un rol de USER con solo el permiso de LEER y CREAR
			RolesEntity invitedRole = RolesEntity.builder().rolenum(RoleEnum.INVITED).permissions(Set.of(readPermission)).build(); //Creamos un rol de INVITED con solo el permiso de LEER.
			RolesEntity developerRole = RolesEntity.builder().rolenum(RoleEnum.DEVELOPER).permissions(Set.of(createPermission, readPermission, updatePermission)).build(); //Creamos un rol de DEVELOPER con los permisos de CREAR, LEER y ACTUALIZAR.
			//3) Crear usuarios
			UserEntity PapilluUser = UserEntity.builder().username("Papillu").password("$2a$10$rvNmJY8fOKkLkC6QMXdoqORuuX5qhtDBlp8pEDATlvdLec2gTl.t2").isenabled(true)
			.accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(adminRole)).build();
			UserEntity DanieeelUser = UserEntity.builder().username("Danieeel").password("4321").isenabled(true)
			.accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(userRole)).build();
			UserEntity InvitedUser = UserEntity.builder().username("Invited").password("5678").isenabled(true)
			.accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(invitedRole)).build();
			UserEntity DeveloperUser = UserEntity.builder().username("Developer").password("8765").isenabled(true)
			.accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).roles(Set.of(developerRole)).build();
			userRepository.saveAll(Set.of(PapilluUser, DanieeelUser, InvitedUser, DeveloperUser));

		};
	}

}
