/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * A program will win a battleship game in the fewest amounts of shots
 * store
 * @author  Andy Le
 */
import battleship.BattleShip;

/**
 * Starting code for COMP10205 - Assignment#6
 * @author mark.yendt
 */

public class A6
{
  // In general do not change code here
   static final int NUMBEROFGAMES = 33; //On hand-in set to 10000
   public static void startingSolution()
  {
    int totalShots = 0;
    System.out.println(BattleShip.version());

    long startTime = System.nanoTime();
    //Must leave loop as is
    for (int game = 0; game < NUMBEROFGAMES; game++) {

      BattleShip battleShip = new BattleShip();
      SampleBot sampleBot = new SampleBot(battleShip);
      
      // Call SampleBot Fire randomly - You need to make this better!
      while (!battleShip.allSunk()) { //Leave alone
        sampleBot.fireShot();
      }
      int gameShots = battleShip.totalShotsTaken();
      totalShots += gameShots;
    }
    long stopTime = System.nanoTime();

    System.out.printf("SampleBot - The Average # of Shots required in %d games to sink all Ships = %.2f\n", NUMBEROFGAMES, (double)totalShots / NUMBEROFGAMES);
    System.out.printf("Time to solve = %d us\n", NUMBEROFGAMES, (stopTime - startTime)/1000);
  }
  public static void main(String[] args)
  {
    startingSolution();
  }
}
