public class Charcutier extends Adventurer{
  int sausage, sausageMax;


  //do we need a death message?
  /*the other constructors ultimately call the constructor
  *with all parameters.*/
  public Charcutier(String name, int hp){
    super(name,hp);
    this.changeSupportedTurns(0);
    setmaxHP(hp);
    sausageMax = 20;
    sausage = sausageMax/2;
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
    if(n <= sausageMax) {
      sausage = n;
    }
    else {
      System.out.println("set special over the max");
    }
  }

  public int getSpecialMax(){
    return sausageMax;
  }

  /*
  Unleashes a dark cloud of smoke to confuse the opponent.
  50-50 chance to cause 50% damage/3 HP points of damage to
  yourself or selected opponent, whichever is greater.
  */
  public String attack(Adventurer other){
    int random = (int)(Math.random()*2);
    if(random == 0) {
    if(other.getHP() <= 6) {
      other.setHP(other.getHP()-3);
      return "SMOKED STRIKE: A dark cloud of smoke has confused " + other + "! " + "Dealt 3 HP points of damage to " + other;
    }
    else {
      int k = other.getHP()-other.getHP()/2;
      other.setHP(other.getHP()/2);
      return "SMOKED STRIKE: A dark cloud of smoke has confused " + other + "! " + k + " HP points of damage to " + other;
    }
  }
  else {
    if(this.getHP() <= 6) {
      this.setHP(this.getHP()-3);
      return "SMOKED STRIKE: A dark cloud of smoke has confused " + this + "! Dealt 3 HP points of damage to " + this;
    }
    else {
      int k = other.getHP()-other.getHP()/2;
      other.setHP(other.getHP()/2);
      return "SMOKED STRIKE: A dark cloud of smoke has confused " + this + "! " + "Dealt " + k + " HP points of damage to " + this;
    }
  }
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
        return "SAUSAGE SLING: " + this + " lost their life to the cured meat barrage, taking " +other + " with them.";
      }
      return "SAUSAGE SLING: " +  this + " lost their life to the cured meat barrage.";
      }
      return "SAUSAGE SLING: " + this + " used their sausages to launch a barrage. This dealt "+other+" and " + this + damage +" points of damage.";
    }else{
      return "Not enough sausages to barrage friends and foes. Instead "+attack(other);
    }

  }
  /*Cured Edge: Heals x HP points and x/2
  special points on a selected ally,
  where, if y=your selected number from 1-5,
  and z=random number from 1-5, x=(y)*(z/y+1),
  and integer division is used (up to maximums).*/
  public String support(Adventurer other){
  int y = (int) (Math.random()*5)+1;
  int z = (int) (Math.random()*5)+1;
  int l = (z/y + 1);
      int x = y*l;
      int restoredHealth;
      int restoredSpecial;
      if(other.getHP() + x < other.getmaxHP()) {
restoredHealth = x;
      }
      else {
restoredHealth = other.getmaxHP()-other.getHP();
      }
      if(other.getSpecial() + x/2 < other.getSpecialMax()) {
restoredSpecial = x/2;
        }
        else {
 restoredSpecial = other.getSpecialMax()-other.getSpecial();
        }
      return "CURED EDGE: restored " + restoredHealth + " HP points and " + restoredSpecial +
      " special points for " + other + "!";
  }

  /*Marination: Randomly restores 20% of your
  special or your HP points (up to maximums).*/
  public String support(){
    int k = (int) (Math.random()*2);
    if(k == 1) {
      int r = 2*this.getSpecialMax()/10;
      if(r+this.getSpecial() < this.getSpecialMax()) {
        this.setSpecial(this.getSpecial()+r);
        return "" + this + " used Marination! Replenished " + r +" sausages!";
      }
      else {
        int l = this.getSpecial();
        this.setHP(this.getSpecialMax());

        return "" + this + " used Marination! Replenished " + (this.getSpecialMax()-l) + " sausages!";
      }
    }
    else {
      int r = 2*this.getmaxHP()/10;
      if(r+this.getHP() < this.getmaxHP()) {
        this.setHP(this.getHP()+r);
        return "" + this + "used Marination! Healed " + r + " HP points!";
      }
      else {
        int l = this.getHP();
        this.setHP(this.getmaxHP());

        return "" + this + "used Marination! Healed " + (this.getmaxHP()-l) + " HP points!";
      }
    }
  }
}
