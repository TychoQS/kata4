package software.ulpgc.kata4.architecture.model;

public record Title(String id, TitleType titleType, String title, int year, int duration) {
    public enum TitleType {
        VIDEOGAME,
        TVPILOT,
        MOVIE,
        TVSERIES,
        TVMINISERIES,
        SHORT,
        TVSPECIAL,
        TVSHORT,
        VIDEO,
        TVMOVIE,
        TVEPISODE
    }
}
