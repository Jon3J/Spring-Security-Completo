package com.xpiritu.spring_security_app.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xpiritu.spring_security_app.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> { //También podriamos haber puesto JpaRepository, pero CrudRepository es suficiente para las operaciones básicas de CRUD (Crear, Leer, Actualizar, Eliminar).

    Optional<UserEntity> findUserEntityByUsername(String username); //Este método nos permite buscar un usuario por su nombre de usuario. Devuelve un Optional, que puede contener un UserEntity si se encuentra, o estar vacío si no se encuentra.

    //@Query("SELECT u FROM UserEntity u WHERE u.username = ?")
    //Optional<UserEntity> findUser(String username); //Este método es similar al anterior, pero utiliza una consulta personalizada con la anotación @Query. La consulta busca un usuario por su nombre de usuario. El signo de interrogación (?) se reemplaza por el valor del parámetro username cuando se ejecuta la consulta.
}
