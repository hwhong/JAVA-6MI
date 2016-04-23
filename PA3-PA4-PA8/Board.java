/**
 * Sample Board
 * <p/>
 * 0   1   2   3
 * 0   -   -   -   -
 * 1   -   -   -   -
 * 2   -   -   -   -
 * 3   -   -   -   -
 * <p/>
 * The sample board shows the index values for the columns and rows
 * Remember that you access a 2D array by first specifying the row
 * and then the column: grid[row][column]
 */

import java.util.*;
import java.io.*;
import java.util.ArrayList;

public class Board {
   public final int NUM_START_TILES = 2;
   public final int TWO_PROBABILITY = 90;
   public final int GRID_SIZE;


   private final Random random;
   private int[][] grid;
   private int score;

   /* Name: Board
    * Purpose: to construct a new board object. this is done by initializing
    *          variables present as fields of this class. A board's size is input
    *          by the user, thus, it is represented by the parameter boardSize. 
    * Parameters: int boardSize, Random random 
    * Return Type: since this is a constructor, there is not return. 
    */ 

   public Board(int boardSize, Random random) 
   {
      this.random = random;
      this.GRID_SIZE = boardSize;

      this.grid = new int [boardSize][boardSize];
      this.score = 0;

      //add a number of tiles based on the constant NUM_START_TILES
      for(int i = 0; i < NUM_START_TILES; i++)
         this.addRandomTile();                                             
   }

   /* Name: Board
    * Purpose: to construct a board object. this is done by initializing
    *          variables present as fields of this class. A board's size is based
    *          on the input string inputboard.
    *          Different to the first constructor
    * Parameters: String inputBoard, Random random 
    * Return Type: since this is a constructor, there is not return. 
    */ 

   public Board(String inputBoard, Random random) throws IOException 
   {
      Scanner s = new Scanner(new File(inputBoard));

      this.GRID_SIZE = s.nextInt();
      this.score = s.nextInt();

      this.grid = new int[GRID_SIZE][GRID_SIZE];
      this.random = random; 

      for (int row = 0; row < GRID_SIZE; row ++) 
      {
         for (int column = 0; column < GRID_SIZE; column++) 
         {
            grid[row][column] = s.nextInt();
         } 
      }
   }

   /* Name: saveBoard
    * Purpose: The purpose of this method is to save the current state of the board
    *          into a file called outputBoard. The GRID_SIZE, score, along with the
    *          board ints will be input into the file.
    * Parameter: String outputBoard
    * Return Type: This method does not have any return types. Simply void.
    */ 

   public void saveBoard(String outputBoard) throws IOException 
   {
      File file = new File(outputBoard);
      PrintWriter printwriter = new PrintWriter(file);

      printwriter.println(GRID_SIZE);
      printwriter.println(score);

      for (int row = 0; row < GRID_SIZE; row++) 
      {
         for (int column = 0; column < GRID_SIZE; column++) 
         {
            printwriter.print(grid[row][column]);
            printwriter.print(" ");
         }
         printwriter.println();
      }
      printwriter.close();
   }

   /* Name: addRandomTile() 
    * Purpose: The purpose of this method is to add random tiles of either 2 or 4
    *          onto the empty space that exists on the board. Based on the values
    *          that are generated using random, the location and the probability
    *          are found. 
    * Parameter: non 
    * Return Type: This method is void. 
    */

   public void addRandomTile() 
   {
      int count = 0; 
      int location = 0;
      int value = 0;

      for (int row = 0; row < GRID_SIZE; row ++) 
      {
         for (int column = 0; column < GRID_SIZE; column++) 
         {
            if (grid [row][column] == 0) 
            {
               count++;
            } 
         } 
      }

      if (count == 0)
      { 
         return;
      } 


      location = random.nextInt(count);
      value = random.nextInt(100);

      count = 0;

      for (int row = 0; row < GRID_SIZE; row++) 
      {
         for (int column = 0; column < GRID_SIZE; column++) 
         {
            if (grid[row][column] == 0) 
            {
               if (count == location)
               {
                  if (value < TWO_PROBABILITY) 
                  {
                     grid[row][column] = 2;
                  } 
                  else
                  {
                     grid[row][column] = 4;
                  } 
               }
               count++;
            } 
         }
      } 
   }

