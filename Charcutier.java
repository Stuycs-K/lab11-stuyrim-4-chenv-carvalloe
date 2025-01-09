public class Charcutier extends Adventurer{
  int sausage, sausageMax;

  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Charcutier(String name, int hp){
    super(name,hp);
    sausageMax = 20;
    sausage = sausageMax/2;
  }

  public Charcutier(String name, int hp){
    this(name,hp);
  }

  public Charcutier(String name){
    this(name,20);
  }

  public Charcutier(){
    this("Carmack");
  }

  /*The next 8 methods are all required because they are abstract:*/
  public String getSpecialName(){
    return "sausage";
  }

  public int getSpecial(){
    return sausage;
  }

  public void setSpecial(int n){
    if() {
      sausage = n;
    }
    else {
      System.out.println("set special over the max");
    }
  }

  public int getSpecialMax(){
    return sausageMax;
  }

  /*Deal 2-7 damage to opponent, restores 2 caffeine*/
  public String attack(Adventurer other){
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
  }
  /*Restores 6 special and 1 hp to self.*/
  public String support(){
  }
}
