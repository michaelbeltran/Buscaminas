package vista;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import modelo.Juego;
import conf.Configuracion;
public class Tablero extends Canvas {
	private Juego juego;
	public int mouseX=-1, mouseY=-1;
	private BufferedImage mina;
	private BufferedImage celda;
	private BufferedImage bandera;
	private BufferedImage blanco;
	public Tablero(Juego DatosJuego){
		setBackground(Color.lightGray);
		imageCanvas();
		juego = DatosJuego;
	}
	public void paint(Graphics g){
		impTablero(g);
	}
	private void impTablero(Graphics g){
		for(int i = 0; i < Configuracion.filas; i++){
			for(int j = 0; j < Configuracion.columnas; j++){
				if(Configuracion.tablero[i][j][1] == Configuracion.DESPEJAD){
					if(Configuracion.tablero[i][j][0] >= 0){
						g.drawImage(blanco, i*Configuracion.INCREMENTO+i, 
									j*Configuracion.INCREMENTO+j, this);
						g.drawString(Integer.toString(Configuracion.tablero[i][j][0]),
								i*Configuracion.INCREMENTO+i+5, j*Configuracion.INCREMENTO+j+15);
					}else if(Configuracion.tablero[i][j][0] == -1){
						g.drawImage(mina, i*Configuracion.INCREMENTO+i,
									j*Configuracion.INCREMENTO+j, this);
					}else if(Configuracion.tablero[i][j][2] == -2){
						g.drawImage(bandera, i*Configuracion.INCREMENTO+i,
									j*Configuracion.INCREMENTO+j, this);
					}
				}else if(Configuracion.tablero[i][j][2] == -2){
						g.drawImage(bandera, i*Configuracion.INCREMENTO+i,
									j*Configuracion.INCREMENTO+j, this);
				}else{
					g.drawImage(celda, i*Configuracion.INCREMENTO+i,
								j*Configuracion.INCREMENTO+j, this);
				}
			}
		}
		
	}
	public void imageCanvas() {
		try {
			mina = ImageIO.read(new File("vista/Images/mina.png"));
			celda = ImageIO.read(new File("vista/Images/celda.png"));
			bandera = ImageIO.read(new File("vista/Images/bandera.png"));
			blanco = ImageIO.read(new File("vista/Images/blanco.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
