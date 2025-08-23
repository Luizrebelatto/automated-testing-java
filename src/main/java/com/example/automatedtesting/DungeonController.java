package com.example.automatedtesting;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/dungeon")
@CrossOrigin(origins = "*")
public class DungeonController {
    
    private final Solution solution = new Solution();
    
    @PostMapping("/calculate-hp")
    public ResponseEntity<DungeonResponse> calculateMinimumHP(@RequestBody DungeonRequest request) {
        try {
            int[][] dungeon = convertToArray(request.getDungeon());

            if (dungeon.length == 0 || dungeon[0].length == 0) {
                return ResponseEntity.badRequest()
                    .body(new DungeonResponse(0, "Dungeon cannot be empty"));
            }
            
            int minimumHP = solution.calculateMinimumHP(dungeon);
            
            return ResponseEntity.ok(new DungeonResponse(minimumHP, "Success"));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DungeonResponse(0, "Error: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Dungeon API is running!");
    }

    private int[][] convertToArray(List<List<Integer>> dungeonList) {
        int rows = dungeonList.size();
        int cols = dungeonList.get(0).size();
        
        int[][] dungeon = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            List<Integer> row = dungeonList.get(i);
            if (row.size() != cols) {
                throw new IllegalArgumentException("All rows must have the same number of columns");
            }
            
            for (int j = 0; j < cols; j++) {
                dungeon[i][j] = row.get(j);
            }
        }
        
        return dungeon;
    }

    public static class DungeonRequest {
        private List<List<Integer>> dungeon;
        
        public List<List<Integer>> getDungeon() {
            return dungeon;
        }
        
        public void setDungeon(List<List<Integer>> dungeon) {
            this.dungeon = dungeon;
        }
    }

    public static class DungeonResponse {
        private int minimumHP;
        private String message;
        
        public DungeonResponse(int minimumHP, String message) {
            this.minimumHP = minimumHP;
            this.message = message;
        }
        
        public int getMinimumHP() {
            return minimumHP;
        }
        
        public void setMinimumHP(int minimumHP) {
            this.minimumHP = minimumHP;
        }
        
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
    }
}
