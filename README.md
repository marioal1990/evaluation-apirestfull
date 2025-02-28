# API de Usuarios - Registro y Login

Este proyecto es una API para registrar y autenticar usuarios, desarrollada con **Spring Boot**. Utiliza una base de datos en memoria **H2**, seguridad con **Spring Security**, y un esquema básico de autenticación para acceder a los endpoints.

---

## Instrucciones para ejecutar el proyecto

1. **Arrancar el proyecto**:
   Ejecuta el siguiente comando en tu terminal:
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Detener el proyecto**:
   Presiona `CTRL + C` en tu terminal.

---

## Información sobre la Base de Datos H2

El proyecto usa la base de datos en memoria **H2** con **Hibernate**. No es necesario configurar manualmente ningún script de base de datos, ya que Hibernate se encarga de la creación automática de tablas.

Si deseas acceder a la consola web de H2, utiliza la siguiente URL en tu navegador:

### Credenciales de acceso a H2:
- **URL de la base de datos**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: *(vacío)*

---

## Seguridad: Credenciales y Roles

El proyecto utiliza **Spring Security** para proteger los endpoints. Existen dos roles definidos con diferentes permisos: **Admin** y **User**.

### Credenciales por defecto:

#### Rol: Admin
- **Usuario**: `admin`
- **Contraseña**: `test123`

Permisos disponibles:
- `registrar` (Registrar usuario)
- `login` (Logear usuario)
- `hearthbeat` (Verificar estado)

#### Rol: User
- **Usuario**: `user`
- **Contraseña**: `test123`

Permisos disponibles:
- `login` (Logear usuario)
- `hearthbeat` (Verificar estado)

---

## Endpoints disponibles

Todos los métodos requieren autenticación con **Basic Authentication**.

### 1. **Registrar un usuario**
**Método HTTP:** `POST`  
**URL:** `http://localhost:8888/api-user/api/v1/user/registro`

#### Cuerpo del JSON de entrada:
```json
{
    "name": "Juan Perez",
    "email": "juan@perez.org",
    "password": "claveSegura123",
    "phones": [
        {
            "number": "123456789",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

#### Respuesta esperada (Ejemplo):
- Código HTTP 201 (Creado)
- JSON con datos del usuario registrado.

---

### 2. **Login de usuario**
**Método HTTP:** `GET`  
**URL:** `http://localhost:8888/api-user/api/v1/user/login`

#### Cuerpo del JSON de entrada:
```json
{
    "email": "juan@perez.org",
    "password": "claveSegura123"
}
```

#### Respuesta esperada (Ejemplo):
- Código HTTP 200 (OK)
- Token JWT si las credenciales son correctas.

---

### 3. **Verificar el estado del controlador (Heartbeat)**
**Método HTTP:** `GET`  
**URL:** `http://localhost:8888/api-user/api/v1/user/hearthbeat`

#### Respuesta esperada:
- Código HTTP 200 (OK)
- Mensaje confirmando el estado del controlador.

---

## Acceder a la documentación OpenAPI (Swagger)

El proyecto incluye documentación interactiva de los endpoints, generada automáticamente con **Springdoc OpenAPI**.

1. Abre tu navegador y accede a la siguiente URL:
   ```
   http://localhost:8888/swagger-ui.html
   ```
2. Desde aquí, puedes probar los endpoints directamente, proporcionando las credenciales necesarias para cada método.

---

## Validaciones de usuarios

El proyecto implementa las siguientes validaciones predefinidas:
1. **Correo electrónico**: Valida que el email tenga un formato correcto.
2. **Contraseña**: La validación de la contraseña es configurable en el archivo `ConstantesUtil.java`. Puedes ajustarla según las necesidades de tu proyecto.

---

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.3.2**
- **Spring Security**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Springdoc OpenAPI**
- **JJWT (JSON Web Tokens)**

---

¡Gracias por usar este proyecto! Si tienes preguntas, no dudes en contactarnos.