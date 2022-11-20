package Test;


//import Move;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Modele.Move;
import Modele.Zen;



public class TestModele {

	int[][] aMove;
	Zen aZen;
	int[][] aZenMove;
	int[][] aTab;
	
	 @Before()
	 public void setUp() {
		 
		 int[][] tab =  {{1 ,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,-1},//0
						{0 ,0 ,0 ,0 ,-1,0 ,-1,0 ,0 ,0 ,0 },//1
						{0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 },//2
						{0 ,0 ,-1,0 ,0 ,0 ,0 ,0 ,-1,0 ,0 },//3
						{0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 },//4
						{-1,0 ,0 ,0 ,0 ,2 ,0 ,0 ,0 ,0 ,-1},//5
						{0 ,1 ,0 ,0 ,0 ,0 ,0 ,0 ,0 ,1 ,0 },//6
						{0 ,0 ,-1,0 ,0 ,0 ,0 ,0 ,-1,0 ,0 },//7 c 3 l 8
						{0 ,0 ,0 ,1 ,0 ,0 ,0 ,1 ,0 ,0 ,0 },//8
						{0 ,0 ,0 ,0 ,-1,0 ,-1,0 ,0 ,0 ,0 },//9
						{-1,0 ,0 ,0 ,0 ,1 ,0 ,0 ,0 ,0 ,1 } //10
					}; //0 ,1 ,2 ,3 ,4 ,5 ,6 ,7 ,8 ,9 ,10	 
		 
		 
		this.aTab = tab;
		aMove = Move.getMove(tab, 5, 1, 1);
		aZen = new Zen();
		aZenMove = aZen.mZenMove(tab, aMove, 15);
	 }


	 
	 @Test()
	  public void testMoveLeft() {		 
		 assertEquals(aMove[0][0],3);
		 assertEquals(aMove[0][1],1);
		 assertEquals(aMove[0][2],1);
		 
	
	  }
	 
	 @Test()
	  public void testMoveRight() {

		 assertEquals(aMove[1][0],7);
		 assertEquals(aMove[1][1],1);
		 assertEquals(aMove[1][2],1);		 
		 
	  }
	 
	 @Test()
	  public void testMoveTop() {

		 
		 assertEquals(aMove[2][0],5);
		 assertEquals(aMove[2][1],4);
		 assertEquals(aMove[2][2],1);	
	
	  }
	 
	 @Test()
	  public void testMoveBot() {
		 assertEquals(aMove[3][0],5);
		 assertEquals(aMove[3][1],-2);
		 assertEquals(aMove[3][2],4);	
	  }
	 
	 @Test()
	  public void testMoveLeftTop() {
		 assertEquals(aMove[4][0],5);
		 assertEquals(aMove[4][1],1);
		 assertEquals(aMove[4][2],1);	
	  }
	 
	 @Test()
	  public void testMoveRightBot() {
		 assertEquals(aMove[5][0],5);
		 assertEquals(aMove[5][1],1);
		 assertEquals(aMove[5][2],1);	
	  }
	 
	 
	 @Test()
	  public void testMoveRightTop() {
		 assertEquals(aMove[6][0],5);
		 assertEquals(aMove[6][1],1);
		 assertEquals(aMove[6][2],1);	
	  }
	 
	 @Test()
	  public void testMoveBotLeft() {
		 assertEquals(aMove[7][0],5);
		 assertEquals(aMove[7][1],1);
		 assertEquals(aMove[7][2],1);	
	  }

	 @Test()
	 public void testZenMove() 
	 {
		 assertEquals(aZenMove[0][0],1);
		 assertEquals(aZenMove[0][1],8);
		 assertEquals(aZenMove[0][2],1);
		 
		 assertEquals(aZenMove[1][1],1);
		 assertEquals(aZenMove[1][2],3);
		 assertEquals(aZenMove[1][3],1);
		 
	 }

	 @Test()
	 public void testZenCanMove() 
	 {
		 assertEquals(aZen.mZenCanMove(null),false);
		 assertEquals(aZen.mZenCanMove(this.aTab),true);
	 }

}
