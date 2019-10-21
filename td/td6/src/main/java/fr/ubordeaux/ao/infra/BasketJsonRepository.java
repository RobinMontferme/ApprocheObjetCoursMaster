package fr.ubordeaux.ao.infra;

import fr.ubordeaux.ao.domain.*;

import org.json.*;
import java.io.*;

public class BasketJsonRepository implements BasketRepository {

    private String jsonPath = "jsonRep.json";

    public void save(Basket b) {
        JSONArray rep = new JSONArray();
        JSONObject basket = new JSONObject();
        JSONObject content = new JSONObject();
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
        content.put("cmdLines", cmdLines);
        content.put("validated", b.isValidated());
        basket.put("id", b.getId());
        basket.put("content", content);
        rep.put(basket);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonPath));
            writer.write(rep.toString());
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
            while ((currentLine = reader.readLine()) != null) {
                jsonContent.append(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String jsonString = jsonContent.toString();
        JSONArray rep = new JSONArray(jsonString);
        JSONObject basketObj = new JSONObject();
        boolean foundBasket = false;
        int index = 0;
        while (!foundBasket && index < rep.length()) {
            JSONObject singleBasket = rep.getJSONObject(index);
            String singleId = singleBasket.getString("id");
            System.out.println(singleId);
            if (singleId.equals(id)) {
                basketObj = singleBasket;
                foundBasket = true;
            } else {
                index++;
            }

        }
        JSONObject obj = basketObj.getJSONObject("content");

        Basket loadedBasket = new Basket(basketObj.getString("id"));

        JSONArray loadedCmdLines = obj.getJSONArray("cmdLines");
        for (int i = 0; i < loadedCmdLines.length(); i++) {
            JSONObject singleLoadedCmdLine = loadedCmdLines.getJSONObject(i);

            int tmpQuantity = singleLoadedCmdLine.getInt("quantity");

            JSONObject loadedReference = singleLoadedCmdLine.getJSONObject("reference");

            RefString tmpRef = new RefString(loadedReference.getString("ref"));
            NameString tmpNS = new NameString(loadedReference.getString("name"));
            DescriptionString tmpDS = new DescriptionString(loadedReference.getString("desc"));
            int tmpPrice = loadedReference.getInt("price");
            Reference tmpReference = new Reference(tmpRef, tmpNS, tmpDS, tmpPrice);

            loadedBasket.addRefAndQty(tmpReference, tmpQuantity);
        }
        boolean basketValidation = obj.getBoolean("validated");
        if (basketValidation) {
            loadedBasket.validateBasket();
        }
        return loadedBasket;

    }
}