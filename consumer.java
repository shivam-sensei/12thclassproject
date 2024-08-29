import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class consumer extends shopkeeper {
    private String dynamic[];
    private int counter;

    public consumer(int length) {
        dynamic = new String[length];
    }

    public static void main(String[] args) {
        consumer dyn = new consumer(1);
        Scanner sc = new Scanner(System.in);
        System.out.println("are you shopkeeper? [y/n]");
        String role = sc.nextLine();
        if (role.equalsIgnoreCase("y")) {
            shopkeeper.main(args);
            System.exit(0);
        }
        System.out.println("your name?");
        String name = sc.nextLine();
        System.out.println("From where do you want to order");
        File brand = new File("bname.txt");
        try {
            Scanner bran = new Scanner(brand);
            while (bran.hasNextLine()) {
                String line = bran.nextLine();
                System.out.println(line);
            }
            bran.close();
            int choice = sc.nextInt();
            sc.nextLine();
            String items = lineString("item.txt", choice);
            String coupon = lineString("coupon.txt", choice);
            String gst = lineString("gst.txt", choice);
            String[] ar = items.split(", ");
            System.out.println("what would you like to have");
            for (int i = 0; i < ar.length; i++) {
                String j = Integer.toString(i + 1);
                System.out.println(j + "." + ar[i] + "rs");
            }
            boolean opt = true;
            double total = 0;
            while (opt == true) {
                System.out.println("type 0 when items selected");
                System.out.println("pls enter item number");
                int itemnumber = sc.nextInt();
                if (itemnumber == 0) {
                    break;
                }
                String[] fud = ar[itemnumber - 1].split("=");
                int price = Integer.parseInt(fud[1]);
                System.out.println("how many you want");
                int amount = sc.nextInt();
                total += price * amount;
                dyn.insert(fud[0] + "\t \t" + (price * amount));
            }
            System.out.println("Do you have a coupon [y/n]");
            char c = sc.next().charAt(0);
            sc.nextLine();
            if (c == 'y') {
                System.out.println("enter your coupon code");
                String copon = sc.nextLine();
                if (copon.equals(coupon)) {
                    total = total - (0.1 * total);
                }
            } else {
                System.out.println("proceeding with the bill");
            }
            System.out.println(name + "'s BILL:");
            dyn.printArray();
            if (c == 'y') {
                System.out.println("discount:\t \t 10%");
            }
            if (gst == "y") {
                total = total + (0.18 * total);
                System.out.println("GST applied 18%");
            }
            System.out.println("Your total: \t" + total + "rs");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lineString(String file, int line) {
        File it = new File(file);
        String lineString = "";
        try {
            Scanner fileScanner = new Scanner(it);
            for (int i = 1; i < line; i++) {
                fileScanner.nextLine();
            }
            lineString = fileScanner.nextLine();
            fileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineString;
    }

    public void printArray() {
        for (int i = 0; i < counter; i++) {
            System.out.println(dynamic[i]);
        }
    }

    public void insert(String element) {

        if (dynamic.length == counter) {
            String newd[] = new String[2 * counter];
            for (int i = 0; i < counter; i++) {
                newd[i] = dynamic[i];
            }
            dynamic = newd;
        }
        dynamic[counter++] = element;
    }
}