   /* Name: rotate
    * Purpose: the method either rotate the grid clockwise or anticlockwise based
    *          on the boolean rotateClockwise that is pasted in as input. If the
    *          boolean is true it will rotate the board clockwise by 90 degress,
    *          while if the boolean result to false, the board will be rotated
    *          anticlockwise. the help method rotateHelp will be use to assit the
    *          rotating. It is defined below. 
    * Parameters: boolean rotateClockwise
    * Return Type: the method does not return anything simply void. 
    */ 
   public void rotate(boolean rotateClockwise) 
   {

      if(!rotateClockwise)
      {
         rotateHelper(); 
      }
      else
      {
         rotateHelper();
         rotateHelper();
         rotateHelper();
      }
   }
   /* Name: rotateHelper
    * Purpose: This method is what makes the method rotate work. It uses variable
    *          such as temp to help keep track of where each row and column are.
    *          This is done by iterating first the row then the column. 
    * Parameter: This method takes no parameter 
    * Return Type: this method does not have a return type. Simply void. 
    */ 

   private void rotateHelper()
   {
      for(int n = 0; n < GRID_SIZE - 1; n++){
         for(int m = n+1; m < GRID_SIZE;m++){
            int temp = grid[m][n];
            grid[m][n]=grid[n][m];
            grid[n][m]=temp;
         }
      }

      for (int y = 0; y < GRID_SIZE; y++) 
      {
         for (int x = 0; x < GRID_SIZE / 2; x++)
         {
            int temp = grid[x][y];
            grid[x][y] = grid[GRID_SIZE - x - 1][y];
            grid[GRID_SIZE - x - 1][y] = temp;
         }
      }


   }


   //Complete this method ONLY if you want to attempt at getting the extra credit
   //Returns true if the file to be read is in the correct format, else return
   //false
   public static boolean isInputFileCorrectFormat(String inputFile) {
      //The try and catch block are used to handle any exceptions
      //Do not worry about the details, just write all your conditions inside the
      //try block
      try {
         //write your code to check for all conditions and return true if it satisfies
         //all conditions else return false
         return true;
      } catch (Exception e) {
         return false;
      }
   }

   /* Name: move
    * Purpose: the purpose of this method is to move the actual tiles on the
    *          grid. However, the main methods that moves the tiles are defined
    *          later below as private methods. The method reads if the direction
    *          is right, left, down, or left to determine which direction the
    *          tiles will be moving. 
    * Parameters: Direction direction 
    * Return Type: this method will return true if the move is successful
    */ 

   public boolean move(Direction direction) 
   {
      if (direction == Direction.RIGHT)
      {
         rightDir();
         return true;
      } 

      if (direction == Direction.LEFT) 
      {
         leftDir();
         return true;
      } 

      if (direction == Direction.DOWN) 
      {
         downDir();
         return true;
      } 

      if (direction == Direction.UP)
      {
         upDir();
         return true;
      } 

      return false;
   }

   /* Name: rightDir
    *  Purpose: The method moves the tiles to the right of the grid. The method
    *           does this by constructing two arraylists. The two arraylist each
    *           has different uses. At last the manipulated arraylist will be
    *           printed back to the grid. 
    *  Parameters: none
    *  Return Type: since it directly modifies the grid, there is no return
    *  type.
    */ 

   private void rightDir()
   { 
      ArrayList<Integer> values = new ArrayList< >();
      ArrayList<Integer> results = new ArrayList< >();

      for ( int row = 0; row < GRID_SIZE; row++) 
      {
         values.clear();
         results.clear();

         for (int column = 0; column < GRID_SIZE; column++)
         {
            if (grid[row][column] != 0)
            { 
               values.add(grid[row][column]);
            } 
         } 

         //rearrange the arrayList from backward to forward 

         int temp1 = 0;

         for (int x = 0; x < values.size(); x++) 
         {
            temp1 = values.get(x);
            values.set(x, values.get(values.size()-x-1));
            values.set(values.size()- x - 1, temp1);
         }

         //mergeing adjacent elements in the array list and output the merged
         //elements to a new arrayList called results 

         int temp = 0; 

         while (values.size() != 0)
         {
            temp = values.get(0);
            values.remove(0);

            if (values.size() != 0 && temp == values.get(0))
            {
               results.add(temp*2);
               values.remove(0);
               this.score = score + results.get(results.size()-1);
            } 
            else 
            { 
               results.add(temp);
            } 
         } 

         //rearrange the arraylist by swapping 

         int temp2 = 0;

         for (int x = 0; x < results.size(); x++) 
         {
            temp2 = results.get(x);
            results.set(x, results.get(results.size()-x-1));
            results.set(results.size()- x - 1, temp2);
         }

         //print the arraylist back onto the grid
         //start by printing 0, then the actual ints 

         int i;
         for( i = 0; i < GRID_SIZE - results.size(); i++)
         {
            grid[row][i] = 0;
         }

         int z = 0;
         for (; i < GRID_SIZE; i++)
         {
            grid[row][i]= results.get(z).intValue();
            z++;
         }
      }

   } 

