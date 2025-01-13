public class Apprentice extends Adventurer{
  int sauce, sauceMax;

  private String[] prepItems = new String[]{"coffee beans", "sliced cherry tomatoes", "sushi rice"};

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Apprentice(String name, int hp){

    super(name,hp, supported, supportTurns); //permission to go over maxHP??
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
    other.applyDamage(damage);
    return "PREP WORK:" + this + " attacked "+ other + " and dealt "+ damage +
    " points of damage.";
  }

  /*Deal 3-12 damage to opponent, only if sauce is high enough.
  *Reduces sauce by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() == 1){
      int x = this.getHP();
      this.setHP(other.getHP());
      other.setHP(x);
      return "RECIPE SWAP" + this + " swapped HP with "+ other.getName()+ ". " + this + "new HP is " + this.getHP() + ". " + other + "new HP is " + other.getHP();
      }else{
      return "Not enough sauce to use the ultimate code. Instead "+attack(other);
    }

  }
  public String support(Adventurer other){
    int x = (int)((Math.random() * 4) + 1);
    other.setHP(x);
    return "LAST MINUTE FIX: gives sauce to "+other+" and restores HP to " + other.getHP();
  }

  public String support(){
    if (getHP() + 2 >= 15)
    {
      setHP(15);
      return "STOCK RELIEF: stock full";
    }
    else{
    setHP(getHP()+this.getHP());
    return this+" STOCK RELIEF: "+this.getHP()+" HP";
  }
  }
}
