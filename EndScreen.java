public class EndScreen {
//CREDITS: https://www.fancytextpro.com/BigTextGenerator FOR THE FANCY TEXT 
    public static void drawWin() { 
        
        String[] win = new String[]{
            "$$\\     $$\\                         $$\\      $$\\ $$\\           $$\\ ", 
            "\\$$\\   $$  |                        $$ | $\\  $$ |\\__|          $$ | ",
            " \\$$\\ $$  /$$$$$$\\  $$\\   $$\\       $$ |$$$\\ $$ |$$\\ $$$$$$$\\  $$ | ",
            "  \\$$$$  /$$  __$$\\ $$ |  $$ |      $$ $$ $$\\$$ |$$ |$$  __$$\\ $$ | ",
            "   \\$$  / $$ /  $$ |$$ |  $$ |      $$$$  _$$$$ |$$ |$$ |  $$ |\\__|",
            "    $$ |  $$ |  $$ |$$ |  $$ |      $$$  / \\$$$ |$$ |$$ |  $$ |    ",
            "    $$ |  \\$$$$$$  |\\$$$$$$  |      $$  /   \\$$ |$$ |$$ |  $$ |$$\\ ",
            "    \\__|   \\______/  \\______/       \\__/     \\__|\\__|\\__|  \\__|\\__|"};
        for(int i = 0; i < 8; i++) {
            Game.TextBox(0, 0, 81, 31, "");
        }
        for(int i = 0; i < 8; i++) {
        Text.go(21+i,1);
        System.out.println(Text.colorize(win[i],Text.BLUE+Text.BRIGHT));
        }
    }
    public static void drawLoss() {

        String[] lose = new String[]{"$$\\     $$\\                         $$\\                            $$\\",     
"\\$$\\   $$  |                        $$ |                           $$ |",
 "\\$$\\ $$  /$$$$$$\\  $$\\   $$\\       $$ |      $$$$$$\\   $$$$$$$\\ $$$$$$\\",
  "\\$$$$  /$$  __$$\\ $$ |  $$ |      $$ |     $$  __$$\\ $$  _____|\\_$$  _|", 
   "\\$$  / $$ /  $$ |$$ |  $$ |      $$ |     $$ /  $$ |\\$$$$$$\\    $$ |",    
    "$$ |  $$ |  $$ |$$ |  $$ |      $$ |     $$ |  $$ | \\____$$\\   $$ |$$\\", 
    "$$ |  \\$$$$$$  |\\$$$$$$  |      $$$$$$$$\\\\$$$$$$  |$$$$$$$  |  \\$$$$  |",
    "\\__|   \\______/  \\______/       \\________|\\______/ \\_______/    \\____/"};
                                                                           
    for(int i = 0; i < 8; i++) {
        Game.TextBox(0, 0, 81, 31, "");
    }
    for(int i = 0; i < 8; i++) {
    Text.go(21+i,1);
    System.out.println(Text.colorize(lose[i],Text.RED+Text.BRIGHT));
    }                                                                    
                                                                           

    }
    public static void main(String[] args) {
        drawWin();
        drawLoss();
    }
}
