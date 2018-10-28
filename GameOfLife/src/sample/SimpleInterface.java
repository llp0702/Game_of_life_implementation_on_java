package sample;

import java.awt.*;
import java.awt.image.*;

import javax.swing.JFrame;
import javax.swing.JPanel;




public class SimpleInterface extends JFrame {

    public final static int HEIGHT=400;
    public final static int WIDTH=400;

    private MyPanel surf;
    private BufferedImage img;

    public SimpleInterface(String name){
        this(name,WIDTH,HEIGHT);
    }	

    public SimpleInterface(String name, int width, int height){
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        surf=new MyPanel();
        surf.setPreferredSize(new Dimension(width, height));
        this.setLayout(new BorderLayout());
        this.add(surf, BorderLayout.CENTER);
        pack();
        setFocusable(true);
        setVisible(true);
    }

    public void refresh(){
        repaint();
    }

    public void createArea(int sizeX, int sizeY){
        img= new BufferedImage(sizeX, sizeY,BufferedImage.TYPE_INT_ARGB);
    }

    public void setRGB(int x, int y, Color c){
        img.setRGB(x, y, c.getRGB());				
    }

    class MyPanel extends JPanel{
        MyPanel(){
            super();

        }
        public void paint(Graphics g){			 
            if (img==null)
                return;
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            int h,w;
            double ri = (double) img.getHeight() / img.getWidth();
            double rp = (double) getHeight() / getWidth();
            if(ri>rp){
                h = getHeight(); 
                w = (int)(h/ri);
            }
            else{
                w = getWidth();
                h = (int)(w*ri);
            }
            g2.drawImage(img, 0, 0, w, h, null);
        }		
    }

}
