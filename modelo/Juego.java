package modelo;
import java.util.Random;
public class Juego{
	public int filas, columnas;
	public int tablero[][][];
	public int minas[][], cantMinas;
	public static final int INCREMENTO = 20;
	public static final int INTER = 1;
	public static final int MEDIO = 2;
	public static final int EXPER = 3;
	public static final int HAY_MIN = -1;
	public static final int CUBIERTO = 1;
	public static final int DESPEJAD = 0;
	public Juego(){
		asignarNivel(EXPER);
		genMina();
		genMapa();
		cantidadMinas();
	}
	private void asignarNivel(int nivel){
		if(nivel == INTER){
			filas = 8;
			columnas = 8;
			cantMinas = 10;
		}else if(nivel == MEDIO){
			filas = 16;
			columnas = 16;
			cantMinas = 40;
		}else if(nivel == EXPER){
			filas = 16;
			columnas = 30;
			cantMinas = 99;
		}
		tablero = new int[filas][columnas][2];
		minas = new int[cantMinas][2];
	}
	public boolean minaExiste(int x, int y){
		for(int i=0;i<minas.length;i++){
			if(minas[i][0] == x && minas[i][1] == y){
				return true;
			}
		}
		return false;
	}
	private void genMina(){
		Random ranGenerator = new Random();
		int x, y;
		for(int i=0; i<cantMinas;i++){
			do{
				x=ranGenerator.nextInt(filas-1);
				y=ranGenerator.nextInt(columnas-1);
			}while(minaExiste(x, y));
			minas[i][0] = x;
			minas[i][1] = y;
			
		}
	}
	private void genMapa(){
		int count = 0;
		for(int i=0; i<filas; i++){
			for(int j=0; j<columnas;j++){
				if(minaExiste(i,j)){
					tablero[i][j][0] = HAY_MIN;
					tablero[i][j][1] = CUBIERTO;
				}else{
					tablero[i][j][0] = 0;
					tablero[i][j][1] = CUBIERTO;
				}
			}
		}
	}
	private void cantidadMinas(){
		for(int i = 0; i<cantMinas; i++){
			int x = minas[i][0];
			int y = minas[i][1];
			if(existeCelda(x-1, y-1))//esquina superior izquierda
				tablero[x-1][y-1][0]++;
			if(existeCelda(x+1, y-1))//esquina inferior izquierda
				tablero[x+1][y-1][0]++;
			if(existeCelda(x, y-1))//izquierda
				tablero[x][y-1][0]++;
			if(existeCelda(x-1, y+1))//esquina superior derecha
				tablero[x-1][y+1][0]++;
			if(existeCelda(x+1, y+1))//esquina inferior derecha
				tablero[x+1][y+1][0]++;
			if(existeCelda(x, y+1))//derecha
				tablero[x][y+1][0]++;
			if(existeCelda(x-1, y))//superior
				tablero[x-1][y][0]++;
			if(existeCelda(x+1, y))//inferior
				tablero[x+1][y][0]++;
		}
	}
	private boolean existeCelda(int x, int y){
		if((x >= 0 && x < filas && y >=0 && y < columnas) && !minaExiste(x,y))
			return true;
		else
			return false;
	}
	public void despejar(int x, int y){
		if(existeCelda(x-1, y-1)){//esquina superior izquierda
			tablero[x-1][y-1][1]=DESPEJAD;
			if(tablero[x-1][y-1][0] == 0)
				despejar(x-1, y-1);
		}
		if(existeCelda(x+1, y-1)){//esquina inferior izquierda
			tablero[x+1][y-1][1]=DESPEJAD;
			if(tablero[x+1][y-1][0] == 0)
				despejar(x+1, y-1);
		}
		if(existeCelda(x, y-1)){//izquierda
			tablero[x][y-1][1]=DESPEJAD;
			if(tablero[x][y-1][0] == 0)
				despejar(x, y-1);
		}
		if(existeCelda(x-1, y+1)){//esquina superior derecha
			tablero[x-1][y+1][1]=DESPEJAD;
			if(tablero[x-1][y+1][0] == 0)
				despejar(x-1, y+1);
		}
		if(existeCelda(x+1, y+1)){//esquina inferior derecha
			tablero[x+1][y+1][1]=DESPEJAD;
			if(tablero[x+1][y+1][0] == 0)
				despejar(x+1, y+1);
		}
		if(existeCelda(x, y+1)){//derecha
			tablero[x][y+1][1]=DESPEJAD;
			if(tablero[x][y+1][0] == 0)
				despejar(x, y+1);
		}
		if(existeCelda(x-1, y)){//superior
			tablero[x-1][y][1]=DESPEJAD;
			if(tablero[x-1][y][0] == 0)
				despejar(x-1, y);
		}
		if(existeCelda(x+1, y)){//inferior
			tablero[x+1][y][1]=DESPEJAD;
			if(tablero[x+1][y][0] == 0)
					despejar(x+1, y);
		}
	}
}
