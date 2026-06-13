import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        while (true) {
            if (!expences.isEmpty()) {
                for (int i = 0; i < expences.size(); i++) {
                    System.out.println(i + 1 + ")" + expences.get(i));
                }
            }

            else {
                System.out.println("empty");
            }

            System.out.println(expences.size() + 1 + ") to Search(title)" );

            System.out.println(expences.size() + 2 + ") to filter" );

            System.out.println("(-1) to Exit" );

            int c = 0;
            while (true) {
                try {
                    c = sc.nextInt();
                    sc.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("invalid input");
                    sc.nextLine();
                }
            }
        

            if (c == expences.size() + 1) {
                System.out.println("title: ");
                String s = "";
                while (true) {
                    try {
                        s = sc.nextLine();
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("invalid input");
                        sc.nextLine();
                    }
                }

                boolean isvalid = false;
                for (int i = 0; i < expences.size(); i++) {
                    if (expences.get(i).discription.equalsIgnoreCase(s)) {
                        System.out.println(expences.get(i));
                        isvalid = true;
                        break;
                    }
                }
                if (!isvalid) {
                    System.out.println("not found");
                }
            }

            else if (c == expences.size() + 2) {
                System.out.println("category");
                String s = "";
                while (true) {
                    try {
                        s = sc.nextLine();
                        break;
                    }
                    catch (Exception e) {
                        System.out.println("invalid input");
                        sc.nextLine();
                    }
                }
                for (int i = 0; i < expences.size(); i++) {
                    if (expences.get(i).category.equalsIgnoreCase(s)) {
                        System.out.println(expences.get(i));
                    }
                }
            }
            else if (c == -1) {
                break;
            }
            else {
                System.out.println("invalid input");
            }
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

    void totalExpence(Scanner sc) {
        System.out.println("(1) to calculate Combine total");

        System.out.println("(2) to calculate category wise total");

        int c = 0;
        while (true) {
            try {
                c = sc.nextInt();
                sc.nextLine();
                break;
            }
            catch (Exception e) {
                System.out.println("invalid input type");
                sc.nextLine();
            }
        }

        if (c == 1) {
            double total = 0;
            for (int i = 0; i < expences.size(); i++) {
                total += expences.get(i).amount;
            }
            System.out.println(total);
        }

        else if (c == 2) {
            for (int i = 0; i < expences.size(); i++) {
                int l = 1;
                System.out.println(l + expences.get(i).category);
                l++;
            }
            System.out.print("Select category: ");
            String s = ""; 
            while (true) {
                try {
                    s = sc.nextLine();
                    break;
                }
                catch (Exception e) {
                    System.out.println("invalid input type");
                    sc.nextLine();
                }
            }
            double total = 0;
            for (int i = 0; i < expences.size(); i++) {
                if (expences.get(i).category.equals(s)) {
                    total += expences.get(i).amount;
                }
            }
            System.out.println(total);
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
        menu.add("(4) Total Expence");


        
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

            else if (c == 4) {
                em.totalExpence(sc);
            }

        }
    }
}