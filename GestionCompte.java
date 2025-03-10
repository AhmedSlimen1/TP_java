class Compte {
    private int solde = 0;

    // Perform an operation that adds and then removes the same amount
    public void operationNulle(int somme) {
        solde += somme;
        System.out.print(" ajoute " + somme);
        solde -= somme;
        System.out.println(" retire " + somme);
    }

    public int getSolde() {
        return solde;
    }
}

class Operation extends Thread {
    private Compte compte;

    // Constructor that accepts a name and a compte object
    public Operation(String nom, Compte compte) {
        super(nom);
        this.compte = compte;
    }

    public void run() {
        try {
            g();  // Call method g, which is the main loop
            f();  // Call method f, which is the secondary loop
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void f() throws InterruptedException {
        while (true) {
            synchronized (compte) {
                int i = (int) (Math.random() * 10000);
                String nom = getName();
                System.out.print("\nOperation: " + nom);
                compte.operationNulle(i);
                int solde = compte.getSolde();

                // If the solde is not 0, exit with status
                if (solde != 0) {
                    System.out.println("\nOperation " + nom + ": ***** solde=" + solde);
                    System.exit(1);
                }

                // Wake up other threads
                compte.notifyAll();
            }
            Thread.sleep(1000);  // Prevent busy-wait
        }
    }

    public void g() throws InterruptedException {
        while (true) {
            synchronized (compte) {
                int i = (int) (Math.random() * 10000);
                String nom = getName();
                System.out.print("\nOperation: " + nom);
                compte.operationNulle(i);
                int solde = compte.getSolde();

                // If the solde is not 0, exit with status
                if (solde != 0) {
                    System.out.println("\nOperation " + nom + ": ***** solde=" + solde);
                    System.exit(1);
                }

                // Wake up other threads
                compte.notifyAll();
            }
            Thread.sleep(1000);  // Prevent busy-wait
        }
    }
}

public class GestionCompte {
    public static void main(String[] args) {
        Compte compte = new Compte();

        // Start two threads with distinct names
        for (int i = 0; i < 2; i++) {
            Operation operation = new Operation("Thread-" + (char)('A' + i), compte);
            operation.start();
        }
    }
}