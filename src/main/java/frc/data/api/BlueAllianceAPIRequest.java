package frc.data.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import frc.data.type.RequestFailedException;

/**
 * An object representing a request to the BlueAlliance API.
 * 
 * <p> A {@code BlueAllianceAPIRequest} instance requires an {@link java.net.http.HttpClient HttpClient} and an {@link IAPIKey APIKey}.
 * Arguments are fixed at initialization, which are provided in the docs.
 * <p>({@link https://www.thebluealliance.com/apidocs/v3})
 * 
 * <p> A request may be sent multiple times
 */
public class BlueAllianceAPIRequest {

    private final HttpClient client;
    private final HttpRequest request;
    public String result;
    
    public BlueAllianceAPIRequest(HttpClient httpclient, IAPIKey key, String args) {

        this.client = httpclient;
        System.out.println(args);
        this.request = HttpRequest.newBuilder()
            .uri(URI.create("https://www.thebluealliance.com/api/v3/" + args))
            .timeout(Duration.ofMinutes(1))
            .header("Content-Type", "application/json")
            .header("X-TBA-Auth-Key", key.getKey())
            .build();

    }

    public String get() throws RequestFailedException {

        this.client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept((o) -> this.result=o)
            .join();

        return this.result;
        //TODO: implement throws for response codes
    }

}
