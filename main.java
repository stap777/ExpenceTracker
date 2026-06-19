import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;




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

    public Expences(double a, String b, String c, LocalDateTime timestamp) {
        this.amount = a;
        this.discription = b;
        this.category = c;
        this.timestamp = timestamp;

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

        HashSet<String> categories = new HashSet<>();

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
            System.out.println(" : Rs. " + total);
        }

        else if (c == 2) {
            

            for (int i = 0; i < expences.size(); i++) {
                categories.add(expences.get(i).category);
            }

            for (String category : categories) {

                double total = 0;

                for (int i = 0; i < expences.size(); i++) {

                    if (expences.get(i).category.equalsIgnoreCase(category)) {
                        total += expences.get(i).amount;
                    }
                }

                System.out.println(category + ": Rs. " + total);
            }
        }
    }

    void saveFile() {
        try {
            FileWriter fw = new FileWriter("expences.txt");

             for (int i = 0; i < expences.size(); i++) {
                Expences e = expences.get(i);
                String line = e.amount + "|" + e.discription + "|" + e.category + "|" + e.timestamp;
                fw.write(line + "\n");
            }

            fw.close();
        }
        catch (IOException e) {
            System.out.println("error occured ");
        }
        

    }

    void loadFile() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("expences.txt"));
            String line;

            while ((line = bf.readLine()) != null) {
                String[] parts = line.split("\\|");
                double amount = Double.parseDouble(parts[0]);
                Expences e = new Expences(amount, parts[1], parts[2], LocalDateTime.parse(parts[3]));
                expences.add(e);
            }
            bf.close();
        }
        catch (IOException e) {
            System.out.println("Error occured");
        }
    }
}

class main {

    public static void main(String [] args) {

        Scanner sc = new Scanner(System.in); 

        ExpenceManager em = new ExpenceManager();

        em.loadFile();

        ArrayList<String> menu = new ArrayList<>();

        menu.add("(1) add expence");
        menu.add("(2) view expence");
        menu.add("(3) remove expence");
        menu.add("(4) Total Expence");
        menu.add("(5) Exit");


        
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
            else if (c == 5) {
                em.saveFile();
                break;
            }
            else {
                System.out.println("invald input");
            }

        }
    }
}