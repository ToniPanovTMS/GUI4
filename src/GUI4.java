import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class GUI4 extends Canvas {
    static int width = 1600, height=1000,y = 324, x = 4, vX=9,vY=9;//размеры окна начальная позиция и векторы

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g; //преобразуем Graphics в Graphics2D (для сглаживания)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //сглаживание
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src/m_str.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int imageWidth = image.getWidth(), imageHeight=image.getHeight();
        while (true) {
            if(x>=width-imageWidth-15||x<=0){
                vX*=-1;
            }else if(y>=height-imageHeight-40||y<=0){
                vY*=-1;
            }
            g2.clearRect(x, y, imageWidth, imageHeight);
            g2.drawImage(image, x, y, this);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            x+=vX;
            y+=vY;
        }

    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("DVD");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(dim.width/2-width/2,dim.height/2-height/2, width,height);
        GUI4 m=new GUI4();
        frame.add(m);
        frame.setVisible(true);
    }
}