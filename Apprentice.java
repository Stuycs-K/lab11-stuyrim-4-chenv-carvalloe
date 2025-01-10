public class Apprentice extends Adventurer{
  int sauce, sauceMax;

  private String[] prepItems = new String[]{"coffee beans", "sliced cherry tomatoes", "sushi rice"};

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Apprentice(String name, int hp){

    super(name,hp); //permission to go over maxHP??
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
    int damage = (int)(Math.random()+2);
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then take a sip of their coffee.";
  }

  /*Deal 3-12 damage to opponent, only if sauce is high enough.
  *Reduces sauce by 8.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 8){
      setSpecial(getSpecial()-8);
      int damage = (int)(Math.random()*5+Math.random()*5)+3;
      other.applyDamage(damage);
      return this + " used their "+preferredLanguage+
      " skills to hack the matrix. "+
      " This glitched out "+other+" dealing "+ damage +" points of damage.";
    }else{
      return "Not enough sauce to use the ultimate code. Instead "+attack(other);
    }

  }
  /*Restores 5 special to other*/
  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
    int hp = 1;
    setHP(getHP()+hp);
    return this+" drinks a coffee to restores "+restoreSpecial(6)+" "
    + getSpecialName()+ " and "+hp+" HP";
  }
}
