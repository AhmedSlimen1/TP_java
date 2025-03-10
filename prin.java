import java.util.Arrays;
class Tri extends Thread {
    int[] T;
    public Tri(int[] T) {
        super();
        this.T = T;
    }
    public void run() {
        try {
            triABullesAvecPause(T);
            System.out.println("Tableau trié :" + Arrays.toString(T));
        } catch (InterruptedException e) {
            throw new RuntimeException("Le tri a été interrompu.", e);
        }
    }
    // Implémentation du tri à bulles avec une pause entre chaque échange
    private void triABullesAvecPause(int[] arr) throws InterruptedException {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
// Échanger les éléments
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
// Afficher l'état du tableau après chaque échange
                    System.out.println("Étape du tri :\t\t\t\t\t\t\t" + Arrays.toString(arr));
// Pause de 500 ms entre chaque échange
                    Thread.sleep(1000);
                }
            }
        }
    }
}
class chargement extends Thread {
    int[] T;
    public chargement(int[] T) {
        super();
        this.T = T;
    }
    public void run() {
        try {
            chargerValeurs();
        } catch (InterruptedException e) {
            throw new RuntimeException("Le chargement a été interrompu.", e);
        }
    }
    // Chargement des valeurs avec gestion des interruptions
    private void chargerValeurs() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            if (T[i]!=0)continue;
            T[i] = (int) (Math.random() * 100); // Générer un nombre entre 0 et 9
            System.out.println("Charger T[" + i + "] :" + T[i]+ " => " + Arrays.toString(T));
// Pause de 500 ms entre chaque chargement
            Thread.sleep(1000);
        }
    }
}
class prin {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t\t\t\tCHARGEMENT \t\t\t TRI");
        int[] T = new int[5];
        new chargement(T).start();
        new Tri(T).start();
    }
}