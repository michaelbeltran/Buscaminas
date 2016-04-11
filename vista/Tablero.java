package vista;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import modelo.Juego;
public class Tablero extends Canvas {
	private Juego juego;
	public int mouseX=-1, mouseY=-1;
	private BufferedImage mina;
	private BufferedImage celda;
	public Tablero(Juego DatosJuego){
		setBackground(Color.lightGray);
		imageCanvas();
		juego = DatosJuego;
	}
	public void paint(Graphics g){
		impTablero(g);
	}
	public void update(Graphics g){
		impTablero(g);
	}
	private void impTablero(Graphics g){
		for(int i = 0; i < juego.filas; i++){
			for(int j = 0; j < juego.columnas; j++){
				if(juego.tablero[i][j][1] == 0){
					if(juego.tablero[i][j][0] >= 0){
						g.drawString(Integer.toString(juego.tablero[i][j][0]), i*Juego.INCREMENTO+5, j*Juego.INCREMENTO+15);
						g.drawRect(i*Juego.INCREMENTO, j*Juego.INCREMENTO, Juego.INCREMENTO, Juego.INCREMENTO);
					}else{
						g.drawImage(mina, i*Juego.INCREMENTO, j*Juego.INCREMENTO, this);
						g.drawRect(i*Juego.INCREMENTO, j*Juego.INCREMENTO, Juego.INCREMENTO, Juego.INCREMENTO);
					}
				}else{
					g.drawImage(celda, i*Juego.INCREMENTO, j*Juego.INCREMENTO, this);
					g.drawRect(i*Juego.INCREMENTO, j*Juego.INCREMENTO, Juego.INCREMENTO, Juego.INCREMENTO);
				}
			}
		}
	}
	public void imageCanvas() {
		try {
			mina = ImageIO.read(new File("vista/Images/mina.png"));
			celda = ImageIO.read(new File("vista/Images/celda.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
