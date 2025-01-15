public class Driver{
  public static void main(String[] args) {
    Adventurer x = new Boss();
    Adventurer y = new Baker();
    System.out.println(x.getHP());
    System.out.println(x.getmaxHP());
    System.out.println(y.support(x));
    System.out.println(y.support());
    System.out.println(y.specialAttack(x));
  }
}
