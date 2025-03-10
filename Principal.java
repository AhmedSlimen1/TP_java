import java.util.Arrays;

class Chargement extends Thread {
    int[] T;
    public Chargement(int[] T) { this.T = T; }
    public void run() {
        try {
            synchronized (T) { //prendre un accès exclusif sur T
                for (int i = 0; i < T.length; i++) {
                    T[i] = (int) (Math.random() * 100);
                    System.out.println("Charger T[" + i + "] :" + T[i] + " => " + Arrays.toString(T));
                    T.notify(); // Envoyer une notification au thread Moyenne pour le Réveiller
                    T.wait(); // Attendre que le thread Moyenne traite la mise à jour
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Le chargement a été interrompu.", e);
        }
    }
}

class Moyenne extends Thread {
    int[] T;

    public Moyenne(int[] T) {
        this.T = T;
    }

    public void run() {
        try {
            while (true) {
                synchronized (T) { //prendre un accès exclusif sur T une fois libéré par notify
                    T.wait(); // Attendre la notification venant du thread Chargement après la mise à jour de T
                    int somme = 0;
                    for (int valeur : T) somme += valeur;
                    double moyenne = (double) somme / T.length;
                    System.out.println("Moyenne arithmétique :" + moyenne);
                    T.notify(); // Notifier le thread Chargement pour continuer
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("Le calcul de la moyenne a été interrompu.", e);
        }
    }
}

class Principal {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\t\t\t\tCHARGEMENT \t\t\t MOYENNE");
        int[] T = new int[5];
        new Moyenne(T).start();
        new Chargement(T).start();
    }
}