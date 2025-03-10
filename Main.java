class NumberPrinter extends Thread {
    private String threadName;

    public NumberPrinter(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        printNumbers();
    }

    // Méthode synchronisée pour éviter le mélange des sorties
    private synchronized void printNumbers() {
        for (int i = 1; i < 5; i++) {
            System.out.println(threadName + ":" + i);
            try {
                Thread.sleep(500); // Petite pause pour simuler le travail et voir la concurrence
            } catch (InterruptedException e) {
                System.out.println(threadName + " a été interrompu.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Créer deux threads avec des noms différents
        NumberPrinter thread1 = new NumberPrinter("Thread-A");
        NumberPrinter thread2 = new NumberPrinter("Thread-B");

        // Démarrer les threads
        thread1.start();
        try {
            thread1.join(); // Attendre que thread1 termine avant de démarrer thread2
        } catch (InterruptedException e) {
            System.out.println("ERRRRRRRR");
        }

        thread2.start(); // Démarrer thread2 après la fin de thread1
        try {
            thread2.join(); // Attendre que thread2 termine avant de terminer main
        } catch (InterruptedException e) {
            System.out.println("Main a été interrompu.");
        }
    }
}
