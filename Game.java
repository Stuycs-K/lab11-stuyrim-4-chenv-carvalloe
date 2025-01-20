import java.util.*;
public class Game{//??? glitch where u go over HP for charcutier, check others,
  //??? use methods from Adventurer as needed
  //?????? MAJOR PROBLEM WITH OPPOSING SIDE'S NUMBER CHOSEN (THE 1-5)
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;

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
          go(j, 1);
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
          
          for(int i = 1; i <= 80; i++){
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
      for (int j = 7; j <= 23; j++){
        Text.go(j,40);
        if(j%2==0) {
        System.out.print(Text.colorize("{",Text.BOLD,Text.RED+Text.BRIGHT));
        }
        else {
          System.out.print(Text.colorize("}",Text.BOLD,Text.RED+Text.BRIGHT));
        
        }
      }
      for (int j = 2; j <= 79; j++){
        Text.go(24,j);
        System.out.print(Text.colorize("~",Text.BOLD,Text.RED+Text.BRIGHT));
      }
      Text.go(29,2);
        Text.reset();
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
        System.out.print(" ");
      }
    }
    go(row,col); //???? how to deal w indexing
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
    public static Adventurer createRandomAdventurer(){ //DOES THIS INCLUDE THE BOSS? *************
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
      return new Charcutier("Caramel"+(int)(Math.random()*100));
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
    drawParty(enemies,2,true); //check indexing?
    //draw enemy party
    
    drawText(Text.colorize("Party actions (party below)",Text.BOLD,Text.GREEN+Text.BRIGHT)
    ,7,2);
    drawText(Text.colorize("Enemy actions (enemies above)",Text.BOLD,Text.RED+Text.BRIGHT),7,42);

    Text.go(29,2);
    Text.showCursor();
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

  public static void run(){ //what happens to party size after dying?
    //Clear and initialize
    //?? made text 78 wide, correct?
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
    else if(x==2) {
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer());
    enemies.add(createRandomAdventurer()); //wtv swapped but works
    }
    else {
      Boss newBoss = new Boss("Gordon"+(int)(Math.random()*100)); //as in gordon ramsay
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
    

    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      if(whichPlayer>=party.size()) {
        whichPlayer=0;
        partyTurn = false; //??? can we do this?
      }
      if(whichOpponent>=enemies.size()) {
        whichOpponent=0;
        partyTurn = true; //??? can we do this?
      }
      String preprompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
    drawText(preprompt,28,2); //??? when time, can make prompts bold? ALL PROMPTS
    //Read user input
      input = userInput(in);

      //example debug statment
      //TextBox(24,2,1,78,"input: "+input+" partyTurn:"+partyTurn+ " whichPlayer="+whichPlayer+ " whichOpp="+whichOpponent );

      //display event based on last turn's input
      if(partyTurn){//??? broken attack x3 from one side???
        if(!(party.get(whichPlayer).getHP()==0)){

        //TextBox(int row, int col, int width, int height, String text)
        //Process user input for the last Adventurer:
        if(input.equals("attack") || input.equals("a")){ //?? indexing off track?
          TextBox(7,2,38,10,party.get(whichPlayer).attack(enemies.get(whichOpponent)));
        }

        else if(input.equals("special") || input.equals("sp")){
          TextBox(7,2,38,10,party.get(whichPlayer).specialAttack(enemies.get(whichOpponent)));
        }

        else if(input.startsWith("su ") || input.startsWith("support ")){
          int playerSupported = Integer.parseInt(in.next());

          if(playerSupported==whichPlayer) {
            TextBox(7,2,38,10,party.get(whichPlayer).support());
          }
          else {
            TextBox(7,2,38,10,party.get(whichPlayer).support(party.get(playerSupported)));
          }
        }
        }

        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++; //?????
        if(whichOpponent >= enemies.size()) {
          partyTurn = true;
          whichOpponent = 0;
        }


        if(whichPlayer < party.size()){
          //This is a player turn.
          //Decide where to draw the following prompt:
          String prompt = "Enter command for "+party.get(whichPlayer)+": attack/special/quit";
          drawText(prompt,28,2);


        }else{
          //This is after the player's turn, and allows the user to see the enemy turn
          //Decide where to draw the following prompt:
          String prompt = "press enter to see monster's turn";
          TextBox(7,41,38,10,"                                      ");
          drawText(prompt,28,2);

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
        if(!(enemies.get(randomEnemy).getHP()==0)){
        if(!(party.get(randomP).getHP()==0)){ //??? assumes aliveness
        
        if(randomIndex==0) {
          TextBox(7,42,38,10,enemies.get(randomEnemy).attack(party.get(randomP)));
        }
        else if(randomIndex==1) {
            TextBox(7,42,38,10,enemies.get(randomEnemy).specialAttack(party.get(randomP)));
        }
        else if(randomIndex==2) {
          int randomE = (int)(Math.random()*enemies.size());
          if(randomE == randomEnemy) {
            TextBox(7,42,38,10,enemies.get(randomEnemy).support());
          }
        else {
          TextBox(7,42,38,10,enemies.get(randomEnemy).support(enemies.get(randomE))); //other support
        }

        //Decide where to draw the following prompt:
        String prompt = "enemy's turn: press enter to see next turn"; //??? note to self do this!
        TextBox(28,2,78,10,"                                                                              ");
        drawText(prompt,28,2); //??? how to show enemies actions
        whichOpponent++;

      }//end of one enemy.
    }
    else {
      for (int i = 0; i < party.size(); i++) {
        if (party.get(i).getHP() > 0) {
            randomP = i;
            break;
        }
    }

      if(randomIndex==0) {
        TextBox(7,42,38,10,enemies.get(randomEnemy).attack(party.get(randomP)));
      }
      else if(randomIndex==1) {
          TextBox(7,42,38,10,enemies.get(randomEnemy).specialAttack(party.get(randomP)));
      }
      else if(randomIndex==2) {
        int randomE = (int)(Math.random()*enemies.size());
        if(randomE == randomEnemy) {
          TextBox(7,42,38,10,enemies.get(randomEnemy).support());
        }
      else {
        TextBox(7,42,38,10,enemies.get(randomEnemy).support(enemies.get(randomE))); //other support
      }
    }
  }
}
else {
  for (int i = 0; i < enemies.size(); i++) {
    if (party.get(i).getHP() > 0) {
        randomEnemy = i;
        break;
    }
}
if(!(party.get(randomP).getHP()==0)){
  for (int i = 0; i < party.size(); i++) {
    if (party.get(i).getHP() > 0) {
        randomP = i;
        break;
    }
  }
}

if(randomIndex==0) {
  TextBox(7,42,38,10,enemies.get(randomEnemy).attack(party.get(randomP)));
}
else if(randomIndex==1) {
    TextBox(7,41,38,10,enemies.get(randomEnemy).specialAttack(party.get(randomP)));
}
else if(randomIndex==2) {
  int randomE = (int)(Math.random()*enemies.size());
  if(randomE == randomEnemy) {
    TextBox(7,42,38,10,enemies.get(randomEnemy).support());
  }
else {
  TextBox(7,42,38,10,enemies.get(randomEnemy).support(enemies.get(randomE))); //other support
} //??? why dont these work
}
String prompt = "enemy's turn: press enter to see next turn"; //??? note to self do this!
        TextBox(28,2,78,10,"                                                                              ");
        TextBox(28,2,78,10,prompt);
}

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

      for(int i = 0; i < party.size(); i++) {
        if(party.get(i).getHP()<=0) { //??? any death messages? 
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
      drawScreen(party,enemies);
      if(party.size()==0||enemies.size() == 0){ //??? logic?
        drawScreen(party, enemies);
        drawText("Game over. Press enter to quit",28,2); //??? this prints out of the bounds lol
        break;
      }
    }//end of main game loop

    //After quit reset things:
    quit();
  }
}
}

//Enemy actions (enemies above)
//Party actions (party below)