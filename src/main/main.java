
package main;

import config.config;
import java.util.Scanner;

public class main {
    
    private static void viewUsers() {
        String votersQuery = "SELECT * FROM tbl_residents";
        String[] votersHeaders = {"ID", "Name", "Age", "Gender", "Address", "Contact"};
        String[] votersColumns = {"r_id", "r_name", "r_age", "r_gender", "r_address", "r_contact"};
        
        config conf = new config();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
    
       // Main Method Here
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int choice;
        boolean isLoggedIn = true; // for Verification
        boolean isOkay = false; // for Approved Account Access

        // Initial Menu Loop: Login, Register, Exit
        while (isLoggedIn) {
            System.out.println("\n\n--- Welcome to the Vaccination System ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            // Input validation
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("\n--- LOGIN ---");
                        if(Login.login()){
                            isLoggedIn = false; // Assume successful login for flow control
                        }
                        break;
                    case 2:
                        System.out.println("\n--- REGISTRATION ---");
                        Register.register();
                        break;
                    case 3:
                        System.out.println("Exiting Program. Goodbye! 👋");
                        sc.close();
                        return; // Terminates the program
                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear the invalid input from the buffer
            }
        }
        
        // Vaccination New Page
        while (!isOkay){
            System.out.println("Vaccination");
            System.out.println("1. Users");
            System.out.println("2. Vaccines (NOT AVAILABLE)");
            System.out.println("3. Records (NOT AVAILABLE)");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
        
            
            switch (choice) {
                case 1:
                    System.out.println("USERS");
                    System.out.println("1. Add Information");
                    System.out.println("2. View Information");
                    System.out.println("3. Update Information");
                    System.out.println("4. Delete Information");
                    System.out.println("5. Exit");
                    System.out.print("Enter Choice: ");
                    int action = sc.nextInt();
                    sc.nextLine();

                    switch (action) {
                        case 1:
                            System.out.print("Enter name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter age: ");
                            String age = sc.nextLine();
                            System.out.print("Enter gender(M/F): ");
                            String gender = sc.nextLine();
                            System.out.print("Enter address: ");
                            String address = sc.nextLine();
                            System.out.print("Enter contact: ");
                            String contact = sc.nextLine();

                            String sql = "INSERT INTO tbl_residents (r_name, r_age, r_gender, r_address, r_contact) VALUES (?, ?, ?, ?, ?)";
                            conf.addRecord(sql, name, age, gender, address, contact);
                            break;
                        case 2:
                            //view users nga function
                            viewUsers();
                            break; 

                        case 3:
                            //update nga function
                            viewUsers();
                            System.out.print("Enter id to update: ");
                            int id = sc.nextInt();

                            System.out.print("Enter new name: ");
                            name = sc.next();

                            System.out.print("Enter new age: ");
                            age = sc.next();

                            System.out.print("Enter new gender: ");
                            gender = sc.next();

                            System.out.print("Enter new address: ");
                            address = sc.next();

                            System.out.print("Enter new contact: ");
                            contact = sc.next();


                            sql = "UPDATE tbl_residents SET r_name = ?, r_age = ?, r_gender = ?, r_address = ?, r_contact = ? WHERE r_id = ?";
                            conf.updateRecord(sql, name, age, gender, address, contact, id);
                            break;

                        case 4:
                            //Delete information
                            config dbConfig = new config();
                            System.out.print("Enter Information ID to delete: ");
                            id = sc.nextInt();

                            String sqlDelete = "DELETE FROM tbl_residents WHERE r_id = ?";
                            dbConfig.deleteRecord(sqlDelete, id);

                            break;

                        case 5:
                            //Exit
                            System.out.println("Exiting Program.");
                            System.exit(0); //mao ni force termination
                            break;
                        default:
                    System.out.println("Invalid option. Please try again.");
                    }
                case 4:
                    System.exit(0); //mao ni force termination
                    break;
            }
        } // while close
    }  // psvm main close
}// public class close