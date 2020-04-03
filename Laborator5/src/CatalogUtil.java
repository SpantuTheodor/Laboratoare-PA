import java.awt.*;
import java.io.*;
import java.net.URI;

public class CatalogUtil {
    public static void save(Catalog catalog)
            throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(catalog.getPath()))) {
            oos.writeObject(catalog);
        }
    }

    public static Catalog load(String path)
            throws InvalidCatalogException, IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(path))) {
            Catalog catalog = (Catalog) ois.readObject();
            return catalog;
        }
    }

    public static void view(Document doc) {
        Desktop desktop = Desktop.getDesktop();
        String location = doc.getLocation();
        if (location.startsWith("https://")) {
            try {
                desktop.browse(URI.create(location));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(location);
            if (file.exists()) {
                try {
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}