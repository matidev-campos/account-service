# Account Service

Microservicio de **Cuentas y Movimientos** desarrollado como parte del challenge técnico.

Este servicio es responsable de:

* Gestión de cuentas
* Registro de movimientos
* Validación de reglas de negocio
* Consumo de eventos asincrónicos provenientes de `client-service`

---

## 🧱 Arquitectura

* Java 17
* Spring Boot 3
* Spring Data JPA
* PostgreSQL
* RabbitMQ (mensajería asincrónica)
* Docker / Docker Compose

Arquitectura basada en **microservicios desacoplados**, comunicados mediante eventos.

---

## 📦 Responsabilidades del servicio

### Account

* Crear cuentas
* Consultar cuentas
* Eliminar cuentas
* Mantener estado y saldo

### Movement

* Registrar movimientos (depósitos / retiros)
* Actualizar saldo
* Listar movimientos por cuenta

---

---

## 🔁 Comunicación asincrónica

El servicio consume eventos publicados por `client-service` a través de RabbitMQ:

* `client.created`
* `client.deleted`

Estos eventos permiten mantener sincronizado el estado de las cuentas sin acoplar servicios.

### Importante

RabbitMQ **no corre embebido** dentro del microservicio.

* En **producción / staging** corre como infraestructura externa
* En **local** puede levantarse opcionalmente con Docker
* Si RabbitMQ no está disponible, el servicio sigue funcionando

Esto garantiza:

* Bajo acoplamiento
* Alta resiliencia
* Eventual consistency

---

## 🐳 Ejecución con Docker (opcional)

```bash
docker-compose up -d
```

Servicios incluidos:

* PostgreSQL
* RabbitMQ (opcional para pruebas de eventos)

---

## ▶️ Ejecución local

```bash
./mvnw spring-boot:run
```

El servicio quedará disponible en:

```
http://localhost:8080
```


