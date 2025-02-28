class Tableau{
    static int [] T= new int[10];
    static void loadT(int n){

        try {
            if (n>10) throw new IndexOutOfBoundsException();
            System.out.println("Charg. de "+n+" entiers dans T") ;
            for (int i = 0; i < n; i++)
                T[i] = i * 10;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOut");
        }
    }
    public static void showT(int n){
        try {
            for (int i = 0; i < n; i++)
                System.out.println(T[i]);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("PB") ;
        }
    }
}
public class TestException2{
    public static void main(String[]args){
        Tableau.loadT(10);
        Tableau.showT(10);
        System.out.println("FIN PROG") ;

    }
}