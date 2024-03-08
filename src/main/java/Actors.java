import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
public class Actors {
    public static final String API_KEY = "Mb3cUPpaCMTSulXkM+zk2Q==ycR8tE2Wqeb3xaoP";
    // TODO --> add your api key about Actors here

    String name;
    double netWorth;
    Boolean isAlive;
    String birthday;
    double height;
    String dateOfDeath = "";

    public Actors(String netWorth, boolean isAlive){
        //TODO --> (Write a proper constructor using the get_from_api functions)
    }

    //another constructor
    public Actors(String name){
        this.name = name;
        this.netWorth = 0;
        this.isAlive = true;
    }


    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name="+
                    name.replace(" ", "+")+"&apikey="+API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public double getNetWorthViaApi(String actorsInfoJson){
        //TODO --> (This function must return the "NetWorth")
        String correctJson = actorsInfoJson.substring(1,actorsInfoJson.length()-1).trim();
        JSONObject actorJson = new JSONObject(correctJson);
        this.netWorth = actorJson.getDouble("net_worth");
        return this.netWorth;
    }

    public boolean isAlive(String actorsInfoJson){
        //TODO --> (If your chosen actor is alive it must return true otherwise it must return false)

        String correctJson = actorsInfoJson.substring(1,actorsInfoJson.length()-1).trim();
        JSONObject actorJson = new JSONObject(correctJson);
        this.isAlive = actorJson.getBoolean("is_alive");
        return this.isAlive;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson){
        //TODO --> (If your chosen actor is deceased it must return the date of death)  -->

        String correctJson = actorsInfoJson.substring(1,actorsInfoJson.length()-1).trim();
        JSONObject actorJson = new JSONObject(correctJson);
        if(!actorJson.getBoolean("is_alive")){
            this.dateOfDeath = actorJson.getString("death");
        }

        return dateOfDeath;
    }

    public void getBirthdayViaApi(String actorsInfoJson){

        String correctJson = actorsInfoJson.substring(1,actorsInfoJson.length()-1).trim();
        JSONObject actorJson = new JSONObject(correctJson);
        this.birthday = actorJson.getString("birthday");

    }

    public void getHeightViaApi(String actorsInfoJson){

        String correctJson = actorsInfoJson.substring(1,actorsInfoJson.length()-1).trim();
        JSONObject actorJson = new JSONObject(correctJson);
        this.height = actorJson.getDouble("height");

    }

    @Override
    public String toString() {
        return "Actors{" +'\n'+
                " name='" + name + '\'' +'\n'+
                " netWorth=" + netWorth +'\n'+
                " isAlive=" + isAlive +'\n'+
                " birthday='" + birthday + '\'' +'\n'+
                " height=" + height +'\n'+
                " dateOfDeath='" + dateOfDeath + '\'' +'\n'+
                '}';
    }
}