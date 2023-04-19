package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


public class Main {
    public static void main(String[] args) throws IOException {

        int index = 1;

        //String url = "https://issues.apache.org/jira/rest/api/2/issue/BOOKKEEPER-1103?expand=changelog";
        //String url = "https://issues.apache.org/jira/rest/api/2/issue/BOOKKEEPER-" + "?expand=changelog";

        for(index = 1; index <= 1105; index++){
            String urlSet = "https://issues.apache.org/jira/rest/api/2/issue/BOOKKEEPER-" + index + "?expand=changelog";
            // Esegui la richiesta GET con cURL
            ProcessBuilder processBuilder = new ProcessBuilder("curl", "-X", "GET", urlSet);
            Process process = processBuilder.start();

            // Leggi la risposta della richiesta GET
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String response = stringBuilder.toString();

            // Analizza la risposta JSON
            Gson gson1 = new Gson();
            JsonObject jsonObject = gson1.fromJson(response, JsonObject.class);
            //System.out.println("Dati: " + jsonObject);
            //String data = jsonObject.get("summary").getAsString(); // Modifica "data" con la chiave dell'oggetto JSON che vuoi estrarre

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Object obj = gson.fromJson(jsonObject, Object.class);
            String formattedJson = gson.toJson(obj);

            //System.out.println(formattedJson);
            System.out.println("Summary: " + jsonObject.get("fields").getAsJsonObject().get("summary").getAsString());

            //System.out.println("Dati: " + data);
        }


    }
}