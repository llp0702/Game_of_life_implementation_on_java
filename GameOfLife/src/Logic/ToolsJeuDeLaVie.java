package Logic;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class ToolsJeuDeLaVie{
	public static boolean[][] makeBloc(){
			boolean[][] x= {{true,false},{true,true}};
			return x;
		}
	
	public static boolean[][] makePlanner(){
			boolean[][] x= {{false,true,false},{false,false,true},{true,true,true}};
			return x;
		}
	public static boolean[][] readFromImg(String name,int x,int y)throws IOException{
				BufferedImage im = ImageIO.read(new File(name));
				boolean[][] tabCells = new boolean[y][x];
				int pasX = im.getWidth()/x,pasY = im.getHeight()/y;
				int pasInitX = pasX/2,pasInitY = pasY/2;
				for(int i=0;i<x;i++){
					for(int j=0;j<y;j++){
							tabCells[j][i] = (!(im.getRGB(pasInitX+i*pasX,pasInitY+j*pasY)==Color.white.getRGB()));
						}
					}
				return tabCells;
		}
	}

