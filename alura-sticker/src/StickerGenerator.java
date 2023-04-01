import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class StickerGenerator {
    
    public void create(InputStream inputStream, String nome_arquivo, String texto) throws Exception{

        // leitura img
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // criar nova imagem com transparência 
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int nova_altura = altura + 200;
        BufferedImage nova_imagem = new BufferedImage(largura, nova_altura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem( em memória)
        Graphics2D graphics = (Graphics2D)nova_imagem.getGraphics();
        graphics.drawImage(imagemOriginal, null, 0, 0);

        //fonte 
        graphics.setColor(Color.YELLOW);
        Font font = new Font("Impact", Font.BOLD, 110);
        graphics.setFont(font);

        // escrever frase na imagem
        String text = texto;
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(text, graphics);
        int largura_texto = (int) retangulo.getWidth();
        int xTexto = (largura - largura_texto) / 2;
        int yTexto = nova_altura - 80;
        graphics.drawString(text, xTexto, yTexto);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(text, font, fontRenderContext);
        
        // contorno da fonte
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(xTexto, yTexto);
        graphics.setTransform(transform);

        var outlineStroke = new BasicStroke(largura * 0.002f);
        graphics.setStroke(outlineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //Desafio 1 - Aula 2: Criar diretório de saída se este ainda não existe
        if (!new File("figurinhas").exists()) {
        new File("figurinhas").mkdir();
        }
        //Escrever a nova imagem em um arquivo
        ImageIO.write(nova_imagem, "png", new File("figurinhas/" + nome_arquivo));
        }
    }

