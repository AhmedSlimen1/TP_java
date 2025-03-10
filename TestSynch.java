class Numbers {
    synchronized void printNumbers(int n) {  // Synchroniser la méthode
        for (int i = 1; i <= 5; i++) {
            System.out.println(n * i);  // Afficher la table de multiplication
            try {
                Thread.sleep(400);  // Attendre 400 ms entre chaque nombre
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class MyThread1 extends Thread {
    Numbers t;

    MyThread1(Numbers t) {
        this.t = t;
    }

    public void run() {
        t.printNumbers(10);  // Imprimer la table de 10
    }
}

class MyThread2 extends Thread {
    Numbers t;

    MyThread2(Numbers t) {
        this.t = t;
    }

    public void run() {
        t.printNumbers(100);  // Imprimer la table de 100
    }
}

public class TestSynch {
    public static void main(String args[]) {
        Numbers obj = new Numbers();  // Créer un objet Numbers
        MyThread1 t1 = new MyThread1(obj);  // Créer le thread pour la table de 10
        MyThread2 t2 = new MyThread2(obj);  // Créer le thread pour la table de 100
        t1.start();  // Lancer le thread t1
        t2.start();  // Lancer le thread t2
    }
}
