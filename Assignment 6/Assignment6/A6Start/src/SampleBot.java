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
import battleship.CellState;
import jdk.swing.interop.SwingInterOpUtils;

import java.awt.Point;
import java.util.*;

/**
 * A Sample random shooter - Takes no precaution on double shooting and has no strategy once
 * a ship is hit.
 *
 * Next Steps
 * 1. Add code to shoot around each hit until a ship sinks
 * 2. Once sink is working -- improve the hunt -- research the concept of parity
 * 3. Improve the hunt -- based on the fact not all squares are equal -- need a probabilty map
 *      Option 1 - fixed shooting pattern
 *      Option 2 - Build a probabilty map - update it after every shot (will increase time)
 * 4. Improve sinker - Optimize the best next shot - Minimize wasted shots
 *
 * @author mark.yendt
 */
public class SampleBot {
    private int gameSize; //Board size == 10 always (10 x 10)
    private BattleShip battleShip; //The board itself
    private Random random; //Random
    private ArrayList<Point> firedShots; //Store shots in ArrayList
    private CellState[][] map; //Store shots in 2D Array (memory of board)
    private Queue<Point> priority;
    private int[] shipSizes;
    private ArrayList<Integer> sanked;

    /**
     * Constructor for SampleBot class
     * @param b previously created battleship instance - should be a new game
     */
    public SampleBot(BattleShip b) {
        battleShip = b; //Board
        gameSize = b.boardSize; //Size
        random = new Random();   //Needed for random shooter - not required for more systematic approaches
        firedShots = new ArrayList<Point>(); //List of shots fired
        map = new CellState[gameSize][gameSize]; //Memory of board state
        priority = new LinkedList<>(); //Points to shoot next
        shipSizes = b.shipSizes();
        sanked = new ArrayList<>();
        for (int y = 0; y < gameSize; y++) {
            for (int x = 0; x < gameSize; x++) {
                map[x][y] = CellState.Empty;
            }
        }
    }

    /**
     * Checks arounds and puts the adjacent spots in queue
     * @param shot
     */
    public void around(Point shot) {
        if (!(firedShots.contains(new Point(shot.x - 1, shot.y))) && (-1 < shot.x - 1) && !(priority.contains(new Point(shot.x - 1, shot.y))))
            priority.add(new Point(shot.x - 1, shot.y));
        if (!(firedShots.contains(new Point(shot.x + 1, shot.y))) && (shot.x + 1 < gameSize) && !(priority.contains(new Point(shot.x + 1, shot.y))))
            priority.add(new Point(shot.x + 1, shot.y));
        if (!(firedShots.contains(new Point(shot.x, shot.y - 1))) && (-1 < shot.y - 1) && !(priority.contains(new Point(shot.x, shot.y - 1))))
            priority.add(new Point(shot.x, shot.y - 1));
        if (!(firedShots.contains(new Point(shot.x, shot.y + 1))) && (shot.y + 1 < gameSize) && !(priority.contains(new Point(shot.x, shot.y + 1))))
            priority.add(new Point(shot.x, shot.y + 1));
    }

    /**
     * Checks the surroundings if shot is hit
     * @param shot
     */
    public void check(Point shot) {
        boolean hit = battleShip.shoot(shot);
        if (hit) { //Sink mode
            map[shot.x][shot.y] = CellState.Hit;
            around(shot);
        } else {
            map[shot.x][shot.y] = CellState.Miss;
        }
    }

    /**
     * Determines the largest ship that is not sunk
     * @return
     */
    public int largestLeft() {
        int max = 0;
        boolean first3 = false;
        boolean second3 = false;
        for (Integer i : sanked) {
            if (i == 3) {
                if (!first3) {
                    first3 = true;
                } else {
                    second3 = true;
                }
            }
        }
        for (int i : shipSizes) {
            if ((!sanked.contains(i)) && (max < i)) {
                max = i;
            }
            if ((!second3) && (max < 3)) {
                max = 3;
            }
        }
        return max;
    }

