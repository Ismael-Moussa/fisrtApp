# Application de gestion de clients

Ce dépôt contient une application complète de gestion de clients avec :
- **Backend** Spring Boot (API REST)
- **Frontend** Angular (interface web)

## Backend (Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

API disponible sur :
- `http://localhost:8080/api/clients`
- `http://localhost:8080/api/users`
- `http://localhost:8080/api/roles`

## Frontend (Angular)

```bash
cd frontend
npm install
npm start
```

L'application sera disponible sur `http://localhost:4200` et utilise un proxy vers le backend.
