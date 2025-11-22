package estruturadados.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import estruturadados.io.FileReader;
import estruturadados.io.FileWriter;
import estruturadados.models.PixelCollection;

public class PixelCollectionController {
  PixelCollection pixelCollection;

  public PixelCollectionController() {
    this.pixelCollection = new PixelCollection();
  }
  
    public void saveToJson(String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = gson.toJson(this);

        FileWriter writer = new FileWriter(filePath);
        writer.writeLine(json);
    }

    public static PixelCollection loadFromJson(String filePath) {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filePath);

        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        return gson.fromJson(json.toString(), PixelCollection.class);
    }
}
