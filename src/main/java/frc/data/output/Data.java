package frc.data.output;

import java.util.ArrayList;

import com.google.gson.JsonParser;

import frc.data.api.BlueAllianceAPI;
import frc.data.api.IAPIKey;
import frc.data.serializers.MatchJsonDeserializer;
import frc.data.type.Event;
import frc.data.type.Match;
import frc.data.type.Team;

/**
 * A high-level interface for the BlueAlliance API, allowing data processing and formatting in Java.
*/
public class Data {

    private static BlueAllianceAPI api;

    public Data(IAPIKey key) {
        api = new BlueAllianceAPI(key);
    }

    public ArrayList<Event> getEvents() {
        return null;
    }
    public ArrayList<Match> getMatches(Team team, int year) {
        
        ArrayList<Match> matches = new ArrayList<>();

        JsonParser.parseString(api.getEvents(team)).getAsJsonArray().forEach((match) -> {
            //try {
                //System.out.print(match);
                matches.add(MatchJsonDeserializer.deserialize(match));
                //matches.add(new MatchJsonAdapter().read(new JsonReader(new StringReader(match.getAsJsonObject().toString()))));
            /*} catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/


        });

        return matches;
    }

    public ArrayList<Match> getAllMatchesForTeam(Team team) {
        api.getEvents(team);
        return null;
    }
}
