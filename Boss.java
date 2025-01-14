public class Boss extends Adventurer{
  int salt, saltMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Boss(String name, int hp){
    super(name,hp);
    setmaxHP(40);
    if(hp > 40) {
      setHP(40);
    }
    saltMax = 25;
    salt = saltMax/2;
  }


  public Boss(String name){
    this(name,40);
  }

  public Boss(){
    this("Gordon");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "salt";
  }

  public int getSpecial(){
    return salt;
  }

  public void setSpecial(int n){
    if(n <= saltMax) {
      salt = n;
    }
    else {
      System.out.println("set special over the max");
    }
  }

  public int getSpecialMax(){
    return saltMax;
  }

  public String attack(Adventurer other){
    int damage = (int)(Math.random()*10)+1;
    other.applyDamage(damage);
    this.applyDamage(10 - damage);
    return "HOT OIL SPILL: " + this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. And lost" + (10 - damage) + "points";
  }

  public String specialAttack(Adventurer other){
    if(getSpecial() >= 20){
      other.setSpecial(0);
      this.setSpecial(this.getSpecial() - 20);
      return "IDIOT SANDWICH: " + this + " eliminated "+other.getSpecialName() +
      " of "+other + " and lost 20 " + this.getSpecialName();
    }else{
      return "Not enough salt. Instead "+attack(other);
    }

  }

  public String support(Adventurer other){
    return "Gives a coffee to "+other+" and restores "
    + other.restoreSpecial(5)+" "+other.getSpecialName();
  }

  public String support(){
    int fifty = (int)(Math.random() * 2);
    if (fifty == 0)
    {
      this.restoreSpecial(7);
      this.setHP(this.getHP() + 5);
      return "MICHELIN STAR: celebrity loved your food and gifted you 7 salt and 5 HP points";
    }
    else
    {
      return "MICHELIN STAR REVOKED: celebrity hated your food and left no tip";
    }
  }
}
