import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Movie {
    public static final String API_KEY = "bf44104e";   // TODO --> add your api key about Movie here
    String title;
    int ImdbVotes;
    ArrayList<String> actorsList;
    String rating;
    String Genre;
    String Director;
    String Language;


    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes){
        //TODO --> (Write a proper constructor using the get_from_api functions)
    }

    //another constructor
    public Movie(String  title){
        this.title = title;
        this.actorsList =new ArrayList<>() ;
        this.rating ="";
        this.ImdbVotes = 0;
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) throws IOException {
        URL url = new URL("https://www.omdbapi.com/?t="+title+"&apikey="+API_KEY);
        URLConnection Url = url.openConnection();
        Url.setRequestProperty("Authorization", "Key" + API_KEY);
        BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine())!=null) {
            stringBuilder.append(line);
        }
        reader.close();
        //handle an error if the chosen movie is not found
        String info = stringBuilder.toString();
        JSONObject movieInfo = new JSONObject(info);
        if (movieInfo.getString("Response").equals("False") ){

            throw new IOException();
        }
        return stringBuilder.toString();
    }
    public int getImdbVotesViaApi(String moviesInfoJson){
        //TODO --> (This function must change and return the "ImdbVotes" as an Integer)
        // NOTICE :: you are not permitted to convert this function to return a String instead of an int !!!

        JSONObject movieJSON = new JSONObject(moviesInfoJson);
        String votes = movieJSON.getString("imdbVotes");
        if (!votes.equals("N/A")) {
            String correctStr = votes.substring(0, votes.indexOf(',')) + votes.substring(votes.indexOf(',') + 1); //deleting non digit character
            this.ImdbVotes = Integer.parseInt(correctStr);
        }
        return ImdbVotes;
    }

    public String getRatingViaApi(String moviesInfoJson){
        //TODO --> (This function must return the rating in the "Ratings" part
        // where the source is "Internet Movie Database")  -->

        JSONObject movieJSON = new JSONObject(moviesInfoJson);
        JSONArray ratingArray = new JSONArray(movieJSON.getJSONArray("Ratings"));
        JSONObject ratingJSON = new JSONObject();
        if (!ratingJSON.isEmpty()) {
            ratingJSON = ratingArray.getJSONObject(0);
            this.rating = ratingJSON.getString("Value");
        }
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson){
        //TODO --> (This function must return the "Actors" in actorsList)
        JSONObject movieJSON = new JSONObject(movieInfoJson);
        String actor = movieJSON.getString("Actors");
        String[] actors = actor.split(",");

        actorsList = new ArrayList<>();
        actorsList.addAll(Arrays.asList(actors));

    }

    public void getDirectorViaApi(String movieInfoJson){

        JSONObject movieJSON = new JSONObject(movieInfoJson);
        this.Director = movieJSON.getString("Director");

    }

    public void getLanguageViaApi(String movieInfoJson){

        JSONObject movieJSON = new JSONObject(movieInfoJson);
        this.Language = movieJSON.getString("Language");

    }

    public void getGenreViaApi(String movieInfoJson){

        JSONObject movieJSON = new JSONObject(movieInfoJson);
        this.Genre = movieJSON.getString("Genre");

    }

    @Override
    public String toString() {
        return "Movie{" +'\n'+
                " title = '" + title + '\'' +'\n'+
                " ImdbVotes = " + ImdbVotes +'\n'+
                " actorsList = " + actorsList +'\n'+
                " rating = '" + rating + '\'' +'\n'+
                " Genre = '" + Genre + '\'' +'\n'+
                " Director = '" + Director + '\'' +'\n'+
                " Language = '" + Language + '\'' +'\n'+
                '}';
    }
}