# NeuralChef

Eine KI-gestützte Koch-Plattform mit automatischer Nährwertanalyse.

## Funktionen

- **Rezeptverwaltung**: Erstellen, Bearbeiten, Löschen und Suchen von Rezepten
- **Automatische Nährwertanalyse**: Berechnung von Kalorien, Proteinen, Kohlenhydraten, Fetten und weiteren Nährwerten
- **Intelligente Zutatenerkennung**: Automatische Erkennung und Kategorisierung von Zutaten
- **REST API**: Vollständige RESTful API für alle Funktionen

## Technologie-Stack

- **Backend**: Spring Boot 3.4.5
- **Datenbank**: PostgreSQL
- **Java**: 17
- **Build Tool**: Maven
- **ORM**: Spring Data JPA / Hibernate

## Voraussetzungen

- Java 17 oder höher
- Maven 3.6+
- Docker & Docker Compose (für die Datenbank)

## Installation und Start

### 1. Repository klonen

```bash
git clone <repository-url>
cd neuralchef
```

### 2. Datenbank starten

```bash
docker-compose up -d
```

Dies startet:
- PostgreSQL auf Port 5432
- pgAdmin auf Port 5050 (http://localhost:5050)

### 3. Anwendung bauen und starten

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Die Anwendung ist nun unter `http://localhost:8080` erreichbar.

## API Endpoints

### Rezepte

| Methode | Endpoint | Beschreibung |
|---------|----------|--------------|
| POST | `/api/recipes` | Neues Rezept erstellen |
| GET | `/api/recipes` | Alle Rezepte abrufen |
| GET | `/api/recipes/{id}` | Rezept nach ID abrufen |
| GET | `/api/recipes?title={title}` | Rezepte nach Titel suchen |
| GET | `/api/recipes?difficulty={difficulty}` | Rezepte nach Schwierigkeit filtern |
| PUT | `/api/recipes/{id}` | Rezept aktualisieren |
| DELETE | `/api/recipes/{id}` | Rezept löschen |
| GET | `/api/recipes/{id}/nutrition` | Nährwertinformationen abrufen |

### Beispiel: Rezept erstellen

```bash
curl -X POST http://localhost:8080/api/recipes \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Spaghetti Carbonara",
    "description": "Klassisches italienisches Pasta-Gericht",
    "servings": 4,
    "preparationTime": 10,
    "cookingTime": 20,
    "instructions": "1. Pasta kochen\n2. Speck anbraten\n3. Eier mit Parmesan vermengen\n4. Alles zusammen mischen",
    "difficulty": "MITTEL",
    "ingredients": [
      {"name": "Spaghetti", "quantity": 400, "unit": "g"},
      {"name": "Eier", "quantity": 4, "unit": "Stück"},
      {"name": "Parmesan", "quantity": 100, "unit": "g"},
      {"name": "Speck", "quantity": 150, "unit": "g"}
    ]
  }'
```

## Schwierigkeitsgrade

- `EINFACH`: Einfache Rezepte für Anfänger
- `MITTEL`: Rezepte mit mittlerem Schwierigkeitsgrad
- `SCHWER`: Fortgeschrittene Rezepte

## Nährwertberechnung

Die Anwendung berechnet automatisch folgende Nährwerte pro Portion:
- Kalorien (kcal)
- Protein (g)
- Kohlenhydrate (g)
- Fett (g)
- Ballaststoffe (g)
- Zucker (g)
- Natrium (mg)

## Entwicklung

### Tests ausführen

```bash
./mvnw test
```

### Anwendung neu bauen

```bash
./mvnw clean package
```

## Datenbank-Zugriff

### pgAdmin
- URL: http://localhost:5050
- Email: admin@neuralchef.com
- Passwort: admin

### PostgreSQL
- Host: localhost
- Port: 5432
- Datenbank: neuralchef
- Benutzer: postgres
- Passwort: postgres

## Projektstruktur

```
src/main/java/com/devportfolio/neuralchef/
├── controller/       # REST Controllers
├── service/          # Business Logic
├── repository/       # Datenbankzugriff
├── model/           # Domain-Modelle (Entities)
├── dto/             # Data Transfer Objects
├── mapper/          # Konvertierung zwischen Entities und DTOs
└── exception/       # Exception-Handling
```

## Lizenz

[Lizenz hier einfügen]

## Autor

[Autor hier einfügen] 