   /* Name: leftDir
    *  Purpose: The method moves the tiles to the left of the grid. The method
    *           does this by constructing two arraylists. The two arraylist each
    *           has different uses. At last the manipulated arraylist will be
    *           printed back to the grid. 
    *  Parameters: none
    *  Return Type: since it directly modifies the grid, there is no return
    *  type.
    */ 

   private void leftDir()
   {
      ArrayList<Integer> values = new ArrayList< >();
      ArrayList<Integer> results = new ArrayList< >();

      for ( int row = 0; row < GRID_SIZE; row++) 
      {
         values.clear();
         results.clear();

         for (int column = 0; column < GRID_SIZE; column++)
         {
            if (grid[row][column] != 0)
            {
               values.add(grid[row][column]);
            } 
         } 


         int temp = 0; 

         while (values.size() != 0)
         {
            temp = values.get(0);
            values.remove(0);

            if (values.size() != 0 && temp == values.get(0))
            {
               results.add(temp*2);         
               values.remove(0);
               this.score = score + results.get(results.size()-1);
            } 
            else 
            { 
               results.add(temp);
            } 
         } 

         int i;
         for( i = 0; i < results.size(); i++ ){
            grid[row][i]= results.get(i).intValue();
         }
         for(; i<GRID_SIZE; i++){
            grid[row][i]=0;
         }
      }  
   }

   /* Name: upDir
    *  Purpose: The method moves the tiles upward of the grid. The method
    *           does this by constructing two arraylists. The two arraylist each
    *           has different uses. At last the manipulated arraylist will be
    *           printed back to the grid. 
    *  Parameters: none
    *  Return Type: since it directly modifies the grid, there is no return
    *  type.
    */ 

   private void upDir()
   {
      ArrayList<Integer> values = new ArrayList< >();
      ArrayList<Integer> results = new ArrayList< >();

      for ( int column = 0; column < GRID_SIZE; column++) 
      {
         values.clear();
         results.clear();

         for (int row = 0; row < GRID_SIZE; row++)
         {
            if (grid[row][column] != 0)
            {
               values.add(grid[row][column]);
            } 
         } 


         int temp = 0; 

         while (values.size() != 0)
         {
            temp = values.get(0);
            values.remove(0);

            if (values.size() != 0 && temp == values.get(0))
            {
               results.add(temp*2);
               values.remove(0);
               this.score = score + results.get(results.size()-1);
            } 
            else 
            { 
               results.add(temp);
            } 
         } 

         int i;
         for( i = 0; i < results.size(); i++ ){
            grid[i][column]= results.get(i).intValue();
         }
         for(; i < GRID_SIZE; i++){
            grid[i][column] = 0;
         }
      }  
   }

   /* Name: downDir
    *  Purpose: The method moves the tiles downward of the grid. The method
    *           does this by constructing two arraylists. The two arraylist each
    *           has different uses. At last the manipulated arraylist will be
    *           printed back to the grid. 
    *  Parameters: none
    *  Return Type: since it directly modifies the grid, there is no return
    *  type.
    */ 


   private void downDir()
   { 
      ArrayList<Integer> values = new ArrayList< >();
      ArrayList<Integer> results = new ArrayList< >();

      for ( int column = 0; column < GRID_SIZE; column++) 
      {
         values.clear();
         results.clear();

         for (int row = 0; row < GRID_SIZE; row++)
         {
            if (grid[row][column] != 0)
            { 
               values.add(grid[row][column]);
            } 
         } 

         //rearrange the arrayList from backward to forward 

         int temp1 = 0;

         for (int x = 0; x < values.size(); x++) 
         {
            temp1 = values.get(x);
            values.set(x, values.get(values.size()-x-1));
            values.set(values.size()- x - 1, temp1);
         }

         //mergeing adjacent elements in the array list and output the merged
         //elements to a new arrayList called results 

         int temp = 0; 

         while (values.size() != 0)
         {
            temp = values.get(0);
            values.remove(0);

            if (values.size() != 0 && temp == values.get(0))
            {
               results.add(temp*2);
               values.remove(0);
               this.score = score + results.get(results.size()-1);
            } 
            else 
            { 
               results.add(temp);
            } 


         } 

         //rearrange the arraylist by swapping 

         int temp2 = 0;

         for (int x = 0; x < results.size(); x++) 
         {
            temp2 = results.get(x);
            results.set(x, results.get(results.size()-x-1));
            results.set(results.size()- x - 1, temp2);
         }

         //print the arraylist back onto the grid
         //start by printing 0, then the actual ints 

         int i;
         for( i = 0; i < GRID_SIZE - results.size(); i++)
         {
            grid[i][column] = 0;
         }

         int z = 0;
         for (; i < GRID_SIZE; i++)
         {
            grid[i][column]= results.get(z).intValue();
            z++;
         }
      }

   } 

