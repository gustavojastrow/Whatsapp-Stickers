import java.io.InputStream;
import java.net.URL;
import java.util.List;



public class App {
  public static void main (String[] args) throws Exception {
    
    // API NASA
    // System.out.println("5 Imagens NASA\n");
    // Api api = Api.NASA;
    // String url = api.getUrl();

    System.out.println("Filmes populares\n");
    Api api = Api.IMDB_POPULARES_MOVIES;
    ContentExtractor contentExtractor = api.getContentExtractor();
    ClientHttp httpClient = new ClientHttp();
    String bodyString = httpClient.dateSearch(api.getUrl());
  
    // Exibir e manipular dados
    List<Content> conteudos = contentExtractor.extractContent(bodyString);

    var generator = new StickerGenerator();



    for(int i=0; i < 3; i++){

      Content content = conteudos.get(i);

      InputStream inputStream = new URL(content.imageUrl()).openStream();
      String nomeArquivo = content.title() + ".png";

      String texto_figurinha = "TOPZERA";
      generator.create(inputStream, nomeArquivo, texto_figurinha);
      
      // negrito no terminal
      System.out.println("\u001b[1mTitulo: \u001b[m" +content.title() );
      System.out.println("\u001b[1mLink Imagem: \u001b[m" + content.imageUrl());
      System.out.println();
      }
  }
}