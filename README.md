[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=anagranada1_ArquiSoft-Grupo-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=anagranada1_ArquiSoft-Grupo-1)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=anagranada1_ArquiSoft-Grupo-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=anagranada1_ArquiSoft-Grupo-1)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=anagranada1_ArquiSoft-Grupo-1&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=anagranada1_ArquiSoft-Grupo-1)

# Parcial Práctico de Arquitectura de Software - Grupo 1

## 👥 Equipo

Proyecto del **Grupo 1**.
* **Amaris Dominguez Leonardo Jose**
* **Buelvas Martínez Valentina**
* **Correa Rios Simon**
* **Granada Rodas Ana Maria**
* **Ramos Velez Juan Pablo**
* **Torres Quintero Jesus Estiven**

---

Aplicación full-stack para **registrar y consultar información del clima** por **país** y **ciudad**.

- **Backend:** Spring Boot (Java 21), REST API, MongoDB, HATEOAS, Swagger/OpenAPI
- **Frontend:** React (CRA) + Axios
- **Infra:** Docker + docker-compose

---

## 📦 Tecnologías

### Backend
- Java **21**
- Spring Boot
- Spring Web
- Spring Data MongoDB
- Spring HATEOAS
- SpringDoc OpenAPI (Swagger UI)

### Base de datos
- MongoDB (DB: `weatherdb`, colección: `climas`)

### Frontend
- React + react-scripts
- Axios
- Tailwind (dependencias instaladas)

---

## 🧭 Arquitectura (backend)

Estructura típica por capas:

- `controller` → expone endpoints REST (`/api/v1/clima`)
- `service` → lógica de negocio (asigna `registrationDate`, persistencia)
- `repository` → acceso a MongoDB con `MongoRepository`

---

## ✅ Requisitos

Para correr local sin Docker:
- JDK 21
- Maven
- Node.js + npm

Para correr con Docker:
- Docker
- Docker Compose

---

## 🚀 Ejecución rápida con Docker (recomendado)

> El `Dockerfile` espera un `.jar` en `target/`, así que primero compila el backend.

1) Compilar el backend:
```bash
mvn clean package -DskipTests
````

2. Levantar API + MongoDB:

```bash
docker-compose up --build
```

* API: `http://localhost:8080`
* Swagger UI: `http://localhost:8080/swagger-ui.html`
* OpenAPI JSON: `http://localhost:8080/api-docs`
* MongoDB: `localhost:27017` (usuario `admin`, pass `password123`)

---

## 🧪 Ejecución local (sin Docker)

### 1) Levantar MongoDB

Puedes usar Docker solo para Mongo:

```bash
docker-compose up -d mongo
```

### 2) Backend

```bash
mvn spring-boot:run
# o:
java -jar target/*.jar
```

### 3) Frontend

```bash
cd frontend
npm install
npm start
```

Frontend: `http://localhost:3000`

---

## 🔌 API (endpoints)

Base path:

```
/api/v1/clima
```

### ✅ Crear registro de clima

**POST** `/api/v1/clima`

Ejemplo:

```bash
curl -X POST "http://localhost:8080/api/v1/clima" \
  -H "Content-Type: application/json" \
  -d '{
    "country": "Colombia",
    "city": "Medellín",
    "temperature": 23.5,
    "feelsLike": 24.1,
    "humidity": 60,
    "description": "Parcialmente nublado",
    "windSpeed": 3.2
  }'
```

📌 La respuesta incluye links HATEOAS (`_links`) para acciones relacionadas.

---

### 🔎 Buscar clima por país y ciudad

**GET** `/api/v1/clima/buscar?country=...&city=...`

Ejemplo:

```bash
curl "http://localhost:8080/api/v1/clima/buscar?country=Colombia&city=Medell%C3%ADn"
```

---

## ⚙️ Configuración

El backend usa la propiedad:

* `spring.data.mongodb.uri`

En `docker-compose.yml` se inyecta como variable de entorno:

* `SPRING_DATA_MONGODB_URI=mongodb://admin:password123@mongo:27017/weatherdb?authSource=admin`

Swagger:

* UI: `/swagger-ui.html`
* Docs: `/api-docs`

---

## 🧩 Estructura del repositorio (alto nivel)

```
.
├── src/                 # Backend (Spring Boot)
├── frontend/            # Frontend (React)
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

---

## 🛠️ Notas / troubleshooting

* Si vas a consumir la API desde el frontend, asegúrate de usar el endpoint de búsqueda correcto:

  * ✅ `/api/v1/clima/buscar?country=...&city=...`
* El backend valida que `country` y `city` no sean nulos al crear registros.
* Si aparece error de CORS en el navegador, puedes habilitar CORS en el controller o configurar un `WebMvcConfigurer`.

---

## 🧾 Scripts (frontend)

Dentro de `frontend/`:

```bash
npm start    # desarrollo
npm run build
npm test
```

---
