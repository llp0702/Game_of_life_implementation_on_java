package Logic;
public class Monde{
	/*Cette classe représente le monde sous forme d'une matrice de booleens dans le jeu de la vie*/
	
	
	//Variables d'instance
	private int dimx,dimy,noGener;
	private boolean[][] tabCells;

	//Accesseurs
	public int getH(){return dimy;}
	public int getL(){return dimx;}
	public boolean get(int i, int j){return tabCells[i][j];}
	
	//Constructeurs
	public Monde(int dimx,int dimy,double seuil){
		this.dimy = dimy;
		this.dimx = dimx;
		tabCells = new boolean[dimy][dimx];
		for(int i=0;i<dimy;i++)for(int j=0;j<dimx;j++){
			tabCells[i][j] = (Math.random()<seuil)?true:false;
			}
		}
	
	public Monde(int dimx, int dimy, int x, int y, boolean[][] motif){
		this.dimx = dimx;
		this.dimy = dimy;
		tabCells = new boolean[dimy][dimx];
		for(int i=0;i<dimy;i++)for(int j=0;j<dimx;j++)tabCells[i][j]=false;
		for(int i=0;i<motif.length;i++)for(int j=0;j<motif[i].length;j++)tabCells[i+x][j+y] = motif[i][j];
		}
	
	//Méthodes
	public String toString(){
		String s="";
		System.out.println("Generation n "+noGener);
		for(int i=0;i<dimy;i++){
				for(int j=0;j<dimx;j++){
					s += (tabCells[i][j]) ? " * " : " . " ;
				}
				s += "\n" ;
			}
		return s;
		}
	public int nbVoisins(int nuLign,int nuCol){
		int i=0;
		/*if(tabCells[nuLign][(nuCol+1)%dimx])i++;
		if(tabCells[(nuLign+1)%dimy][(nuCol+1)%dimx])i++;
		if(tabCells[(nuLign+1)%dimy][nuCol])i++;
		if(tabCells[(nuLign+1)%dimy][(nuCol+dimx-1)%dimx])i++;
		if(tabCells[nuLign][(nuCol+dimx-1)%dimx])i++;
		if(tabCells[(nuLign+dimy-1)%dimy][(nuCol+dimx-1)%dimx])i++;
		if(tabCells[(nuLign+dimy-1)%dimy][nuCol])i++;
		if(tabCells[(nuLign+dimy-1)%dimy][(nuCol+1)%dimx])i++;*/
		for(int u=nuLign-1;u<=nuLign+1;u++)for(int j=nuCol-1;j<=nuCol+1;j++)if(!(u==nuLign&&j==nuCol))if(tabCells[(u+dimy)%dimy][(j+dimx)%dimx])i++;
		return i;
		}
	public void genSuiv(){
		boolean[][] newTab = new boolean[dimy][dimx];
		for(int i=0;i<dimy;i++)
			for(int j=0;j<dimx;j++){
				int x=nbVoisins(i,j);
				if((x<2 || x>3))newTab[i][j]=false;
				else if(x==3)newTab[i][j]=true;
				else newTab[i][j]=tabCells[i][j];
			}
		//tabCells = newTab;
		for(int i=0;i<dimy;i++)for(int j=0;j<dimx;j++)tabCells[i][j]=newTab[i][j];
		noGener++;	
		}
	}
