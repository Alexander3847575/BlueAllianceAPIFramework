package frc.data.api;

import java.net.http.HttpClient;

import frc.data.type.*;

/**
 * Serves as a intermediatery to get raw api requests.
 */
public class BlueAllianceAPI {

    private static HttpClient client;
    private IAPIKey key;

    public BlueAllianceAPI(IAPIKey key) {

        client = HttpClient.newHttpClient();
        this.key = key;

    }

    /**
     * Returns a list of {@link type.Event Events} for a given Team.
     * @param team - A frc {@link type.Team Team}.
     */
    public String getEvents(Team team) {

        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "team/" + team.getTeamId() + "/events");
        String result = null;
        try {
            result =  request.get();
        } catch (RequestFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public String getMatches(Event event) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "event/" + event.getEventId() + "/matches");
        String result = null;
        try {
            result =  request.get();
        } catch (RequestFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    public String getMatches(Team team, int year) {
        BlueAllianceAPIRequest request = new BlueAllianceAPIRequest(client, key, "team/" + team.getTeamId() + "/matches/" + year + "/simple");

        String result = null;
        try {
            result =  request.get();
        } catch (RequestFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

}
