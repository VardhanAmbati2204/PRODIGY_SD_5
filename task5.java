import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task5 {
    public static void main(String[] args) {
        String url = "https://www.example-ecommerce-website.com/products";
        
        try {
            Document doc = Jsoup.connect(url).get();
            Elements products = doc.select(".product"); // Assuming each product has a class named 'product'

            List<String[]> data = new ArrayList<>();
            data.add(new String[]{"Product Name", "Price", "Rating"}); // CSV header

            for (Element product : products) {
                String name = product.select(".product-name").text(); // Assuming the product name has a class 'product-name'
                String price = product.select(".product-price").text(); // Assuming the price has a class 'product-price'
                String rating = product.select(".product-rating").text(); // Assuming the rating has a class 'product-rating'

                data.add(new String[]{name, price, rating});
            }

            writeDataToCSV(data);
            System.out.println("Data extraction complete. Check the CSV file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeDataToCSV(List<String[]> data) {
        String filePath = "products.csv";
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
