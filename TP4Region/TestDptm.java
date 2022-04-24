public class TestDptm {
    public static void main(String[] arg) {

        System.out.print(" dptm d1 : ");
        Departement d1 = new Departement("1", "Ain", 603827, 5762);
        System.out.println(d1.toString());

        System.out.print(" dptm d2 : ");
        Departement d2 = new Departement("27", "Eure", 588111, 6040);
        System.out.println(d2.toString());

        System.out.print(" dptm d3 : ");
        Departement d3 = new Departement("76", "Seine-Maritime", 1251282, 6278);
        System.out.println(d3.toString());

    }
}