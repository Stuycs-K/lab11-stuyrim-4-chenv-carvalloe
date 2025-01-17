import java.util.*;
public class Game{

  public static void main(String[] args) {
    run();
  }
  public static void go(int row,int col){
    System.out.print("\033[" + row + ";" + col + "H");
}

public static int background(int color){
  return color + 10;
}
public static void color(int m){
  System.out.print("\033[;" + m + "m");
}
public static void color(int m1, int m2){
  System.out.print("\033["+ m1 + ";" + m2 + "m");
}
public static void color(int m1, int m2, int m3){
  System.out.print("\033["+ m1 + ";" + m2 + ";" + m3 + "m");
}
public static void color(int m1, int m2, int m3, int m4){
  System.out.print("\033["+ m1 + ";" + m2 + ";" + m3 + ";"+m4+"m");
}

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.clear();

        for(int i = 1; i <= 80; i++){
          Text.go(1,i);

          if(i%2 == 1) {
            System.out.print(Text.colorize("/",Text.BOLD,Text.RED+Text.BRIGHT));
          }
          else {
            System.out.print(Text.colorize("\\",Text.BOLD,Text.RED+Text.BRIGHT));
          }
        }

        for (int j = 1; j <= 30; j++) {
          Text.go(j, 1);
          if(j%2 == 1) {
            System.out.print(Text.colorize("/",Text.BOLD,Text.RED+Text.BRIGHT));
          }
          else {
            System.out.print(Text.colorize("\\",Text.BOLD,Text.RED+Text.BRIGHT));
          }
          go(j,80);
          if(j%2 == 1) {
            System.out.print(Text.colorize("\\",Text.BOLD,Text.RED+Text.BRIGHT));
          }
          else {
            System.out.print(Text.colorize("/",Text.BOLD,Text.RED+Text.BRIGHT));
          }
          
          for(int i = 0; i <= 80; i++){
            Text.go(30,i);
            if(i%2 == 1) {
              System.out.print(Text.colorize("\\",Text.BOLD,Text.RED+Text.BRIGHT));
            }
            else {
              System.out.print(Text.colorize("/",Text.BOLD,Text.RED+Text.BRIGHT));
            }
          }
          
      }

