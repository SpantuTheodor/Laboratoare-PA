import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void testCreateSave() throws IOException {
        Catalog catalog =
                new Catalog("Java Resources", "D:\\catalog.ser");
        Document doc = new Document("java1", "Java Course 1",
                "https://profs.info.uaic.ro/~acf/java/slides/en/");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        CatalogUtil.save(catalog);
    }

    private void testLoadView() throws InvalidCatalogException, IOException, ClassNotFoundException {
        Catalog catalog = CatalogUtil.load("D:\\catalog.ser");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}