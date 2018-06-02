import java.util.Scanner;

public class passWordRecord {
    public static void main(String[] args) {
        System.out.println("Select the operations: a. Search for the password "
                + "b. Add the password e.Update the passWord "
                + "f. Delete The Password ");

        //get the operations from user.
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();

        //process the selection.
        switch (selection.toLowerCase()) {
            case ("a"):
                System.out.println("Search for the password is selected.");
                break;

            case ("b"):
                System.out.println("Add the password");
                break;

            case ("c"):
                System.out.println("Update the password");
                break;

            case ("d"):
                System.out.println("Delete the Password.");
                break;

            case ("e"):
                System.out.println("Create a JSON file");
                createJsonFile file = new createJsonFile();
                break;

            default:
                System.out.println("Invalid Selection");
        }

    }
}
