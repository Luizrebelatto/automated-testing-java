# Dungeon HP Calculator API

This project provides a REST API to calculate the minimum HP required to complete a dungeon game. The algorithm uses dynamic programming to find the optimal path through a 2D dungeon grid.

## Features

- **Java 23** implementation of the dungeon HP calculation algorithm
- **REST API** with Spring Boot
- **Comprehensive testing** with JUnit 5
- **Input validation** and error handling

## Algorithm

The `calculateMinimumHP` method uses a bottom-up dynamic programming approach:

1. Start from the bottom-right corner (destination)
2. Work backwards to the top-left corner (starting position)
3. For each cell, calculate the minimum HP needed to survive
4. Ensure HP never drops below 1 at any point

## API Endpoints

### Calculate Minimum HP
```
POST /api/dungeon/calculate-hp
```

**Request Body:**
```json
{
  "dungeon": [
    [-2, -3, 3],
    [-5, -10, 1],
    [10, 30, -5]
  ]
}
```

**Response:**
```json
{
  "minimumHP": 7,
  "message": "Success"
}
```

### Health Check
```
GET /api/dungeon/health
```

**Response:** `"Dungeon API is running!"`

## Running the Application

1. **Build the project:**
   ```bash
   ./gradlew build
   ```

2. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

3. **Run tests:**
   ```bash
   ./gradlew test
   ```

## Example Usage

### Using curl:
```bash
curl -X POST http://localhost:8080/api/dungeon/calculate-hp \
  -H "Content-Type: application/json" \
  -d '{
    "dungeon": [
      [-2, -3, 3],
      [-5, -10, 1],
      [10, 30, -5]
    ]
  }'
```

### Using JavaScript:
```javascript
const response = await fetch('/api/dungeon/calculate-hp', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({
    dungeon: [
      [-2, -3, 3],
      [-5, -10, 1],
      [10, 30, -5]
    ]
  })
});

const result = await response.json();
console.log(`Minimum HP required: ${result.minimumHP}`);
```

## Dungeon Format

- The dungeon is represented as a 2D array of integers
- Positive values represent healing rooms
- Negative values represent damage rooms
- The player must move from top-left to bottom-right
- Movement is only allowed down or right
- HP must never drop below 1

## Technical Details

- **Java Version:** 23
- **Framework:** Spring Boot 3.5.4
- **Build Tool:** Gradle with Kotlin DSL
- **Testing:** JUnit 5

## Error Handling

The API includes comprehensive error handling:
- Invalid dungeon dimensions
- Empty dungeon arrays
- Malformed input data
- Internal calculation errors

All errors return appropriate HTTP status codes and descriptive error messages.
