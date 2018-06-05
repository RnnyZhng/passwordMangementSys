import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class passWordRecord {
    public static void main(String[] args) {
        System.out.println("Select the operations: a. Search for the password "
                + "b. Add the password c.Update the passWord "
                + "d. Delete The Password "
                + "e. Create a Json file");

        //get the operations from user.
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();

        //get the user name.
        System.out.println("What is your first name?");
        String userFirstName = scanner.next().toLowerCase();

        //process the selection.
        switch (selection.toLowerCase()) {
            case ("a"):
                System.out.println("The WEBSITE/COMPANY you want to search for password.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String title = "nothing";
                try {
                    title = reader.readLine().toLowerCase();
                    reader.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }

                searchPassword searcher = new searchPassword(userFirstName, title);
                break;

            case ("b"):
                addInfo info = new addInfo(userFirstName);
                break;

            case ("c"):
                System.out.println("Update the password");
                break;

            case ("d"):
                System.out.println("Delete the Password.");
                break;

            case ("e"):
                createJsonFile file = new createJsonFile();
                file.createJsonFileNormal();
                break;

            default:
                System.out.println("Invalid Selection");
        }
        scanner.close();
    }
}
