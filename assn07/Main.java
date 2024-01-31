package assn07;

import java.util.Set;
import java.util.Scanner;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> passwordManager = new PasswordManager<>();

        // your code below
        String masterPass = "";
        String pass = "";

        while (!passwordManager.checkMasterPassword(masterPass)) {
            System.out.println("Enter Master Password");
            masterPass = scanner.nextLine();

            while (!pass.equals("Exit") && passwordManager.checkMasterPassword(masterPass)) {
                pass = scanner.nextLine();

                String site = "";
                String password = "";
                int passwordLength = 0;

                switch (pass) {
                    case "New password" -> {
                        site = scanner.nextLine();
                        password = scanner.nextLine();
                        passwordManager.put(site, password);
                        System.out.println("New password added");
                    }
                    case "Get password" -> {
                        site = scanner.nextLine();
                        password = passwordManager.get(site);
                        if (password == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println(password);
                        }
                    }
                    case "Delete account" -> {
                        site = scanner.nextLine();
                        password = passwordManager.remove(site);
                        if (password == null) {
                            System.out.println("Account does not exist");
                        } else {
                            System.out.println("Account deleted");
                        }
                    }
                    case "Check duplicate password" -> {
                        password = scanner.nextLine();
                        List<String> duplicateList = passwordManager.checkDuplicate(password);
                        if (duplicateList.isEmpty()) {
                            System.out.println("No account uses that password");
                        } else {
                            System.out.println("Websites using that password:");

                            for (String site : duplicateList) {
                                System.out.println(site);
                            }
                        }
                    }
                    case "Get accounts" -> {
                        Set<String> accounts = passwordManager.keySet();
                        System.out.println("Your accounts:");
                        for (String account : accounts) {
                            System.out.println(account);
                        }
                    }
                    case "Generate random password" -> {
                        passwordLength = scanner.nextInt();
                        password = passwordManager.generateRandomPassword(passwordLength);
                        System.out.println(password);
                        scanner.nextLine();
                    }
                    case "Exit" -> masterPass = "";
                    default -> System.out.println("Command not found");
                }
            }
        }
    }
}