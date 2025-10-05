
package main;

import config.config;
import java.util.Scanner;

public class Register {
    public static void register() {
        config conf = new config();
        Scanner sc = new Scanner(System.in);
        String type = null;
        boolean isLooping = true;
        
        // ---REGISTRATION FORM---
        System.out.print("Full Name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        
    while(isLooping){    
        // SELECTION OF THE TYPE OF USER
        System.out.println("1. Admin");
        System.out.println("2. Patient");
        System.out.print("Select Type: ");
        String answer = sc.nextLine();
        
        
            switch (answer){
                case "1":
                    type = "Admin";
                    isLooping = false;
                    break;
                case "2":
                    type = "Patient";
                    isLooping = false;
                    break;
                default:
                    System.out.println("Invalid! Again.");
            }
        }
        String sql = "INSERT INTO tbl_users (name, email, password, status, type) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sql, name, email, password, "Pending", type);
    }
}
