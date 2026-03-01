public class Main {
    public static void main(String[] args) {

        Location locA = new Location("Iasi", "oras", 10, 20);
        Location locB = new Location("Bucuresti", "oras", 50, 80);
        System.out.println(locA);
        System.out.println(locB);
        Road drum1 = new Road(locA, locB, "autostrada", 100, 130);
        System.out.println(drum1);

        Location locC = new Location("Constanta", "oras", 70, 10);
        Location locD = new Location("Arad", "oras", 20, 60);
        System.out.println(locC);
        System.out.println(locD);
        Road drum2 = new Road(locC, locD, "drum national", 90, 100);
        System.out.println(drum2);
    }

}
