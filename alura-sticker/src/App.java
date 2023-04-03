import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
  public static void main (String[] args) throws Exception {
    
    // Consultar API.java para visualizar as apis disponíveis

    System.out.println("\n Top 5 melhores linguagens de programação! \n");
    Api api = Api.LINGUAGENS;

    ContentExtractor contentExtractor = api.getContentExtractor();
    ClientHttp httpClient = new ClientHttp();
    String bodyString = httpClient.dateSearch(api.getUrl());
  
    // Exibir e manipular dados
    List<Content> contents = contentExtractor.extractContent(bodyString);

    var generator = new StickerGenerator();

    for(int i=0; i < 5; i++){

      Content content = contents.get(i);

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