    /**
     * Checks if the ship is valid vertically
     * @param a1
     * @param a2
     * @param b
     * @return
     */
    public boolean isValidY(int a1, int a2, int b) {
        boolean valid = true;
        for (int i = a1; i < a2; i++) {
            if (map[b][i] != CellState.Empty) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Checks if the ship is valid horizontally
     * @param a1
     * @param a2
     * @param b
     * @return
     */
    public boolean isValidX(int a1, int a2, int b) {
        boolean valid = true;
        for (int i = a1; i < a2; i++) {
            if (map[i][b] != CellState.Empty) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * Creates density spread on 2d array to make hunting process more efficient
     * @return
     */
    public Point densityShot() {
        int[][] density = new int[gameSize][gameSize];
        for (int y = 0; y < gameSize; y++) {
            for (int x = 0; x < gameSize; x++) {
                density[x][y] = 0;
            }
        }
        int largest = largestLeft();
        for (int y = 0; y <= gameSize - largest; y++) {
            for (int x = 0; x < gameSize; x++) {
                if (isValidY(y, y + largest, x)) {
                    for (int z = y; z < y + largest; z++) {
                        density[x][z] += 1;
                    }
                }
            }
        }
        for (int x = 0; x <= gameSize - largest; x++) {
            for (int y = 0; y < gameSize; y++) {
                if (isValidX(x, x + largest, y)) {
                    for (int z = x; z < x + largest; z++) {
                        density[z][y] += 1;
                    }
                }
            }
        }
        Point max = new Point(0, 0);
        for (int y = 0; y < gameSize; y++) {
            for (int x = 0; x < gameSize; x++) {
                if (density[max.x][max.y] < density[x][y]) {
                    max = new Point(x, y);
                }
            }
        }
        return max;
    }

    /**
     * Determines the longest ship that was sunk (not acurrate but is used to speed up serach algorithm)
     * @param shot
     */
    public void longest(Point shot) {
        int xLeft = 1;
        int xRight = 1;
        int yUp = 1;
        int yDown = 1;
        boolean contXL = true;
        boolean contXR = true;
        boolean contYU= true;
        boolean contYD = true;
        int xCount = 1;
        int yCount = 1;
        while (contXL || contXR || contYU || contYD) {
            if ((-1 < (shot.x - xLeft)) && (map[shot.x - xLeft][shot.y] == CellState.Hit) && contXL) {
                xLeft += 1;
                xCount += 1;
            }
            else {
                contXL = false;
            }
            if (((shot.x + xRight) < 10) && (map[shot.x + xRight][shot.y] == CellState.Hit) && contXR) {
                xRight += 1;
                xCount += 1;
            }
            else {
                contXR = false;
            }
            if ((-1 < (shot.y - yUp)) && (map[shot.x][shot.y - yUp] == CellState.Hit) && contYU) {
                yUp += 1;
                yCount += 1;
            }
            else {
                contYU = false;
            }
            if (((shot.y + yDown) < 10) && (map[shot.x][shot.y + yDown] == CellState.Hit) && contYD) {
                yDown += 1;
                yCount += 1;
            }
            else {
                contYD = false;
            }
        }
        int value = Math.min(Math.max(xCount, yCount), 5);
        if (sanked.contains(value) && (value != 3)) {
            sanked.add(3);
        } else {
            sanked.add(value);
        }
    }

    /**
     * Create a random shot and calls the battleship shoot method
     *
     * @return true if a Ship is hit, false otherwise
     */
    public void fireShot() {
        Point shot;
        int numberOfShipsSunk = battleShip.numberOfShipsSunk();
        if (priority.isEmpty()) {
            shot = densityShot();
        } else {
            shot = priority.remove();
        }
        //System.out.println("\n" + shot.toString() + " was shot");
        firedShots.add(shot);
        check(shot);
        if (numberOfShipsSunk < battleShip.numberOfShipsSunk()) {
            //System.out.println("We SUNK a SHIP!!! Total Sunk = " + battleShip.numberOfShipsSunk());
            longest(shot);
        }
    }

    /**
     * Print the game map to the screen
     */
    void printMap()
    {
        System.out.println("M A P of B A T T L E S H I P");
        for (int y = 0; y < gameSize; y++) {
            for (int x = 0; x < gameSize; x++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
    }
}
