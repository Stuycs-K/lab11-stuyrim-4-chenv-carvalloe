public class Apprentice extends Adventurer{
  int sauce, sauceMax;

  private String[] prepItems = new String[]{"coffee beans", "sliced cherry tomatoes", "sushi rice", 
"leftover soup", "stale bread", "apple pie"};

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Apprentice(String name, int hp){

    super(name,hp); //permission to go over maxHP??
    // this.changeSupported(); SHOULD WE CHANGE OR IS IT DEFAULT 0
    this.changeSupportedTurns(0);
    setmaxHP(15); //should we do this
    if(hp > 15) {
      setHP(15);
    }
    sauceMax = 1;
    sauce = 1;
  }


  public Apprentice(String name){
    this(name,15);
  }

  public Apprentice(){
    this("Alice");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "sauce";
  }

  public int getSpecial(){
    return sauce;
  }

  public void setSpecial(int n){
    sauce = n;
  }

  public int getSpecialMax(){
    return sauceMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 sauce*/
  public String attack(Adventurer other){
    int damage = (int)((Math.random()*2) + 1);
    int item = (int) (Math.random()*6);
    other.applyDamage(damage);
    return "PREP WORK: " + this + " attacked "+ other + " using " + prepItems[item] + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if sauce is high enough.
  *Reduces sauce by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() == 1){
      int x = this.getHP();
      if (other.getHP() > this.getmaxHP())
      {
        this.setHP(this.getmaxHP());
      }
      else
      {
      this.setHP(other.getHP());
    }
    if (x > other.getmaxHP())
    {
      other.setHP(other.getmaxHP());
    }
    else{
      other.setHP(x);
    }
    setSpecial(0);
      return "RECIPE SWAP: " + this + " swapped HP with "+ other.getName()+ ". " + this + "'s new HP is " + this.getHP() + ". " + other + "'s new HP is " + other.getHP() + ".";
      }else{
      return "Not enough sauce to use the ultimate code. Instead "+attack(other);
    }

  }
  public String support(Adventurer other){
    int x = (int)((Math.random() * 4) + 1);
    if(other.getHP()+x <= other.getmaxHP()) {
    other.setHP(other.getHP()+x);
    return "LAST MINUTE FIX: " + this + " gives sauce to "+other+" and restores HP to " + other.getHP();
  }
  else {
    x = other.getmaxHP()-other.getHP();
    other.setHP(other.getHP()+x);
    return "LAST MINUTE FIX: gives sauce to "+other+" and restores HP to " + other.getHP();
  }

}
  public String support(){
    if (getHP() + 2 >= 15)
    {
      setHP(15);
      return "STOCK RELIEF: " + this + "'s HP is now full!";
    }
    else{
    setHP(getHP()+this.getHP());
    return this+" STOCK RELIEF: " + this + "'s points is now set to "+this.getHP()+" HP!";
  }
  }
}
