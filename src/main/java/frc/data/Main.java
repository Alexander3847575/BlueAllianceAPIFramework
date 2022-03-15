package frc.data;

import java.util.Scanner;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import frc.data.api.BlueAllianceAPI;
import frc.data.api.IAPIKey;
import frc.data.output.Data;
import frc.data.output.DataIO;
import frc.data.serializers.JsonSerializerRegistry;
import frc.data.type.Match;
import frc.data.type.Team;

/**
 * An example CLI used for debugging.
 */
public class Main {

    //Activate test mode
    private static final boolean testMode = true;
    private static final IAPIKey apiKey = new APIKey("C:\\Windows\\tracing\\repos\\FIRST match data processing\\src\\main\\java\\frc\\data\\apikey.txt");


    public static void main(String[] args) {

        if(testMode){
            test();
        }
        else {
            JsonSerializerRegistry.registerSerializers();
            Gson gson = new GsonBuilder().create();

            BlueAllianceAPI api = new BlueAllianceAPI(apiKey);
            Scanner scanner = new Scanner(System.in);
            String input = "";
            int number = 0;

            
            while (input != "exit") {

                System.out.print("Input team number:\n=> ");
                input = scanner.nextLine();

                try {
                    number = Integer.parseInt(input);
                    scanner.close();
                    break;
                } catch (Exception e){
                    System.out.println("Please enter a valid integer!");
                }

            }

            Team team = new Team(number);
            ArrayList<Match> matches = new ArrayList<>();

            JsonParser.parseString(api.getMatches(team, 2020)).getAsJsonArray().forEach((match) -> matches.add(gson.fromJson(match, Match.class))); //TODO: ew
            

            DataIO.writeRaw(api.getMatches(team, 2020), ".\\output.matches");

        }


    }

    private static void test() {

        /*ArrayList<Match> matches = new Data(apiKey).getAllMatchesForTeam(new Team(8248));
    
        DataIO.writeMatchData(matches, ".\\output.matches");*/

        System.out.println("\n" + DataIO.readMatchData(".\\output.matches"));

    }

}
 