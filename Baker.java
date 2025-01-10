public class Baker extends Adventurer{
  int leavener, leavenerMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Baker(String name, int hp){
    super(name,hp);
    setmaxHP(hp);
    leavenerMax = 15;
    leavener = leavenerMax/2;
  }


  public Baker(String name){
    this(name,30);
  }

  public Baker(){
    this("Bob");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "leavener";
  }

  public int getSpecial(){
    return leavener;
  }

  public void setSpecial(int n){
    if(n <= leavenerMax) {
      leavener = n;
    }
    else {
      System.out.println("set special over the max");
    }
  }

  public int getSpecialMax(){
    return leavenerMax;
  }

  /*Deal 3/5 damage to opponent*/
  public String attack(Adventurer other){
    int x = (int)(Math.random()*5);
    if(x!=0) {
    other.applyDamage(3);
    return this + ", using a hot batter-filled cannon ball, attacked "+ other + " and dealt "+ 3 +
    " points of damage.";


  }
  else {
    other.applyDamage(5);
    return this + ", using a hot batter-filled cannon ball, attacked "+ other + " and dealt "+ 3 +
    " points of damage. The impact was so strong it dealt another 2 points of damage.";

  }

  }

  /*Rolling Pin: Reduces the opponent HP or special points by 40%, whichever is greater. Consumes 6 leavener.
  */
  public String specialAttack(Adventurer other){
    if(this.getSpecial()>=6) {
      setSpecial(getSpecial()-6);
    if(4*other.getHP()/10 > 4*other.getSpecial()/10) {
      int l = 4*other.getHP()/10;
      other.setHP(other.getHP()-4*other.getHP()/10);
      return "" + this + " rolls away " + other + "'s HP by " + l
      + ", consuming 6 leavener in the process.";
    }
    else {

      int l = 4*other.getSpecial()/10;
      other.setSpecial(other.getSpecial()-4*other.getSpecial()/10);
      return "" + this + " rolls away " + other + "'s " + other.getSpecialName() + " by " + l
      + ", consuming 6 leavener in the process.";
    }
  }
    else{
      return "Not enough leavener to roll away your foes! Instead "+attack(other);
    }

  }
  //TO BE WRITTEN/MODIFIED
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    if(this.getSpecial()<12) {
      this.setSpecial(this.getSpecial()+3);
      return "" + this + " expertly gathers 3 more leaveners from the kitchen cupboard"
      + ", leading to a total of " + this.getSpecial() + " leaveners.";
    }
    else {
      int l = 15-this.getSpecial();
      this.setSpecial(15);
      return "" + this + " expertly gathers " + l+" more leaveners from the kitchen cupboard"
      + ", leading to a total of " + this.getSpecial() + " leaveners.";
    }
  }
}
