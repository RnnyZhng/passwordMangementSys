import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.*;
import java.util.Scanner;

public class addInfo {
    private Scanner scanner = new Scanner(System.in);
    private String title, username, password, notice, inputFile, outputFile;

    public addInfo(String inputFile) {
        this.inputFile = inputFile + "_passwordRecord.json";
        outputFile = "output.json";

        System.out.println("Enter the company Name you want to add.");
        setTitle();

        System.out.println("Enter the username you want to add.");
        setUsername();

        System.out.println("Enter the password you want to add.");
        setPassword();

        System.out.println("Enter anything you want to notice.");
        setNotice();

        setPersonalInfo();
    }

    public void setTitle() {
        title = scanner.next();
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
        notice = scanner.next();
        if (notice.isEmpty()) {
            notice = null;
        }
    }

    public void setPersonalInfo() {
        JsonParserFactory factory = Json.createParserFactory(null);
        try {
            Reader reader = new FileReader(inputFile);
            JsonParser parser = factory.createParser(reader);

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
                        generator.write(name, "***" + parser.getString());
                        if (name.equals("notice")) {
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
            generator.write("title", title);
            generator.write("username", username);
            generator.write("password", password);
            generator.write("notice", notice);
            generator.writeEnd();

            //close the JPA.
            generator.writeEnd();
            generator.close();
            writer.close();

        } catch (FileNotFoundException e ) {
            System.out.println("cannot find the file");
        } catch (IOException f) {
            System.out.println("Input/Output Invalid.");
        }

        File oldFile = new File(outputFile);
        oldFile.renameTo(new File(this.inputFile));
    }

}
