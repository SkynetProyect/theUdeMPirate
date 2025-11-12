# UP STUDY

### Descripción general del sistema

**UP STUDY** es una aplicación web que permite a estudiantes y profesores compartir, consultar y filtrar documentos académicos en formato PDF. La idea principal es crear una especie de biblioteca colaborativa o “pirate bay académica”, donde cualquier usuario pueda subir y leer documentos, pero solo los administradores puedan modificar materias y profesores.

El sistema está compuesto por tres partes principales:

* **Frontend (Angular)**: Interfaz que permite al usuario ver, filtrar y subir documentos.
* **Backend (Spring Boot, arquitectura de 4 capas)**: Gestiona la lógica de negocio, las pruebas unitarias y la conexión con la base de datos.
* **Base de datos (PostgreSQL)**: Almacena la información de los documentos, autores, categorías y etiquetas.

Todo se orquesta mediante **Docker Compose**, que levanta los servicios con un volumen persistente para guardar los archivos PDF (máximo 20 MB por archivo).

### Arquitectura general del sistema

La aplicación funciona con una arquitectura de tres capas desplegada en contenedores Docker.

**Estructura general:**

1. El **usuario** interactúa con el **frontend Angular**.
2. El frontend se comunica con el **backend en Spring Boot** mediante peticiones HTTP.
3. El backend gestiona la información y se conecta a **PostgreSQL**, que guarda los datos de documentos, autores, categorías y etiquetas.

**Usuarios:**

* Subir archivo
* Modificar archivo
* Descargar archivo
* Filtrar por materia
* Filtrar por profesor
* Ver lista de documentos

**Administradores:**

* Modificar materias
* Modificar profesores

### Instrucciones de despliegue local con Minikube

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/SkynetProyect/theUdeMPirate.git
   cd up-study
   ```
2. Iniciar Minikube

3. Aplicar los manifiestos de Kubernetes

4. Verificar los pods y servicios

5. Abrir el servicio del frontend

### Docker Compose

El proyecto cuenta con un `docker-compose.yml` que levanta:

* El **backend** (Spring Boot)
* El **frontend** (Angular)
* La **base de datos PostgreSQL**
* Un **volumen persistente** para los documentos

Para ejecutar todo el entorno con un solo comando:

```bash
docker-compose up -d
```

### Pipeline CI/CD

El flujo de integración y despliegue continuo se realiza con **GitHub Actions**:

1. Un desarrollador hace `commit` y `push` al repositorio.
2. El pipeline ejecuta las siguientes tareas:

   * Compila el proyecto.
   * Ejecuta las pruebas unitarias.
   * Construye las imágenes Docker del backend y frontend.
   * Publica las imágenes en Docker Hub.
3. Finalmente, despliega los servicios usando `docker-compose up -d`, manteniendo el volumen persistente para no perder los PDFs.

📎 *Inserta aquí el enlace al pipeline en GitHub Actions*

### Enlaces

* **Docker Hub (Backend)** → [Enlace al DockerHub del backend](https://hub.docker.com/repository/docker/leninospina/upstudy-backend/general)
* **Docker Hub (Frontend)** → [Enlace al DockerHub del frontend](https://hub.docker.com/repository/docker/leninospina/upstudy-frontend/general)
---

### Equipo de trabajo

| Rol            | Nombre                            |
| -------------- | --------------------------------- |
| Desarrolladora | **Ana María Alucema**             |
| Desarrollador  | **Lenin Ospina**                  |
| Desarrolladora | **Juana Valentina Rincón Granda** |
| Desarrollador  | **Santiago Restrepo**             |