import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class createJsonFile {
    private Scanner scanner = new Scanner(System.in);
    private String name, info;

    /**
     * This constructor is to call all the methods used to set up a file.
     */
    public void createJsonFileNormal() {
        System.out.println("Enter the first name of this file");
        setName();
        System.out.println("Enter the Introduction of the document");
        setInfo();
        setFile(name, info);
    }

    /**
     * This is to set the introduction from the user.
     */
    public void setInfo() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            info = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
        //if there is nothing in the variable.
        if (info.isEmpty()) {
            info = null;
        }
    }

    /**
     * This method is to set the name for the file.
     */
    public void setName() {
        name = scanner.next();
        name = name.toLowerCase();
    }

    /**
     * This is to set the Json file together with name, date and information.
     */
    public void setFile(String name, String info) {
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        try {
            Writer writer = new FileWriter(name + "_passwordRecord" + ".json");
            JsonGenerator generator = factory.createGenerator(writer);

            generator.writeStartObject();
            generator.write("name", name);
            generator.write("date", new Date().toString());
            generator.write("Introduction", info);
            //close the generator and writer.
            generator.writeEnd();
            generator.close();
            writer.close();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }




}
