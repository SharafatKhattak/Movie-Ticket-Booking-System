public class Movie {
    private int id;
    private String movieName;
    private String posterPath;
    private String genre;
    private String showingDate;

    public Movie(int id, String movieName, String posterPath, String genre, String showingDate) {
        this.id = id;
        this.movieName = movieName;
        this.posterPath = posterPath;
        this.genre = genre;
        this.showingDate = showingDate;
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

    public String getGenre() {
        return genre;
    }

    public String getShowingDate() {
        return showingDate;
    }
}