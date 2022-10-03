package com.webapp.hotelreservation.restclient;
import com.webapp.hotelreservation.exceptions.InvalidDataException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestClient {

    Logger logger = Logger.getLogger(RestClient.class.getName());

    private HttpClient client;

    public RestClient(){
        this.client = HttpClient.newHttpClient();
    }

    public String GetHttpResponse(String url) throws IOException, InterruptedException, InvalidDataException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
                .GET()
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()>=400 && response.statusCode()<500){
            logger.log(Level.WARNING, "Error occurred with status code: "+String.valueOf(response.statusCode()));
            throw new InvalidDataException("Invalid data provided");
        }
        logger.log(Level.INFO, "Request successfully processed. Status code: "+String.valueOf(response.statusCode()));
        return response.body();
    }

    public boolean PostHttpRequest(String url, String body){
        HttpResponse response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header("Content-Type", "application/json")
                    .build();

            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            if (response.statusCode()==200){
                logger.log(Level.INFO, "Request successfully posted. Status code: "+String.valueOf(response.statusCode()));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
