import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Scanner;


class Expences {
    double amount;
    String discription;
    String category;
    LocalDateTime timestamp;

    public Expences(double a, String b, String c) {
        amount = a;
        discription = b;
        category = c;
        timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd MMM yyyy  HH:mm");
        return amount + " | " 
        + discription + " | "  
        + category + " | " 
        + timestamp.format(formater); 
    }
}

class ExpenceManager {

    ArrayList<Expences> expences = new ArrayList<>();

    void addExpence(Scanner sc) {
        double amount = 0;
        while (true) {
            try {
                System.out.println("Amount");
                amount = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch (Exception e) {
                System.out.println("please enter valid amount");
                sc.nextLine();
            }
        }
        
        System.out.println("Discription");
        String discription = sc.nextLine();
        
        System.out.println("Category");
        String category = sc.nextLine();

        Expences e = new Expences(amount, discription, category);

        expences.add(e);
    }

    void viewExpence(Scanner sc) {

        if (!expences.isEmpty()) {
            for (int i = 0; i < expences.size(); i++) {
                System.out.println(i + 1 + ")" + expences.get(i));
            }
        }
        else {
            System.out.println("empty");
        }
    }

    void removeExpence(Scanner sc) {

        for (int i = 0; i < expences.size(); i++) {
            System.out.println(i + 1 + ")" + expences.get(i));
        }
        System.out.println("Remove");
        int r = sc.nextInt();
        sc.nextLine();

        if (r <= expences.size() && r >= 0) {
            expences.remove(r - 1);
        }
        else {
            System.out.println("invalid Expence");
        }
    }



}

class main {

    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in); 

        ExpenceManager em = new ExpenceManager();

        ArrayList<String> menu = new ArrayList<>();

        menu.add("(1) add expence");
        menu.add("(2) view expence");
        menu.add("(3) remove expence");

        
        while (true) {
            for (int i = 0; i < menu.size(); i++) {
                System.out.println(menu.get(i));
            }

            int c = sc.nextInt();
            sc.nextLine();

            if (c == 1) {
                em.addExpence(sc);
            }

            else if (c == 2) {
                em.viewExpence(sc);
            }

            else if (c == 3) {
                em.removeExpence(sc);
            }

        }
    }
}