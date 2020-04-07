package aulabruno;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AulaBrunoCasa02_ex02 extends JFrame {
    
    public static void main(String[] args) {
        int x, y, w, h;
        x = 10;
        y = 10;
        w = 100;
        h = 100;
        
        String img = "tabuleiroNC_1.png";
        
        if(x < 0)
            x = 0;
        if(y < 0)
            y = 0;
        if(x > w)
            x = 0;
        if(y > h)
            y = 0;
        
        AulaBrunoCasa02_ex02 regiao = new AulaBrunoCasa02_ex02(img, x, y, w, h);
        
        regiao.setSize(200, 200);
        regiao.setVisible(true);
    }

    private AulaBrunoCasa02_ex02(String img, int x, int y, int w, int h) {
        BufferedImage orig = null;
        
        File arq = new File(img);
        try {
            orig = ImageIO.read(arq);
        } catch (IOException ex) {
            System.out.println("Imagem não existe!");
            System.exit(0);
        }
        
        setTitle("Média da ROI");
        
        if(w > orig.getWidth())
            w = orig.getWidth() - x;
        
        if(h > orig.getHeight())
            h = orig.getHeight()- y;
        
        if((w > orig.getWidth()) && (h > orig.getHeight())){
            w = orig.getWidth();
            h = orig.getHeight();
            x = 0;
            y = 0;
        }
        
        Rectangle regiao = new Rectangle(x, y, w, h);
        double mediaROI = media(orig, regiao);
    }

    private double media(BufferedImage orig, Rectangle regiao) {
        BufferedImage imgRegiao = orig.getSubimage(regiao.x, regiao.y, regiao.width, regiao.height);
        
        JLabel imgL = new JLabel(new ImageIcon(orig));
        getContentPane().add(new JScrollPane(imgL));
        setSize(imgRegiao.getWidth(), imgRegiao.getHeight());
        
        return media(imgRegiao);
    }

    private double media(BufferedImage imgRegiao) {
        double soma = 0.0;
        
        Raster raster = imgRegiao.getRaster();
        for(int j=0; j<imgRegiao.getHeight(); j++)
            for(int i=0; i<imgRegiao.getWidth(); i++)
                soma += raster.getSample(i, j, 0);
        
        return soma / (imgRegiao.getWidth() * imgRegiao.getHeight());
    }
}
