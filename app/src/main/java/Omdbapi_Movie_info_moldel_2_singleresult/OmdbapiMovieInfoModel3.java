
package Omdbapi_Movie_info_moldel_2_singleresult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OmdbapiMovieInfoModel3 {

    String Title, Year, Genre, Director, Country, Poster, ImdbID;

    public OmdbapiMovieInfoModel3(String title, String year, String genre, String director, String country, String poster, String imdbID){
        this.Title = title;
        this.Year = year;
        this.Genre = genre;
        this.Director = director;
        this.Country = country;
        this.Poster = poster;
        this.ImdbID = imdbID;

    }


    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return ImdbID;
    }

    public String getGenre() {
        return Genre;
    }

    public String getPoster() {
        return Poster;
    }
}
