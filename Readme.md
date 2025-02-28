# Microservicios: ms-core-persona-cliente y ms-core-fin-cuentas-movimientos

Este proyecto contiene dos microservicios desarrollados con Spring Boot: `ms-core-persona-cliente` para la gestión de clientes y `ms-core-fin-cuentas-movimientos` para la gestión de cuentas y movimientos financieros. Ambos usan Kafka para comunicación asíncrona y PostgreSQL como base de datos.

## Requisitos previos
- **Java 17**: Instalado localmente o en los contenedores Docker.
- **Maven**: Para construir los proyectos.
- **Docker y Docker Compose**: Para ejecutar los microservicios y dependencias (PostgreSQL, Kafka, Zookeeper).
- **Postman o cURL**: Para probar los endpoints.

## Configuración inicial
1. **Clonar el repositorio**:
   ```bash
   git clone <repositorio>
   cd <repositorio>
```
Construir los proyectos:
bash

mvn clean install

Levantar los servicios con Docker:
bash

docker-compose up -d

Verificar logs:
bash

docker-compose logs ms-core-persona-cliente
docker-compose logs ms-core-fin-cuentas-movimientos

Endpoints
Microservicio: ms-core-persona-cliente
Puerto: 8080
Base URL: http://localhost:8080
1. Obtener todos los clientes
   Método: GET

Endpoint: /clientes/getAll

Descripción: Retorna una lista de todos los clientes registrados.

Parámetros: Ninguno

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": [
{
"clienteId": 1,
"nombres": "Juan Pérez",
"genero": "MASCULINO",
"edad": 30,
"identificacion": "1717171712",
"direccion": "Calle Falsa 123, Quito",
"telefono": "0987654321",
"contrasenia": "securePass123",
"estado": true
}
]
}

Ejemplo con cURL:
bash

curl http://localhost:8080/clientes/getAll

2. Obtener clientes por estado
   Método: GET

Endpoint: /clientes/getAllByEstado

Descripción: Retorna una lista de clientes filtrada por estado (true o false).

Parámetros:
estado (query param, requerido): "true" o "false"

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": [
{
"clienteId": 1,
"nombres": "Juan Pérez",
"genero": "MASCULINO",
"edad": 30,
"identificacion": "1717171712",
"direccion": "Calle Falsa 123, Quito",
"telefono": "0987654321",
"contrasenia": "securePass123",
"estado": true
}
]
}

Ejemplo con cURL:
bash

curl http://localhost:8080/clientes/getAllByEstado?estado=true

3. Obtener un cliente por ID
   Método: GET

Endpoint: /clientes/{id}

Descripción: Retorna un cliente específico por su clienteId.

Parámetros:
id (path variable, requerido): ID del cliente (Long)

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": {
"clienteId": 1,
"nombres": "Juan Pérez",
"genero": "MASCULINO",
"edad": 30,
"identificacion": "1717171712",
"direccion": "Calle Falsa 123, Quito",
"telefono": "0987654321",
"contrasenia": "securePass123",
"estado": true
}
}

Error esperado (si no existe):
json

{
"status": 404,
"message": "No se encontró el cliente con el id: 999",
"payload": null
}

Ejemplo con cURL:
bash

curl http://localhost:8080/clientes/1

4. Crear un cliente
   Método: POST

Endpoint: /clientes/create

Descripción: Crea un nuevo cliente.

Cuerpo de la solicitud (JSON):
json

{
"nombres": "Ana Gómez",
"genero": "FEMENINO",
"edad": 28,
"identificacion": "1723456785",
"direccion": "Av. Nueva 789",
"telefono": "0971234567",
"contrasenia": "pass789"
}

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": {
"clienteId": 4,
"nombres": "Ana Gómez",
"genero": "FEMENINO",
"edad": 28,
"identificacion": "1723456785",
"direccion": "Av. Nueva 789",
"telefono": "0971234567",
"contrasenia": "pass789",
"estado": true
}
}

Ejemplo con cURL:
bash

curl -X POST http://localhost:8080/clientes/create -H "Content-Type: application/json" -d '{"nombres":"Ana Gómez","genero":"FEMENINO","edad":28,"identificacion":"1723456785","direccion":"Av. Nueva 789","telefono":"0971234567","contrasenia":"pass789"}'

5. Actualizar un cliente
   Método: PUT

Endpoint: /clientes/update

Descripción: Actualiza un cliente existente (debe incluir clienteId).

Cuerpo de la solicitud (JSON):
json

{
"clienteId": 1,
"nombres": "Juan Pérez Actualizado",
"genero": "MASCULINO",
"edad": 31,
"identificacion": "1717171712",
"direccion": "Calle Nueva 456",
"telefono": "0987654321",
"contrasenia": "newPass123",
"estado": true
}

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": {
"clienteId": 1,
"nombres": "Juan Pérez Actualizado",
"genero": "MASCULINO",
"edad": 31,
"identificacion": "1717171712",
"direccion": "Calle Nueva 456",
"telefono": "0987654321",
"contrasenia": "newPass123",
"estado": true
}
}

