import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class searchPassword {
    private String userFirstName, title;

    public searchPassword(String userFirstName, String title) {
        this.title = title;
        this.userFirstName = userFirstName;
        serachFile();
    }

    public void serachFile() {
        System.out.println();
        String fileName = userFirstName + "_passwordRecord.json";

        //open the Json file and create a Json parser.
        try {
            JsonParserFactory factory = Json.createParserFactory(null);
            Reader reader = new FileReader(fileName);
            JsonParser parser = factory.createParser(reader);

            //iterate for the whole json file.
            String name;
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                switch (e) {

                    case KEY_NAME:
                        name = parser.getString();
                        if (name.contains(title)) { //if the title match the searching.
                            while (parser.hasNext()) {
                                e = parser.next();
                                switch (e) {
                                    case KEY_NAME:
                                        name = parser.getString();
                                        break;
                                    case VALUE_STRING:
                                        //This is to print out the information.
                                        if (name.equals("username")) {
                                            System.out.println("****************************************");
                                            System.out.println("The username is: " + parser.getString());
                                        } else if (name.equals("password")) {
                                            System.out.println("The password is: " + parser.getString());
                                        } else if (name.equals("notice")){
                                            System.out.println("The notice is: " + parser.getString());
                                            //if the password is found the searching will be stop
                                            return;
                                        }
                                        break;
                                }
                            }
                            //if the title is found, the following loop will stop.
                            return;
                        }
                        break;

                        default:
                            break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Invalid: you should set up first.");
        }
    }

    public void searchTitle() {
        System.out.println("searching the Title.");
    }
}
