
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class shopkeeper {
    public static void main(String[] args) {
        boolean copen = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the brand name");
        String brandname = sc.nextLine();
        System.out.println("how many items you want to add");
        int ino = sc.nextInt();
        sc.nextLine();
        String arr[] = new String[ino];
        for (int i = 0; i < ino; i++) {
            System.out.println("enter item name");
            String m = sc.nextLine();
            System.out.println("enter item mrp");
            String n = sc.nextLine();
            arr[i] = m + "=" + n;
        }
        System.out.println("do you want to add gst? [y/n]");
        String gst = sc.nextLine();
        System.out.println("do you want to add coupon? [y/n]");
        String coupon = sc.nextLine();
        String code = "";
        if (coupon.equals("y")) {
            System.out.println("enter coupon code you want to  give");
            code = sc.nextLine();
            copen = true;
        }
        File sn = new File("sno.txt");
        try {
            FileWriter item = new FileWriter("item.txt", true);
            String abc = Arrays.toString(arr).substring(1, Arrays.toString(arr).length() - 1);
            item.write(abc + "\n");
            item.close();
            Scanner serial = new Scanner(sn);
            int t = Integer.parseInt(serial.nextLine());
            t++;
            String temp = Integer.toString(t);
            FileWriter srl = new FileWriter(sn);
            srl.write(temp);
            srl.close();
            serial.close();
            FileWriter brand = new FileWriter("bname.txt", true);
            brand.write(temp + ": " + brandname + "\n");
            brand.close();
            FileWriter gs = new FileWriter("gst.txt", true);
            gs.write(gst + "\n");
            gs.close();
            if (copen == true) {
                FileWriter coup = new FileWriter("coupon.txt", true);
                coup.write(code + "\n");
                coup.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}