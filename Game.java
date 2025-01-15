import java.util.*;
public class Game{
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

    public static final String CLEAR_SCREEN = "\033[2J";

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
  public static void drawBackground(){ //needs work; fix the extra line @ bottom ????? and other
    //NOTE: COPIED FROM 12-04-COLOR SCREEN W MODIFICATIONS
    System.out.print(CLEAR_SCREEN); //is this fine?
        go(0,0); //will this make things be off???
        int wide = 80; //80 x 30 width
        int len = 30; //80 x 30 length specificatin
        color(background(BORDER_COLOR));

        for(int i = 1; i < wide-1; i++){System.out.print(" ");

        }

        for(int j = 1; j < len-1; j++) {
            System.out.println(" ");
        }

        color(background(BORDER_COLOR));
        go(len, 0);
         //PRINT REST
         //for(int i = 2; i < wid; j++) {
        for(int i = 0; i < wide; i++){
            System.out.print(" ");
            go(len,i);
        }
        //for(int j = 1; j < len; j++) {
        for(int j = 0; j < wide; j++) {

            go(len,j);

            System.out.print(" ");
        }

        go(31,1);
        color(background(BORDER_BACKGROUND));
        System.out.println(" ");
    }


  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    go(startRow, startCol);
    System.out.print(s);
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
    for(int i = row; i < row+width; i++) {
      for(int j = col; j < col+height; j++) {
        go(i,j);
        System.out.println(" ");
      }
    }
    go(row,col); //???? how to deal w indexing
    if(width > text.length()) {
      System.out.println(text.substring(0,width));
      TextBox(row+1,col,width,height-1,text.substring(width));
    }
    else {
      System.out.println(text);
    }
  }




    //return a random adventurer (choose between all available subclasses)
    //feel free to overload this method to allow specific names/stats.
    public static Adventurer createRandomAdventurer(){ //DOES THIS INCLUDE THE BOSS? *************
      int x = (int)(Math.random()*3);
      if(x==0) {
      return new Apprentice("Alicia"+(int)(Math.random()*100));
      }
      else if(x==1) {

      return new Baker("Bobby"+(int)(Math.random()*100));
      }
      else {
      return new Apprentice("Caramel"+(int)(Math.random()*100));
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
    public static void drawParty(ArrayList<Adventurer> party,int startRow){//the name is under 13 chars

      go(startRow,1);
      int l = party.size();
      int[] coordinates = new int[l];
      for(int i = 0; i < l; i++) {

        coordinates[i]=80/l*i+1;
      }
      for(int i = 0; i < l; i++) {
        go(startRow,coordinates[i]);
        drawText(party.get(i).getName(),startRow,coordinates[i]);
        go(startRow+1,coordinates[i]);
        drawText("HP: "+party.get(i).getHP()+"/"+(party.get(i)).getmaxHP()
        ,startRow+1,coordinates[i]);
        go(startRow+2,coordinates[i]);
        drawText(party.get(i).getSpecialName()+": "+party.get(i).getSpecial()+"/"+
          party.get(i).getSpecialMax(),startRow+2,coordinates[i]);
      }

      /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
      //YOUR CODE HERE
      /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
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





  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){ //note to self: i passed in party and enemies, is this allowed T-T

    drawBackground();

    //draw player party
    drawParty(party, 2);

    drawParty(enemies,26); //check indexing?


    //draw enemy party

  }

  public static String userInput(Scanner in){
      //Move cursor to prompt location

      //show cursor

      String input = in.nextLine();

      //clear the text that was written

      return input;
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){ //what happens to party size after dying?
    //Clear and initialize
    Text.hideCursor();
    Text.clear();


    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    int x = (int)(Math.random()*3)+1;
    if(x==3) {
      enemies.add(createRandomAdventurer());
      enemies.add(createRandomAdventurer());
    }
    if(x==2) {
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer()); //wtv swapped but works
    }

    if(x==1) {
      Boss newBoss = new Boss("GrillFiend"+(int)(Math.random()*100));
      enemies.add(newBoss);
    }
    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<>(); //NOTE TO SELF NAMING??
    Apprentice alice = new Apprentice("Alice"+(int)(Math.random()*100));
    Baker bob = new Baker("Bob"+(int)(Math.random()*100));
    Charcutier cAdventurer = new Charcutier("Carmack"+(int)(Math.random()*100));
      party.add(alice);
      party.add(bob);
      party.add(cAdventurer);


    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party,enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){

        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){
          party.get(whichPlayer).attack(enemies.get(whichOpponent));
        }

        else if(input.equals("special") || input.equals("sp")){
          party.get(whichPlayer).specialAttack(enemies.get(whichOpponent));
        }

        else if(input.startsWith("su ") || input.startsWith("support ")){
          String l = in.next();
          int playerSupported = Integer.parseInt(in.next());

          if(playerSupported==whichPlayer) {
            party.get(whichPlayer).support();
          }
          else {
            party.get(whichPlayer).support(party.get(playerSupported));
          }

        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";

          partyTurn = false;
          whichOpponent = 0;
        }
        //done with one party member
      }else{
        //not the party turn!


        //enemy attacks a randomly chosen person with a randomly chosen attack.z`
        //Enemy action choices go here!
        int randomP = (int)(Math.random()*party.size());
        int randomIndex = (int) (Math.random()*3);
        int randomEnemy = (int)(Math.random()*enemies.size());
        if(randomIndex==0) {
          enemies.get(randomEnemy).attack(party.get(randomP));
        }
        else if(randomIndex==1) {
            enemies.get(randomEnemy).specialAttack(party.get(randomP));
        }
        else if(randomIndex==2) {
          int randomE = (int)(Math.random()*enemies.size());
          if(randomE == randomEnemy) {
            enemies.get(randomEnemy).support();
          }
        else {
          enemies.get(randomEnemy).support(enemies.get(randomE)); //other support
        }

        //Decide where to draw the following prompt:
        String prompt = "press enter to see next turn"; //??? note to self do this!

        whichOpponent++;

      }//end of one enemy.

      //modify this if statement.
      if(!partyTurn && whichOpponent >= enemies.size()){
        //THIS BLOCK IS TO END THE ENEMY TURN
        //It only triggers after the last enemy goes.
        whichPlayer = 0;
        turn++;
        partyTurn=true;
        //display this prompt before player's turn
        String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
      }

      //display the updated screen after input has been processed.
      drawScreen(party,enemies);


    }//end of main game loop


    //After quit reset things:
    quit();
  }
}
}
