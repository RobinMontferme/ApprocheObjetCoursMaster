package fr.ubordeaux.ao.infra;

import fr.ubordeaux.ao.domain.*;

import org.json.*;
import java.io.*;


public class BasketJsonRepository implements BasketRepository {

    private String jsonPath = "jsonRep.json";

    public void save(Basket b) {
        JSONObject basket = new JSONObject();
        JSONArray cmdLines = new JSONArray();
        
        for (CommandLine c : b.getCommandLines().values()) {
            JSONObject singleCmdLine = new JSONObject();

            JSONObject singleRef = new JSONObject();
            singleRef.put("ref", c.getReference().ref.value);
            singleRef.put("name", c.getReference().name.value);
            singleRef.put("desc", c.getReference().desc.value);
            singleRef.put("price", c.getReference().price);

            singleCmdLine.put("reference", singleRef);
            singleCmdLine.put("quantity", c.getQuantity());

            cmdLines.put(singleCmdLine);
        }
        basket.put("cmdLines", cmdLines);
        basket.put("id", b.getId());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonPath));
            writer.write(basket.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Basket load(String id) {
        StringBuilder jsonContent = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(jsonPath));
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                jsonContent.append(currentLine);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.toString();
        JSONObject obj = new JSONObject(jsonString);
        
        Basket loadedBasket = new Basket(obj.getString("id"));

        JSONArray loadedCmdLines = obj.getJSONArray("cmdLines");
        for(int i = 0; i< loadedCmdLines.length();i++) {
            JSONObject  singleLoadedCmdLine = loadedCmdLines.getJSONObject(i);
            
            int tmpQuantity = singleLoadedCmdLine.getInt("quantity");
            
            JSONObject loadedReference = singleLoadedCmdLine.getJSONObject("reference");
            
            RefString tmpRef = new RefString(loadedReference.getString("ref"));
            NameString tmpNS = new NameString(loadedReference.getString("name"));
            DescriptionString tmpDS = new DescriptionString(loadedReference.getString("desc"));
            int tmpPrice = loadedReference.getInt("price");
            Reference tmpReference = new Reference(tmpRef,tmpNS,tmpDS,tmpPrice);

            loadedBasket.addRefAndQty(tmpReference,tmpQuantity);
        }   
        
        return loadedBasket;
    }
}