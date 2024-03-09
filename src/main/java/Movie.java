import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
public class Movie {
    public static final String API_KEY = "39677b73";

    String MovieName;
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes)
    {
        this.actorsList = actorsList;
        this.rating = rating;
        this.ImdbVotes = ImdbVotes;
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        try {
            URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
            URLConnection Url = url.openConnection();
            Url.setRequestProperty("Authorization", "Key" + API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        }
        catch (IOException e)
        {
            System.err.println("Error" + e.getMessage());
            return null;
        }

    }
    public int getImdbVotesViaApi(String moviesInfoJson)
    {
        int ImdbVotes = 0;
        JSONObject JO = new JSONObject(moviesInfoJson);
        ImdbVotes = Integer.parseInt((JO.getString("imdbVotes").replace("," , "")));
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson)
    {
        String rating = "";
        JSONObject JO = new JSONObject(moviesInfoJson);
        rating = JO.getString("imdbRating");
        return rating + "/10";
    }

    public String getActorListViaApi(String movieInfoJson)
    {
        String actors = "";
        JSONObject JO = new JSONObject(movieInfoJson);
        actors = JO.getString("Actors");
        return actors;
    }
}