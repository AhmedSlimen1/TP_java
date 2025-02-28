class Tableau{
    static int [] T= new int[10];
    static void loadT(int n){
        System.out.println("Charg. de "+n+" entiers dans T") ;
            for (int i=0;i<n;i++)
                T[i] = i*10 ;
    }
    public static void showT(int n){
        System.out.println("Affich. de "+n+" entiers de T") ;
        for (int i=0;i<n;i++)

            System.out.println(T[i]) ;

    }
}
public class TestException{
    public static void main(String[]args){
        try {
            Tableau.loadT(15);
        }
        catch (Exception e){
            System.out.println("DEPASSE CAP TAB");
        }
        try {
            Tableau.showT(15);
        }
        catch (Exception e) {
            System.out.println("FIN PROG");
        }
    }
}