Ejemplo con cURL:
bash

curl -X PUT http://localhost:8080/clientes/update -H "Content-Type: application/json" -d '{"clienteId":1,"nombres":"Juan Pérez Actualizado","genero":"MASCULINO","edad":31,"identificacion":"1717171712","direccion":"Calle Nueva 456","telefono":"0987654321","contrasenia":"newPass123","estado":true}'

6. Eliminar un cliente (lógica)
   Método: DELETE

Endpoint: /clientes/{id}

Descripción: Realiza una eliminación lógica cambiando el estado del cliente a false.

Parámetros:
id (path variable, requerido): ID del cliente (Long)

Respuesta esperada: No retorna contenido (204 No Content implícito por void), pero el estado del cliente y sus cuentas asociadas cambiará a false.

Ejemplo con cURL:
bash

curl -X DELETE http://localhost:8080/clientes/1

Microservicio: ms-core-fin-cuentas-movimientos
Puerto: 8081
Base URL: http://localhost:8081
1. Obtener todas las cuentas
   Método: GET

Endpoint: /cuentas/getAll

Descripción: Retorna una lista de todas las cuentas registradas.

Parámetros: Ninguno

Respuesta esperada:
json

{
"status": 200,
"message": "Success",
"payload": [
{
"numCuenta": "1234567890",
"tipoCuenta": "AHORRO",
"saldoInicial": 500.00,
"estado": true,
"clienteId": 1
}
]
}

Ejemplo con cURL:
bash

curl http://localhost:8081/cuentas/getAll

2. Obtener una cuenta por número
   Método: GET

Endpoint: /cuentas/{numCuenta}

Descripción: Retorna una cuenta específica por su número.

Parámetros:
numCuenta (path variable, requerido): Número de cuenta (String)

Respuesta esperada:
json

{
"numCuenta": "1234567890",
"tipoCuenta": "AHORRO",
"saldoInicial": 500.00,
"estado": true,
"clienteId": 1
}

Ejemplo con cURL:
bash

curl http://localhost:8081/cuentas/1234567890

3. Crear una cuenta
   Método: POST

Endpoint: /cuentas/create

Descripción: Crea una nueva cuenta.

Cuerpo de la solicitud (JSON):
json

{
"tipoCuenta": "AHORRO",
"saldoInicial": 500.00,
"estado": true,
"clienteId": 1
}

Respuesta esperada:
json

{
"numCuenta": "1234567890",
"tipoCuenta": "AHORRO",
"saldoInicial": 500.00,
"estado": true,
"clienteId": 1
}

Ejemplo con cURL:
bash

curl -X POST http://localhost:8081/cuentas/create -H "Content-Type: application/json" -d '{"tipoCuenta":"AHORRO","saldoInicial":500.00,"estado":true,"clienteId":1}'

4. Generar un reporte
   Método: GET

Endpoint: /reportes/reporte

Descripción: Genera un reporte de cuentas y movimientos para un cliente en un rango de fechas.

Parámetros:
clienteId (query param, requerido): ID del cliente (Long)

fechaInicio (query param, requerido): Fecha inicial (formato yyyy-MM-dd)

fechaFin (query param, requerido): Fecha final (formato yyyy-MM-dd)

tipoReporte (query param, requerido): Nombre del bean de la estrategia (e.g., movementsByAccountAndDateRangeStrategy)

Respuesta esperada:
json

{
"status": 200,
"message": "Reporte generado con éxito",
"payload": {
"clienteId": 1,
"cuentas": [
{
"cuenta": {
"numCuenta": "1234567890",
"tipoCuenta": "AHORRO",
"saldoInicial": 500.00,
"estado": true,
"clienteId": 1
},
"saldoActual": 700.00,
"movimientos": [
{
"id": 1,
"fecha": "2025-01-15T10:00:00",
"tipoMovimiento": "CREDITO 200.00",
"valor": 200.00,
"saldo": 700.00,
"cuentaNum": "1234567890"
}
]
}
]
}
}

Ejemplo con cURL:
bash

curl http://localhost:8081/reportes/reporte?clienteId=1&fechaInicio=2025-01-01&fechaFin=2025-03-31&tipoReporte=movementsByAccountAndDateRangeStrategy

5. Crear un movimiento (supuesto, basado en contexto previo)
   Método: POST

Endpoint: /movimientos

Descripción: Crea un nuevo movimiento financiero para una cuenta.

Cuerpo de la solicitud (JSON):
json

{
"cuentaNum": "1234567890",
"valor": 200.00
}

Respuesta esperada: Varía según implementación, asumimos un 200 OK con el movimiento creado.

Ejemplo con cURL:
bash

curl -X POST http://localhost:8081/movimientos -H "Content-Type: application/json" -d '{"cuentaNum":"1234567890","valor":200.00}'

