# Spring Security – Arquitectura y Funcionamiento

<img width="500" height="500" alt="Gemini_Generated_Image_u4hxo8u4hxo8u4hx" src="https://github.com/user-attachments/assets/d4de4a73-5079-4fb5-b356-71371bf23b7d" />

<img width="500" height="500" alt="Gemini_Generated_Image_xokoynxokoynxoko" src="https://github.com/user-attachments/assets/96eb0189-e499-42e2-8b63-7da056f4c278" />


Este proyecto introduce los conceptos fundamentales de **Spring Security**, centrándose en su arquitectura interna y en cómo se gestiona el proceso de autenticación dentro de una aplicación Spring.

---

## 🔐 1. Cadena de Filtros (Security Filter Chain)

Cuando un cliente envía un **HTTP Request** a la aplicación, este no llega directamente al controlador.  
Si Spring Security está activo, la petición pasa primero por la **Security Filter Chain**, una cadena de filtros que evalúa y valida el request paso a paso.

Cada filtro realiza una tarea específica. Si un filtro falla, la petición se detiene.  
Si todos los filtros se ejecutan correctamente, el request continúa hacia el controlador.

Dentro de esta cadena existe un componente clave:

### 🧩 DelegatingFilterProxy
Es un filtro propio de Spring que permite delegar la ejecución a filtros personalizados.  
Spring Security utiliza este mecanismo para permitirnos definir nuestras propias validaciones y reglas de seguridad.

Spring incluye alrededor de **12 filtros por defecto**, además del `DelegatingFilterProxy`.

---

## 🛡️ 2. ¿Cómo trabaja Spring Security internamente?

El corazón del proceso de autenticación es el **Authentication Manager**, encargado de gestionar la autenticación de usuarios.

Este utiliza uno o varios **Authentication Providers**, que son los encargados de validar credenciales según el tipo de autenticación.  
Ejemplos de providers:

- Autenticación contra base de datos  
- Autenticación en memoria  
- Autenticación con servicios externos  
- Autenticación personalizada  

El provider más común es el que valida usuarios almacenados en una base de datos.

---

## 🧱 3. Componentes principales del Authentication Provider

Un provider típico basado en base de datos utiliza dos componentes esenciales:

### 🔑 PasswordEncoder
- Codifica contraseñas antes de almacenarlas.
- Verifica que la contraseña ingresada coincida con la codificada.
- El algoritmo más usado es **BCrypt**.

### 👤 UserDetailsService
- Se conecta a la base de datos (MySQL, PostgreSQL, SQLite, MongoDB, Oracle, etc.).
- Extrae los usuarios y sus datos.
- Devuelve un objeto `UserDetails` para que el provider pueda validar la autenticación.

---

## 🔄 4. Flujo completo de autenticación

Ejemplo: el usuario envía un request con usuario **SANTI** y contraseña **4321**.

1. El request entra en la **Security Filter Chain**.  
2. Al llegar al `DelegatingFilterProxy`, se activa el proceso de autenticación.  
3. El **Authentication Manager** recibe las credenciales.  
4. El Authentication Manager delega en el **Authentication Provider** adecuado.  
5. El provider llama al **UserDetailsService** para obtener el usuario desde la base de datos.  
6. El provider valida la contraseña usando el **PasswordEncoder**.  
7. Si todo es correcto:
   - El Authentication Manager informa al `DelegatingFilterProxy`.
   - El usuario autenticado se registra en el **Security Context Holder**.
8. Si el usuario no existe o la contraseña es incorrecta:
   - Se devuelve un **401 Unauthorized**.

---

## 🗂️ 5. Security Context Holder

Es el lugar donde Spring Security almacena la información del usuario autenticado.

Contiene dos elementos principales:

- **Principal** → Información del usuario (username, password codificada, etc.)
- **Authorities** → Roles y permisos del usuario

Mientras el usuario esté registrado en el Security Context, no se le volverá a pedir autenticación en cada request.

---

## 🛠️ 6. Implementación en Código

Todo lo explicado se plasma en la configuración de Spring Security y en la implementación de:

- Filtros personalizados  
- Providers  
- Servicios `UserDetailsService`  
- Configuración de `PasswordEncoder`  
- Controladores protegidos  

Este proyecto se desarrolla utilizando **Visual Studio Code** como editor principal..

## 📄 Licencia

Este proyecto es de uso educativo y libre para estudio y práctica.




