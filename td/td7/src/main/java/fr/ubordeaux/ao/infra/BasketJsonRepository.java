package fr.ubordeaux.ao.infra;

import fr.ubordeaux.ao.domain.*;
import java.util.List;
import java.util.ArrayList;
import org.json.*;
import java.io.*;

public class BasketJsonRepository implements BasketRepository {
    public final String ID_KEY="id";
    public final String VALIDATED_KEY="validated";
    public final String CMD_KEY="cmdLines";
    public final String REFERENCE_KEY="reference";
    public final String QTY_KEY="qty";
    public final String REF_KEY="ref";
    public final String NAME_KEY="name";
    public final String DESC_KEY="desc";
    public final String PRICE_KEY="price";
    public final String CONTENT_KEY="content";
    private String jsonPath = "jsonRep.json";

    public void save(Basket b) {
        BasketDAO basketToSave = b.serialize();
        JSONArray rep = new JSONArray();
        JSONObject basket = new JSONObject();
        JSONObject content = new JSONObject();
        JSONArray cmdLines = new JSONArray();

        for (CommandLineDAO c : basketToSave.cmd) {
            JSONObject singleCmdLine = new JSONObject();

            JSONObject singleRef = new JSONObject();
            singleRef.put(REF_KEY, c.ref);
            singleRef.put(NAME_KEY, c.name);
            singleRef.put(DESC_KEY, c.desc);
            singleRef.put(PRICE_KEY, c.price);

            singleCmdLine.put(REFERENCE_KEY, singleRef);
            singleCmdLine.put(QTY_KEY, c.qty);

            cmdLines.put(singleCmdLine);
        }
        content.put(CMD_KEY, cmdLines);
        content.put(VALIDATED_KEY, basketToSave.validated);
        basket.put(ID_KEY, basketToSave.id);
        basket.put(CONTENT_KEY, content);
        rep.put(basket);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonPath));
            writer.write(rep.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(Basket b) {}
    public BasketDAO load(String id) {
        //Reading JSON
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
        //searching for right basket basket
        String jsonString = jsonContent.toString();
        JSONArray rep = new JSONArray(jsonString);
        JSONObject basketJSON = new JSONObject();
        boolean foundBasket = false;
        int index = 0;
        while (!foundBasket && index < rep.length()) {
            JSONObject singleBasket = rep.getJSONObject(index);
            String singleId = singleBasket.getString(ID_KEY);
            System.out.println(singleId);
            if (singleId.equals(id)) {
                basketJSON = singleBasket;
                foundBasket = true;
            } else {
                index++;
            }

        }
        //data extracting
        String idFromJSON = basketJSON.getString(ID_KEY);
        JSONObject contentJSON = basketJSON.getJSONObject(CONTENT_KEY);
        
        boolean validationFromJSON = contentJSON.getBoolean(VALIDATED_KEY);
        JSONArray cmdLinesJSON = contentJSON.getJSONArray(CMD_KEY);

        List<CommandLineDAO> cmdFromJSON = new ArrayList<CommandLineDAO>();
        for (int i = 0; i < cmdLinesJSON.length(); i++) {
            JSONObject singleCmdJSON = cmdLinesJSON.getJSONObject(i);
            
            int qtyFromJSON = singleCmdJSON.getInt(QTY_KEY);
            
            JSONObject referenceJSON = singleCmdJSON.getJSONObject(REFERENCE_KEY);
            int priceFromJSON = referenceJSON.getInt(PRICE_KEY);
            
            String refFromJSON = referenceJSON.getString(REF_KEY);
            String nameFromJSON = referenceJSON.getString(NAME_KEY);
            String descFromJSON = referenceJSON.getString(DESC_KEY);

            cmdFromJSON.add(new CommandLineDAO(qtyFromJSON, priceFromJSON, refFromJSON, nameFromJSON, descFromJSON));
        }
       
        BasketDAO loadedBasket = new BasketDAO(idFromJSON,validationFromJSON,cmdFromJSON);
        return loadedBasket;

    }
}