      for (int j = 2; j <= 79; j++){
        Text.go(6,j);
        System.out.print(Text.colorize("~",Text.BOLD,Text.RED+Text.BRIGHT));
      }
      for (int j = 7; j <= 24; j++){
        Text.go(j,40);
        if(j%2==0) {
        System.out.print(Text.colorize("{",Text.BOLD,Text.RED+Text.BRIGHT));
        }
        else {
          System.out.print(Text.colorize("}",Text.BOLD,Text.RED+Text.BRIGHT));
        
        }
      }
      for (int j = 2; j <= 79; j++){
        Text.go(25,j);
        System.out.print(Text.colorize("~",Text.BOLD,Text.RED+Text.BRIGHT));
      }
      Text.go(32,1);
      System.out.println(" ");
        Text.reset();
    }
    
  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    go(startRow, startCol);
    System.out.println(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    if(height==0) {
      return;
    }
    for(int i = row; i < row+height; i++) {
      for(int j = col; j < col+width; j++) {
        go(i,j);
        System.out.print(" ");
      }
    }
    go(row,col); 
    if(text.length() > width) {
      drawText(text.substring(0,width),row,col);
      TextBox(row+1,col,width,height-1,text.substring(width));
    }
    else {
      drawText(text,row,col);
    }
  }

    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){
      int x = (int)(Math.random()*3);
      if(x==0) {
        String[] potentialNames = new String[]{"Abel","Alex","Alicia","Amelia","Anthony","Aurora","Autumn","Ava"};
        String finName = potentialNames[(int) (Math.random()*potentialNames.length)];
      return new Apprentice(finName+(int)(Math.random()*100));
      }
      else if(x==1) {
        String[] potentialNames = new String[]{"Brooks", "Brayden", "Bradley", "Brooklyn", "Bea", "Bethany", "Bonnie", "Barbara"};
        String finName = potentialNames[(int) (Math.random()*potentialNames.length)];
      return new Baker(finName+(int)(Math.random()*100));
      }
      else {
        String[] potentialNames = new String[]{"Cade","Caramel","Carlos","Carnation","Cecilia","Charlotte","Clementine","Cooper"};
        String finName = potentialNames[(int) (Math.random()*potentialNames.length)];
      return new Charcutier(finName+(int)(Math.random()*100));
      }
    }
    /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
    *Should include Name HP and Special on 3 separate lines.
    *Note there is one blank row reserved for your use if you choose.
    *Format:
    *Bob          Amy        Jun
    *HP: 10       HP: 15     HP:19
    *Caffeine: 20 Mana: 10   Snark: 1
    * ***THIS ROW INTENTIONALLY LEFT BLANK***
    */
    public static void drawParty(ArrayList<Adventurer> party,int startRow, boolean isE){//the name is under 13 chars

      int l = party.size();
      int ll;
      int[] coordinates = new int[l];
      for(int i = 0; i < l; i++) {

        coordinates[i]=80/l*i+2;
      }
      for(int i = 0; i < l; i++) {
        go(startRow,coordinates[i]);
        if(isE) {
          ll = 31;
        }
        else {
          ll = 32;
        }
    
        drawText(Text.colorize(party.get(i).getName(),Text.BOLD,Text.INVERTED,ll),startRow,coordinates[i]);
        go(startRow+1,coordinates[i]);
        drawText("HP: "+colorByPercent(party.get(i).getHP(),party.get(i).getmaxHP())
        ,startRow+1,coordinates[i]);
        go(startRow+2,coordinates[i]);
        drawText(party.get(i).getSpecialName()+": "+colorByPercent(party.get(i).getSpecial(),party.get(i).getSpecialMax()),startRow+2,coordinates[i]);
      }
    }
  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    if(hp < maxHP/4) {
      return "\u001B[31m" + output + "\u001B[0m";
    }
    else if(hp < 3*maxHP/4) {
      return  "\u001B[33m" + output + "\u001B[0m";
    }
    return output;
  }
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){ 
    //note to self: i passed in party and enemies, is this allowed T-T
    drawBackground();
    //draw player party
    drawParty(party, 25,false);
    drawParty(enemies,1,true);
    //draw enemy party
    
    drawText(Text.colorize("Party actions (party below)",Text.BOLD,Text.GREEN+Text.BRIGHT)
    ,6,2);
    drawText(Text.colorize("Enemy actions (enemies above)",Text.BOLD,Text.RED+Text.BRIGHT),6,41);

    Text.go(31,1);
    Text.showCursor();
  }
  public static void TextBoxR(int row, int col, int width, int height, ArrayList<String> text) {
    int size = text.size();
    int addRow = 0;
    for(int i = 0; i < size; i++) {
      String m = text.get(i);
      TextBox(row+addRow,col,width,height-addRow,m);
      if(m.length() % width != 0) {
        addRow+=1;
      }
      addRow += m.length()/width;
    }
  }
  
  public static String userInput(Scanner in){
      //Move cursor to prompt location
      Text.go(31,1);
      //show cursor
      Text.showCursor();

      String input = in.nextLine();
      //clear the text that was written
      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(31,1);
  }

  public static void checkDead(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies) {
    for(int i = 0; i < party.size(); i++) {
      if(party.get(i).getHP()<=0) {
        party.remove(i);
        i--;
      }
    }
    for(int i = 0; i < enemies.size(); i++) {
      if(enemies.get(i).getHP()<=0) {
        enemies.remove(i);
        i--;
      }
    }
  }
  public static void run(){
    // ******* INITIAL SETUP
    Text.go(31,1);
    Text.hideCursor();
    Text.clear();
    
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int numEnemies = (int)(Math.random()*3)+1;
    if (numEnemies==1) {
        enemies.add(new Boss("Gordon"+(int)(Math.random()*100)));
    } 
    else {
      for (int i=0; i < numEnemies; i++) {
        enemies.add(createRandomAdventurer());
      }
    }

    ArrayList<Adventurer> party = new ArrayList<>();
      party.add(new Apprentice("Alice"+(int)(Math.random()*100)));
      party.add(new Baker("Bob"+(int)(Math.random()*100)));
      party.add(new Charcutier("Carmack"+(int)(Math.random()*100)));
    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    String input = "";
    Scanner in = new Scanner(System.in);
    boolean justFinishedEnemy = false;
    boolean justFinishedParty = false;

    drawScreen(party,enemies);
    ArrayList<String> partyTW = new ArrayList<String>();
    ArrayList<String> enemyTW = new ArrayList<String>();

    //********** DONE WITH SETUP */
    
    
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //******* CHECKS FOR PARTY VS ENEMY TURN */
      if(whichPlayer>=party.size()) {
        whichPlayer=0;
        partyTurn = false;
        justFinishedParty = true;
      }
      if(whichOpponent>=enemies.size()) {
        whichOpponent=0;
        partyTurn = true;
        justFinishedEnemy = true;
      }
      //************ DONE CHECKING FOR PARTY/ENEMY TURN */

      drawScreen(party, enemies);
        TextBoxR(7,2,37,13,partyTW);
        TextBoxR(7,41,38,13,enemyTW);
      
      if(partyTurn){
        if(justFinishedEnemy) {
          justFinishedEnemy = false;
          String enterPrompt = "party's turn: press enter to start next turn"; 
          TextBox(28,2,70,1,enterPrompt);
        }
        else {
          partyTW.clear();
        enemyTW.clear();
        //******* CHECKING ACTION */
        String preprompt = "Enter command for "+party.get(whichPlayer)+": (a)ttack/(sp)ecial/(su)pport/(q)uit [player #]";
        drawText(preprompt,28,2);
        input = userInput(in);
        
        if(!(party.get(whichPlayer).getHP()==0)){
        if(input.startsWith("attack") || input.startsWith("a")){
          String[] supportStringSplot = input.split(" ");
          if(supportStringSplot.length == 1) {
            partyTW.add("you need to select a player #, please try again.");
          whichPlayer--;
          }
          if(supportStringSplot.length >= 2) {
            int playerSupported = Integer.parseInt(supportStringSplot[1]);
            if(playerSupported >= enemies.size() || playerSupported < 0) {
              partyTW.add("your input went out of bounds, please try again.");
          whichPlayer--;
            }
            else {
              partyTW.add(party.get(whichPlayer).attack(enemies.get(playerSupported)));
            }
          } 
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          String[] supportStringSplot = input.split(" ");
          if(supportStringSplot.length == 1) {
            partyTW.add("you need to select a player #, please try again.");
          whichPlayer--;
          }
          if(supportStringSplot.length >= 2) {
            int playerSupported = Integer.parseInt(supportStringSplot[1]);
            if(playerSupported >= enemies.size() || playerSupported < 0) {
              partyTW.add("your input went out of bounds, please try again.");
          whichPlayer--;
            }
            else {
              partyTW.add(party.get(whichPlayer).specialAttack(enemies.get(playerSupported)));
            }
          } 
        }
        else if(input.startsWith("su") || input.startsWith("support")){
          String[] supportStringSplot = input.split(" ");
          if(supportStringSplot.length == 1) {
            partyTW.add("you need to select a player #, please try again.");
          whichPlayer--;
          }
          if(supportStringSplot.length >= 2) {
            int playerSupported = Integer.parseInt(supportStringSplot[1]);
            if(playerSupported==whichPlayer) {
              partyTW.add(party.get(whichPlayer).support());
            }
            else if(playerSupported >= party.size() || playerSupported < 0) {
              partyTW.add("your input went out of bounds, please try again.");
          whichPlayer--;
            }
            else {
              partyTW.add(party.get(whichPlayer).support(party.get(playerSupported)));
            }
          } 
        }
        else {
          partyTW.add("your input did not match the requirements, please try again.");
          whichPlayer--;
        }
        whichPlayer++; 
        }
        checkDead(party, enemies);
        //******* DONE CHECKING ACTION */
      }

        
        //**** CHECKING IF ANY PLAYERS HAVE DIED */
      if (party.isEmpty()) {
          drawText("Game Over! The enemies have won! Try again...", 28,2);
          EndScreen.drawLoss();
          quit();
          break;
      } 
      else if (enemies.isEmpty()) {
          drawText("Congratulations! You have defeated all enemies!", 28,2);
          EndScreen.drawWin();
          quit();
          break;
      }
        if(whichPlayer > party.size()){
          String enemyPrompt = "enemy's turn: press enter to see next turn"; 
          TextBox(28,2,70,1,enemyPrompt);

          partyTurn = false;
          whichPlayer=0;
          whichOpponent = 0;
        }
        //**** DONE CHECKING IF ANY PLAYERS HAVE DIED */

        if(whichPlayer >= party.size()){
          String prompt = "press enter to see monster's turn";
          TextBox(26,2, 78, 2, prompt);
          partyTurn = false;
          whichOpponent = 0;
        }

      }
      else{
        if(justFinishedParty) {
          justFinishedParty = false;
          String enterPrompt = "enemy's turn: press enter to see opponent moves"; 
          TextBox(28,2,70,1,enterPrompt);
          System.out.flush();
        }
        else {
        checkDead(party, enemies);
        drawScreen(party, enemies);
        int randomP = (int)(Math.random()*party.size());
        int randomIndex = (int) (Math.random()*3);
        int randomEnemy = (int)(Math.random()*enemies.size());
        
        if(randomIndex==0) {
          enemyTW.add(enemies.get(randomEnemy).attack(party.get(randomP)));
        }
        else if(randomIndex==1) {
          enemyTW.add(enemies.get(randomEnemy).specialAttack(party.get(randomP)));
        }
        else {
          int randomE = (int)(Math.random()*enemies.size());
          if(randomE == randomEnemy) {
            enemyTW.add(enemies.get(randomEnemy).support());
          }
          else {
            enemyTW.add(enemies.get(randomEnemy).support(enemies.get(randomE)));
          }
      }
      whichOpponent++;
      checkDead(party, enemies);
      System.out.flush();

    if (party.isEmpty()) {
        drawText("Game Over! The enemies have won! Try again...", 28,2);
        EndScreen.drawLoss();
        quit();
        break;
    }
    else if (enemies.isEmpty()) {
        drawText("Congratulations! You have defeated all enemies!", 28,2);
        EndScreen.drawWin();
        quit();
        break;
    }
    }
  }
  quit();
  }
}
}