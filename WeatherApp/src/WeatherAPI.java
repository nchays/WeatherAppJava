
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WeatherAPI {
    private StringBuilder result;


    public WeatherAPI(String countryName) throws IOException {
        //https://www.metaweather.com/api/location/search/>query=<CITY>
        //https://open-meteo.com/en/docs#latitude=52.3738&longitude=4.8910&hourly=temperature_2m
        //https://api.open-meteo.com/v1/forecast?latitude=52.3738&longitude=4.8910&hourly=temperature_2m

        Repository repository = new Repository();
        repository.fillData();
        this.result = new StringBuilder();


        List<Country> countries = repository.getCountries();
        String capital = "";
        Double latitude = 0.0;
        Double longitude = 0.0;
        try {
            capital = countries.stream().filter(c -> c.getCountry().equals(countryName)).findFirst().get().getCapital();
            latitude = countries.stream().filter(c -> c.getCountry().equals(countryName)).findFirst().get().getLatitude();
            longitude = countries.stream().filter(c -> c.getCountry().equals(countryName)).findFirst().get().getLongitude();

        } catch(NoSuchElementException e) {
            this.result.append("Invalid country name!");
            return;
        }


        try {
            URL url = new URL(String.format("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&hourly=temperature_2m&current_weather=true&windspeed_unit", latitude, longitude));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode" + responseCode);
            }

            StringBuilder info = new StringBuilder();

            Scanner scan = new Scanner(url.openStream());

            while (scan.hasNext()) {
                info.append(scan.nextLine());
                scan.close();

                JSONParser parser = new JSONParser();
                Object data = parser.parse(String.valueOf(info));

                JSONObject countryData = (JSONObject) data;
                this.result.append(String.format("Current weather for: %s, %s\n", capital, countryName));
                this.result.append("---------------------\n");

                JSONObject current_weather = (JSONObject) countryData.get("current_weather");

               // System.out.println(String.format("Temperature: %.1f\nDate and time: %s\nWind speed: %.2f km/h", current_weather.get("temperature"), current_weather.get("time"),current_weather.get("windspeed")));
                this.result.append(String.format("Temperature: %.1f C°\nDate and time: %s\nWind speed: %.2f km/h", current_weather.get("temperature"), current_weather.get("time"),current_weather.get("windspeed")));

            }
        } catch (Exception e) {

        }

    }

    public StringBuilder getResult() {
        return result;
    }
}
