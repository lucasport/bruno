package aulabruno;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class AulaBrunoCasa02 extends JFrame {
    
    public static void main(String[] args) {
        
        String img = "nome.jpg";
        
        int fator = 1, tipoOp = 1;
        
        if(fator <= 0)
            fator = 1;
        
        if(tipoOp <= 0 || tipoOp > 2)
            tipoOp = 1;
        
        JFrame zoomImg = new AulaBrunoCasa02(img, fator, tipoOp);
        
        zoomImg.setVisible(true);
    }
    
    private AulaBrunoCasa02(String img, int fator, int tipoOp){
        
        BufferedImage orig = null, dest = null;
        
        File arq = new File(img);
        try {
            orig = ImageIO.read(arq);
        } catch (IOException ex) {
            System.out.println("Arquivo não existe");
            System.exit(0);
        }
        
        setTitle("Ampliação e Redução");
        
        dest = zoomImagem(orig, fator, tipoOp);
        
        JLabel imgL = new JLabel(new ImageIcon(dest));
        getContentPane().add(new JScrollPane(imgL));
        setSize(orig.getWidth(), orig.getHeight());
    }

    private BufferedImage zoomImagem(BufferedImage orig, int fator, int tipoOp) {
        
        BufferedImage result = null;
        int w, h;
        
        switch(tipoOp){
            case 1:
                w = fator * orig.getWidth();
                h = fator * orig.getHeight();
                result = new BufferedImage(w, h, orig.getType());
                for(int j=0; j<h; j++)
                    for(int i=0; i<w; i++)
                        result.setRGB(i, j, orig.getRGB(i/fator, j/fator));
                break;
            case 2:
                w = (int)orig.getWidth() / fator;
                h = (int)orig.getHeight() / fator;
                result = new BufferedImage(w, h, orig.getType());
                for(int j=0; j<h; j++)
                    for(int i=0; i<w; i++)
                        result.setRGB(i, j, orig.getRGB(i * fator, j * fator));
        }
        return result;
    }
}
