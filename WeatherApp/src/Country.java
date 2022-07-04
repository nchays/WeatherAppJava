import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Country {
    private String country;
    private String capital;
    private Double latitude;
    private Double longitude;

    public Country(String country, String capital, Double latitude, Double longitude) {
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
