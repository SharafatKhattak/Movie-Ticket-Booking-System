public class Movie {
    private int id;
    private String movieName;
    private String posterPath;

    public Movie(int id, String movieName, String posterPath) {
        this.id = id;
        this.movieName = movieName;
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }
    public String getPosterPath() {
        return posterPath;
    }


}