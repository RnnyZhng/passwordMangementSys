import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class addInfo {
    private Scanner scanner = new Scanner(System.in);
    private String title, username, password, notice, inputFile, outputFile, firstName;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public addInfo(String inputFile) {
        firstName = inputFile;
        this.inputFile = inputFile + "_passwordRecord.json";
        outputFile = "output.json";

        System.out.println("Enter the COMPANY/WEBSITE Name you want to add.");
        setTitle();

        System.out.println("Enter the USERNAME you want to add.");
        setUsername();

        System.out.println("Enter the PASSWORD you want to add.");
        setPassword();

        System.out.println("Enter ANYTHING you want to NOTICE.");
        setNotice();

        setPersonalInfo();
    }

    public void setTitle() {
        try {
            title = reader.readLine();
        } catch (IOException e) {
            e.getStackTrace();
        }

        if (title.isEmpty()) {
            title = null;
        }
    }

    public void setUsername() {
        username = scanner.next();
        if (username.isEmpty()) {
            username = null;
        }
    }

    public void setPassword() {
        password = scanner.next();
        if (password.isEmpty()) {
            password = null;
        }
    }

    public void setNotice() {
        try {
            notice = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        if (notice.isEmpty()) {
            notice = null;
        }
    }

    /**
     * This method is to read the exist Json file, and import the new setting.
     */
    public void setPersonalInfo() {
        JsonParserFactory factory = Json.createParserFactory(null);
        try {
            Reader reader = new FileReader(inputFile);
            JsonParser parser = factory.createParser(reader);
            //create a writer for the json
            JsonGeneratorFactory factory2 = Json.createGeneratorFactory(null);
            Writer writer = new FileWriter(outputFile);
            JsonGenerator generator = factory2.createGenerator(writer);

            String name = null;
            while (parser.hasNext()) {
                JsonParser.Event e = parser.next();
                switch (e) {
                    case KEY_NAME:
                        name = parser.getString();
                        break;

                    case VALUE_STRING:
                        generator.write(name, parser.getString());
                        if (name.equals("notice")) {    //if exist notice, end the password object.
                            generator.writeEnd();
                        }
                        break;

                    case START_OBJECT:
                        if (name != null) {
                            generator.writeStartObject(name);
                        } else {
                            generator.writeStartObject();
                        }
                        break;
                }
            }
            reader.close();

            //This is to add the new information.
            generator.writeStartObject(title);
            generator.write("username", username);
            generator.write("password", password);
            generator.write("last updated", new Date().toString());
            generator.write("notice", notice);
            generator.writeEnd();

            //close the JPA.
            generator.writeEnd();
            generator.close();
            writer.close();

        } catch (FileNotFoundException e ) {        //if the file has not already built up and import the setting.
            System.out.println("############################## Creating a personal JSON file 100%.");
            System.out.println("############################## Importing your password to your file 100%");
            createJsonFile creator = new createJsonFile();
            creator.setFile(firstName, "nothing");

            //import the new password.
            setPersonalInfo();
        } catch (IOException f) {
            System.out.println("Input/Output Invalid.");
        }
        // replace the old with new one.
        File oldFile = new File(outputFile);
        oldFile.renameTo(new File(this.inputFile));
    }

}
