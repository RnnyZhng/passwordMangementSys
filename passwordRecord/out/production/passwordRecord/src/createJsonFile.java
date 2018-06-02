import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class createJsonFile {
    private Scanner scanner = new Scanner(System.in);
    private String name;

    public createJsonFile() {
        System.out.println("Enter the owner of this file");
        setName();

        setFile();
    }

    /**
     * This method is to set the name for the file.
     */
    public void setName() {
        name = scanner.next();
        name += "_PasswordRecord";
    }

    public void setFile() {
        JsonGeneratorFactory factory = Json.createGeneratorFactory(null);
        try {
            Writer writer = new FileWriter(name + ".json");
            JsonGenerator generator = factory.createGenerator(writer);

            generator.writeStartObject();
            generator.write("name", name);
            generator.writeEnd();
            generator.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}