   /* Name: isGameOver
    *  Purpose: The method checks whether if there are any valid moves left
    *           in the grid. This is done by calling the method canMove.
    *  Parameters: none
    *  Return Type: The method returns true if there are no possible moves left
    *               in the grid, else, false will be turned so that the user can
    *               continue playing the game. 
    */ 

   public boolean isGameOver() 
   {
      boolean left = this.canMove(Direction.LEFT);
      boolean right = this.canMove(Direction.RIGHT);
      boolean up = this.canMove(Direction.UP);
      boolean down = this.canMove(Direction.DOWN);

      if (left == false && right == false
            && up == false && down == false) 
      {
         return true;
      } 

      return false;
   }

   /* Name: canMove
    * Purpose: the general purpose of this method is to check if there are any
    *          viable moevs left in the grid. The method does this by checking
    *          each direction. from right, left, up to down. If the move is
    *          possible then the method returns true. The definition of a
    *          viable move is when the adjacent element in the way of direction
    *          is either a 0 or the number equivalent to the one that is being
    *          moved. 
    * Parameter: Direction direction 
    * Return Type: the method returns true if the move is possible, else it
    *              do nothing.
    */


   public boolean canMove(Direction direction) 
   {
      if(direction == Direction.UP)
      {
         for (int row = GRID_SIZE - 1; row > 0; row--) 
         {
            for (int column = 0; column < GRID_SIZE; column++) 
            {
               if( grid[row][column] != 0)
               {
                  if (grid[row-1][column] == grid[row][column])
                  {
                     return true;
                  }
                  else if (grid[row-1][column]==0)
                  {
                     return true;
                  } 
               }
            } 
         }
      }


      if(direction == Direction.DOWN)
      {
         for (int row = 0; row < GRID_SIZE-1;row++)
         {
            for (int column = 0; column < GRID_SIZE; column++)
            {
               if (grid[row][column] != 0)
               {
                  if (grid[row+1][column] == grid[row][column])
                  {
                     return true;
                  }
                  else if (grid[row+1][column]==0)
                  {
                     return true;
                  }
               }
            } 
         } 
      } 

      if(direction == Direction.RIGHT) 
      {
         for (int row = 0; row < GRID_SIZE; row++)
         {
            for (int column = 0; column < GRID_SIZE-1; column++) 
            {
               if (grid[row][column] != 0)
               {
                  if (grid[row][column+1] == 0)
                  {
                     return true;
                  } 
                  else if (grid[row][column]==grid[row][column+1])
                  {
                     return true;
                  } 
               }
            } 
         } 
      } 

      if(direction == Direction.LEFT) 
      {
         for (int row = 0; row < GRID_SIZE; row++) 
         {
            for (int column = GRID_SIZE -1 ; column > 0;column--) 
            {
               if (grid[row][column] != 0)
               {
                  if (grid[row][column-1] == 0)
                  {
                     return true;
                  } 
                  else if (grid[row][column] == grid[row][column-1]) 
                  {
                     return true;
                  } 
               }
            } 
         } 
      }
      return false;

      //end brace
   }

   // Return the reference to the 2048 Grid
   public int[][] getGrid() {
      return grid;
   }

   // Return the score
   public int getScore() {
      return score;
   }

   @Override
      public String toString() {
         StringBuilder outputString = new StringBuilder();
         outputString.append(String.format("Score: %d\n", score));
         for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
               outputString.append(grid[row][column] == 0 ? "    -" :
                     String.format("%5d", grid[row][column]));

            outputString.append("\n");
         }
         return outputString.toString();
      }
}
