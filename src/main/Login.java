
package main;

import config.config;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Login {
    public static boolean login(){
        config conf = new config();
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter Email: ");
        String Uemail = sc.nextLine();
        
        System.out.print("Enter Password: ");
        String Upass = sc.nextLine();
        
        String sql = "SELECT * FROM tbl_users WHERE email =? AND password = ?";
        
        List<Map<String, Object>> result = conf.fetchRecords(sql, Uemail, Upass);
        
        if (!result.isEmpty()){
            
            String name = result.get(0).get("name").toString();
            String type = result.get(0).get("type").toString();
            String status = result.get(0).get("status").toString();
            
            if ("Approved".equals(status)) {
            System.out.println("\n\nWelcome " + name + " [" + type + "]\n\n");
            return true; // Login successful and approved
            } else {
                System.out.println("Your account is not approved.");
                return false; // Login successful but status is Pending
            }
        } else {
            System.out.println("INVALID INPUT.");
            return false; // Login failed (bad email/password)
    }     
    }
}
