import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static <T> void saveList(
            String filePath, List<T> data) {

        try {

            Path parent = Paths.get(filePath).getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            ObjectOutputStream output =
                new ObjectOutputStream(
                    new FileOutputStream(filePath));

            output.writeObject(data);
            output.close();

        } catch (IOException e) {

            System.out.println("Error saving data: "
                    + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> loadList(String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try {

            ObjectInputStream input =
                new ObjectInputStream(
                    new FileInputStream(file));

            List<T> data = (List<T>) input.readObject();

            input.close();

            return data;

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading data: "
                    + e.getMessage());

            return new ArrayList<>();
        }
    }
}
