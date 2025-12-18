# Portfolio - Sistema de Gestión de Portafolio

Aplicación full-stack para la gestión de portafolio de inversiones con modo oscuro/claro y funcionalidad completa de ingresos y egresos.

## Estructura del Proyecto

```
Portfolio/
├── backend/          # Backend Spring Boot (Java)
├── src/             # Frontend React + TypeScript
└── README.md
```

## Tecnologías

### Frontend
- React 18
- TypeScript
- Vite
- Tailwind CSS
- Recharts (gráficos)
- Axios (HTTP client)
- Lucide React (iconos)

### Backend
- Spring Boot 3.2.0
- Java 17
- Spring Data JPA
- H2 Database (desarrollo)
- PostgreSQL (preparado para producción)
- Lombok

## Instalación y Ejecución

### Frontend

1. Instalar dependencias:
```bash
npm install
```

2. Ejecutar en modo desarrollo:
```bash
npm run dev
```

El frontend estará disponible en `http://localhost:3000`

### Backend

1. Asegúrate de tener Java 17 instalado (Maven no es necesario, se usa Maven Wrapper)

2. Navegar a la carpeta backend:
```bash
cd backend
```

3. Compilar y ejecutar (Windows):
```bash
.\mvnw.cmd spring-boot:run
```

O en Linux/Mac:
```bash
./mvnw spring-boot:run
```

**Nota:** La primera vez que ejecutes el wrapper, descargará Maven automáticamente.

El backend estará disponible en `http://localhost:8080`

4. Consola H2 (desarrollo):
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:portfoliodb`
   - Usuario: `sa`
   - Contraseña: (vacía)

## Características

### Frontend
- ✅ Dashboard completo con diseño responsive
- ✅ Modo oscuro/claro con toggle
- ✅ Tarjetas de resumen (Dinero Ingresado, Líquido, Invertido, Rendimiento, Valor Total)
- ✅ Lista de activos con detalles
- ✅ Gráfico de distribución del portafolio (donut chart)
- ✅ Historial de ventas
- ✅ Modal para ingresar/retirar dinero
- ✅ Integración con API backend

### Backend
- ✅ API REST completa
- ✅ Endpoints para obtener resumen, activos, ventas y distribución
- ✅ Endpoints para ingresar y retirar dinero
- ✅ Estructura preparada para base de datos
- ✅ Modelos JPA (Asset, SoldAsset, Transaction, PortfolioSummary)
- ✅ Repositorios Spring Data JPA
- ✅ CORS configurado
- ✅ Datos de ejemplo inicializados

## Endpoints API

- `GET /api/portfolio/summary` - Obtener resumen del portafolio
- `GET /api/portfolio/assets` - Obtener lista de activos
- `GET /api/portfolio/sold-assets` - Obtener historial de ventas
- `GET /api/portfolio/distribution` - Obtener distribución del portafolio
- `POST /api/portfolio/deposit` - Ingresar dinero
- `POST /api/portfolio/withdraw` - Retirar dinero

## Configuración de Base de Datos

### Desarrollo (H2 - actual)
La aplicación está configurada para usar H2 en memoria. Los datos se inicializan automáticamente con ejemplos.

### Producción (PostgreSQL)
Para cambiar a PostgreSQL:

1. Descomentar las líneas en `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/portfoliodb
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

2. Comentar las líneas de H2

3. Crear la base de datos PostgreSQL:
```sql
CREATE DATABASE portfoliodb;
```

## Próximos Pasos

- [ ] Autenticación y autorización
- [ ] Validaciones adicionales
- [ ] Tests unitarios e integración
- [ ] Migración a PostgreSQL
- [ ] Funcionalidad para agregar/editar/eliminar activos
- [ ] Funcionalidad para vender activos
- [ ] Actualización automática de precios
- [ ] Exportación de reportes

## Licencia

MIT
