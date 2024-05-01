import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Country> countries;

    public Repository() {
        this.countries = new ArrayList<>();
    }

    public void fillData() throws IOException {
        // I added "WeatherApp/" at the beginning of the path because it will not work without it
        BufferedReader reader = new BufferedReader(new FileReader("WeatherApp/Data Base/list of countries.txt"));
        try {
            String line = reader.readLine();

            while(line != null) {
                String[] inputTokens = line.split(",");

                String country = inputTokens[0];
                String capital = inputTokens[1];
                Double latitude = Double.parseDouble(inputTokens[2]);
                Double longitude = Double.parseDouble(inputTokens[3]);

                this.countries.add(new Country(country, capital, latitude, longitude));

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    public List<Country> getCountries() {
        return countries;
    }
}
