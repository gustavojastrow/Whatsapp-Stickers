import java.util.List;
import java.util.Map;

public class LinguagensContentExtractor implements ContentExtractor{
    public List<Content> extractContent(String json){
        
        // Extrair dados (Titulo, url)
        var parser = new JsonParser();
        List<Map<String, String>> atributes_list = parser.parse(json);

        // popular lista de conteudos   
        return atributes_list.stream()
            .map(atributes -> new Content(atributes.get("title"), atributes.get("image")))
            .toList();
}
}
