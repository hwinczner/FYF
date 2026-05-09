# FYF — Figure Your Future
A career planning REST API that helps users map out their professional future based on roles and jobs they want to achieve.

Built with **Spring Boot**, **PostgreSQL**, and **JWT authentication** — designed to be consumed by a Flutter mobile app.

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Spring Boot 3 |
| Database | PostgreSQL |
| Auth | JWT (jjwt 0.12.3) |
| ORM | Spring Data JPA / Hibernate |
| Migrations | Flyway |
| Build Tool | Maven |
| Frontend (planned) | Flutter |

---

## Getting Started

### Prerequisites
- Java 21+
- PostgreSQL
- Maven

### Setup

1. Clone the repository
```bash
git clone https://github.com/yourusername/fyf.git
cd fyf
```

2. Create a PostgreSQL database
```sql
CREATE DATABASE fyf;
```

3. Create `src/main/resources/application-local.yml` (this file is gitignored):
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fyf
    username: your_postgres_username
    password: your_postgres_password

jwt:
  secret: your-base64-encoded-secret-here
  expiration: 86400000
```

4. Generate a JWT secret:
```bash
openssl rand -base64 32
```

5. Run the application:
```bash
mvn spring-boot:run
```

Flyway will automatically run all migrations and seed data on startup.

---

## Environment Variables

| Variable | Description |
|---|---|
| `JWT_SECRET` | Base64 encoded secret key for signing JWT tokens |
| `DB_URL` | PostgreSQL connection URL |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |

For local development these are set in `application-local.yml`. For production set them as environment variables on your hosting platform.

---

## Authentication

All endpoints except `/auth/**` require a valid JWT token in the `Authorization` header:

```
Authorization: Bearer <token>
```

### Login
```
POST /auth/login
```
Request:
```json
{
    "email": "user@example.com",
    "password": "password123"
}
```
Response:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "userId": "123e4567-e89b-12d3-a456-426614174000",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe"
}
```

Store the `token` and `userId` — the token goes in the `Authorization` header for every subsequent request, and the `userId` is used in endpoint URLs.

---

## API Endpoints

### Users
| Method | Endpoint | Description |
|---|---|---|
| GET | `/users` | Get all users |
| POST | `/users` | Create a new user |
| GET | `/users/search?email=` | Find user by email |

### Plans
| Method | Endpoint | Description |
|---|---|---|
| GET | `/plan/{email}/plans` | Get all plans for a user |
| POST | `/plan/{email}/plans` | Create a new plan |
| PUT | `/plan/{planId}` | Update a plan |
| DELETE | `/plan/{planId}` | Delete a plan |

### Milestones
| Method | Endpoint | Description |
|---|---|---|
| GET | `/plans/{planId}/milestones` | Get all milestones for a plan |
| POST | `/plans/{planId}/milestones` | Add a milestone to a plan |
| PUT | `/milestones/{id}` | Update a milestone |
| PATCH | `/milestones/{id}/status` | Update milestone status only |
| DELETE | `/milestones/{id}` | Delete a milestone |

### Roles
| Method | Endpoint | Description |
|---|---|---|
| GET | `/roles` | Get all roles (paginated) |
| GET | `/roles/{id}` | Get role by ID |
| GET | `/roles/search?q=` | Search roles by title or industry |
| GET | `/roles/{id}/requirements` | Get skills required for a role |

---

## Data Models

### User
```json
{
    "id": "uuid",
    "email": "user@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "createdAt": "2026-01-15T10:30:00",
    "updatedAt": "2026-01-15T10:30:00"
}
```

### Plan
```json
{
    "id": "uuid",
    "title": "Become a Software Engineer",
    "status": "IN_PROGRESS",
    "targetRole": {
        "id": "uuid",
        "title": "Software Engineer"
    },
    "targetDate": "2028-01-15",
    "milestoneCount": 8,
    "milestonesCompleted": 3,
    "overallPercentage": 35,
    "createdAt": "2026-01-15T10:30:00",
    "updatedAt": "2026-04-01T14:22:00"
}
```

### Milestone
```json
{
    "id": "uuid",
    "title": "Learn Java",
    "description": "Complete Java fundamentals course",
    "status": "COMPLETED",
    "dueDate": "2026-03-01",
    "completedAt": "2026-02-28T10:00:00",
    "orderIndex": 1
}
```

### Role
```json
{
    "id": "uuid",
    "title": "Software Engineer",
    "industry": "Technology",
    "seniorityLevel": "MID",
    "demandLevel": "HIGH",
    "averageSalary": {
        "entry": 55000,
        "mid": 85000,
        "senior": 120000
    },
    "requiredExperienceYears": 2,
    "requiredSkills": ["Java", "SQL", "Git"],
    "preferredSkills": ["Docker", "AWS", "Agile"]
}
```

---

## Enums

**PlanStatus:** `NOT_STARTED` `IN_PROGRESS` `COMPLETED` `PAUSED`

**MilestoneStatus:** `NOT_STARTED` `IN_PROGRESS` `COMPLETED`

**DemandLevel:** `LOW` `MEDIUM` `HIGH`

**SeniorityLevel:** `JUNIOR` `MID` `SENIOR` `LEAD`

**EducationLevel:** `NONE` `HIGH_SCHOOL` `BACHELORS` `MASTERS` `PHD`

**SkillLevel:** `BEGINNER` `INTERMEDIATE` `ADVANCED`

---

## Error Responses

All errors return a consistent format:

```json
{
    "status": 404,
    "error": "Not Found",
    "message": "User not found: user@example.com",
    "path": "/users/search",
    "timestamp": "2026-05-05T14:00:00"
}
```

| Status | Error | Cause |
|---|---|---|
| 400 | Bad Request | Missing or invalid fields |
| 401 | Unauthorized | Invalid or missing JWT token |
| 404 | Not Found | Resource doesn't exist |
| 409 | Conflict | Duplicate resource (e.g. email already registered) |
| 500 | Internal Server Error | Unexpected server error |

---

## Database Schema

```
users
  └── plans (one user → many plans)
        └── milestones (one plan → many milestones)

roles
  ├── role_required_skills (many to many → skills)
  ├── role_preferred_skills (many to many → skills)
  └── role_progression (self referencing → next roles)

skills
```

---

## Seeded Data

The database comes pre-seeded with 10 roles and 25 skills via Flyway migrations:

**Roles:** Software Engineer, Data Analyst, Product Manager, UX Designer, DevOps Engineer, Data Scientist, Frontend Developer, Backend Developer, Cybersecurity Analyst, Cloud Architect

**Skill categories:** Programming, Database, Tools, Cloud, Frameworks, Soft Skills, Methodology, Data Science, Design, Infrastructure

---

## Project Structure

```
src/main/java/com/hw/fyf/
├── FyfApplication.java
├── controllers/
│   ├── AuthController.java
│   ├── UserController.java
│   ├── PlansController.java
│   ├── MilestoneController.java
│   └── RoleController.java
├── services/
│   ├── UserDetailsServiceImpl.java
│   ├── JwtService.java
│   ├── PlanService.java
│   ├── MilestoneService.java
│   └── RoleService.java
├── models/
│   ├── User.java
│   ├── Plan.java
│   ├── Milestone.java
│   ├── Role.java
│   └── Skill.java
├── dtos/
│   ├── AuthResponseDTO.java
│   ├── LoginRequest.java
│   ├── CreatePlanRequest.java
│   ├── PlanSummaryDTO.java
│   ├── RoleSummaryDTO.java
│   └── ErrorResponseDTO.java
├── enums/
│   ├── PlanStatus.java
│   ├── MilestoneStatus.java
│   ├── DemandLevel.java
│   ├── SeniorityLevel.java
│   ├── EducationLevel.java
│   └── SkillLevel.java
├── exceptions/
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── ConflictException.java
├── JwtAuth/
│   ├── JwtAuthFilter.java
│   └── JwtService.java
└── config/
    └── SecurityConfig.java

src/main/resources/
├── application.yml
├── application-local.yml  (gitignored)
└── db/migration/
    ├── V1__create_users.sql
    ├── V2__create_plans_and_milestones.sql
    ├── V3__create_roles_and_skills.sql
    └── V4__seed_skills_and_links.sql
```

---

## Planned Features

- [ ] Progress tracking endpoint
- [ ] Skill gap analysis
- [ ] Role recommendations based on user skills
- [ ] Career path generation
- [ ] Dashboard aggregation endpoint
- [ ] Flutter mobile app

---

## License
This project is for personal/educational use.
