package modelo;
import java.util.Random;
import conf.Configuracion;
public class Juego{
	public Juego(int nivel){
		asignarNivel(nivel);
		genMina();
		genMapa();
		cantidadMinas();
	}
	private void asignarNivel(int nivel){
		if(nivel == Configuracion.INTER){
			Configuracion.filas = 8;
			Configuracion.columnas = 8;
			Configuracion.cantMinas = 10;
		}else if(nivel == Configuracion.MEDIO){
			Configuracion.filas = 16;
			Configuracion.columnas = 16;
			Configuracion.cantMinas = 40;
		}else if(nivel == Configuracion.EXPER){
			Configuracion.filas = 16;
			Configuracion.columnas = 30;
			Configuracion.cantMinas = 99;
		}
		Configuracion.tablero = new int[Configuracion.filas][Configuracion.columnas][2];
		Configuracion.minas = new int[Configuracion.cantMinas][2];
	}
	public boolean minaExiste(int x, int y){
		for(int i=0;i<Configuracion.minas.length;i++){
			if(Configuracion.minas[i][0] == x && Configuracion.minas[i][1] == y){
				return true;
			}
		}
		return false;
	}
	private void genMina(){
		Random ranGenerator = new Random();
		int x, y;
		for(int i=0; i<Configuracion.cantMinas;i++){
			do{
				x=ranGenerator.nextInt(Configuracion.filas-1);
				y=ranGenerator.nextInt(Configuracion.columnas-1);
			}while(minaExiste(x, y));
			Configuracion.minas[i][0] = x;
			Configuracion.minas[i][1] = y;
			
		}
	}
	private void genMapa(){
		int count = 0;
		for(int i=0; i<Configuracion.filas; i++){
			for(int j=0; j<Configuracion.columnas;j++){
				if(minaExiste(i,j)){
					Configuracion.tablero[i][j][0] = Configuracion.HAY_MIN;
					Configuracion.tablero[i][j][1] = Configuracion.CUBIERTO;
				}else{
					Configuracion.tablero[i][j][0] = 0;
					Configuracion.tablero[i][j][1] = Configuracion.CUBIERTO;
				}
			}
		}
	}
	private void cantidadMinas(){
		for(int i = 0; i<Configuracion.cantMinas; i++){
			int x = Configuracion.minas[i][0];
			int y = Configuracion.minas[i][1];
			if(existeCelda(x-1, y-1))//esquina superior izquierda
				Configuracion.tablero[x-1][y-1][0]++;
			if(existeCelda(x+1, y-1))//esquina inferior izquierda
				Configuracion.tablero[x+1][y-1][0]++;
			if(existeCelda(x, y-1))//izquierda
				Configuracion.tablero[x][y-1][0]++;
			if(existeCelda(x-1, y+1))//esquina superior derecha
				Configuracion.tablero[x-1][y+1][0]++;
			if(existeCelda(x+1, y+1))//esquina inferior derecha
				Configuracion.tablero[x+1][y+1][0]++;
			if(existeCelda(x, y+1))//derecha
				Configuracion.tablero[x][y+1][0]++;
			if(existeCelda(x-1, y))//superior
				Configuracion.tablero[x-1][y][0]++;
			if(existeCelda(x+1, y))//inferior
				Configuracion.tablero[x+1][y][0]++;
		}
	}
	private boolean existeCelda(int x, int y){
		if(x >= 0 && x < Configuracion.filas && y >=0 &&
			y < Configuracion.columnas && !minaExiste(x,y) &&
			Configuracion.tablero[x][y][1] == Configuracion.CUBIERTO)
			return true;
		else
			return false;
	}
	public void mostrar(int x, int y){
		int temX, temY;
		for(int i=0; i<Configuracion.filas; i++){
			for(int j=0; j<Configuracion.columnas;j++){
				temX = i*Configuracion.INCREMENTO+i;
				temY = j*Configuracion.INCREMENTO+j;
				if(x >= temX && x <= temX+Configuracion.INCREMENTO &&
					y >= temY && y <= temY+Configuracion.INCREMENTO){
					despejar(i, j);
					break;
				}
			}
		}
	}
	public void despejar(int x, int y){
		Configuracion.tablero[x][y][1]=Configuracion.DESPEJAD;
		if(Configuracion.tablero[x][y][0] == 0){
			if(existeCelda(x-1, y-1)){//esquina superior izquierda
					despejar(x-1, y-1);
			}
			if(existeCelda(x+1, y-1)){//esquina inferior izquierda
					despejar(x+1, y-1);
			}
			if(existeCelda(x, y-1)){//izquierda
					despejar(x, y-1);
			}
			if(existeCelda(x-1, y+1)){//esquina superior derecha
					despejar(x-1, y+1);
			}
			if(existeCelda(x+1, y+1)){//esquina inferior derecha
					despejar(x+1, y+1);
			}
			if(existeCelda(x, y+1)){//derecha
					despejar(x, y+1);
			}
			if(existeCelda(x-1, y)){//superior
					despejar(x-1, y);
			}
			if(existeCelda(x+1, y)){//inferior
					despejar(x+1, y);
			}
		}
		
	}
}
