public class Charcutier extends Adventurer{
  int caffeine, caffeineMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Charcutier(String name, int hp, String language){
    super(name,hp);
    caffeineMax = 12;
    caffeine = caffeineMax/2;
    preferredLanguage = language;
  }

  public Charcutier(String name, int hp){
    this(name,hp,"c++");
  }

  public Charcutier(String name){
    this(name,24);
  }

  public Charcutier(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "caffeine";
  }

  public int getSpecial(){
    return caffeine;
  }

  public void setSpecial(int n){
    caffeine = n;
  }

  public int getSpecialMax(){
    return caffeineMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
    int damage = (int)(Math.random()*6)+2;
    other.applyDamage(damage);
    restoreSpecial(2);
    return this + " attacked "+ other + " and dealt "+ damage +
    " points of damage. They then take a sip of their coffee.";
  }

  /*Sausage Sling: Creates a barrage of sausages across the field,
  reducing all players (opponent/ally) by x HP
  (where x is a random number from 1-5). Uses 4 sausage.
  */
  public String specialAttack(Adventurer other){
    if(getSpecial() >= 4){
      setSpecial(getSpecial()-4);
      int damage = (int)(Math.random()*3.5+Math.random()*3.5)+1;//
      other.applyDamage(damage);
      this.applyDamage(damage);
      if(this.getHP() <= 0) {
        this.setHP(0);
        if(other.getHP() <= 0) {
        return this + " lost their life to the cured meat barrage, taking " +other " with them.";
      }
      return this + " lost their life to the cured meat barrage."
      }
      return this + " used their sausages to launch a barrage. This dealt "+other+" and " + this + damage +" points of damage.";
    }else{
      return "Not enough sausages to barrage friends and foes. Instead "+attack(other);
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
