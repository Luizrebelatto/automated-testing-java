package com.example.automatedtesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    
    private Solution solution;
    
    @BeforeEach
    void setUp() {
        solution = new Solution();
    }
    
    @Test
    void testBasicDungeon() {
        int[][] dungeon = {
            {-2, -3, 3},
            {-5, -10, 1},
            {10, 30, -5}
        };
        
        int result = solution.calculateMinimumHP(dungeon);
        assertEquals(7, result);
    }
    
    @Test
    void testSingleCellDungeon() {
        int[][] dungeon = {{-5}};
        
        int result = solution.calculateMinimumHP(dungeon);
        assertEquals(6, result);
    }
    
    @Test
    void testPositiveDungeon() {
        int[][] dungeon = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        int result = solution.calculateMinimumHP(dungeon);
        assertEquals(1, result);
    }
    
    @Test
    void testNegativeDungeon() {
        int[][] dungeon = {
            {-1, -2, -3},
            {-4, -5, -6},
            {-7, -8, -9}
        };
        
        int result = solution.calculateMinimumHP(dungeon);
        assertEquals(25, result);
    }
    
    @Test
    void testTwoByTwoDungeon() {
        int[][] dungeon = {
            {-1, -2},
            {-3, -4}
        };
        
        int result = solution.calculateMinimumHP(dungeon);
        assertEquals(8, result);
    }
}
