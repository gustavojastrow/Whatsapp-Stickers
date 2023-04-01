public enum Api {
    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ImDbContentExtractor()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=bx519SB8uKU36mYoAPSVeN6WU7gptf5neqY3PtdR&start_date=2023-01-10&end_date=2023-01-20", new NasaContentExtractor());
    
    private String url;
    private ContentExtractor contentExtractor;

    Api(String url, ContentExtractor contentExtractor){
        this.url = url;
        this.contentExtractor = contentExtractor;
    }


    public ContentExtractor getContentExtractor() {
        return contentExtractor;
    }

    public String getUrl() {
        return url;
    }
}