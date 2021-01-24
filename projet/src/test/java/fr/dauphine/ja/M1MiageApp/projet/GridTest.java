package fr.dauphine.ja.M1MiageApp.projet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.dauphine.ja.M1MiageApp.projet.Grid.Result;

public class GridTest{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */

	final int[] basePoints = {120, 72, 72, 975, 513, 513, 975, 72, 72, 120};
	
	@Test
    public void newGameTest(){
        Grid grid = new Grid(35, 9, "5D");
        for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++)
				if ((basePoints[r] & (1 << c)) != 0)
					assertTrue(grid.points[20 + r][20 + c] == 1);
        
    }
	@Test
	public void computerMoveTest() {
		Grid grid = new Grid(35, 9, "5D");
		assertEquals(grid.computerMove(200, 200, 0), Result.KO);
		assertEquals(grid.computerMove(23, 19, 0), Result.OK);
	}
	
	@Test
	public void playerMoveTest() {
		Grid grid = new Grid(35, 9, "5D");
		assertEquals(grid.playerMove(20, 23, 30), Result.KO);
		assertEquals(grid.playerMove(26, 19, 0), Result.OK);
	}
	
	
    

}
