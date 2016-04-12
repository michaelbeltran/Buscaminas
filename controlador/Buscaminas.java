package controlador;
import modelo.Juego;
import vista.VistaTablero;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import conf.Configuracion;
public class Buscaminas implements MouseListener{
	public static Juego juego;
	public static VistaTablero tablero;
	public Buscaminas(){
		tablero = new VistaTablero();
		tablero.menu();
		tablero.mouseListMenu(this);
	}
	public static void main(String args[]){
		Buscaminas bus = new Buscaminas();
	}
	//Invoked when a mouse button has been pressed on a component.
	public void mousePressed(MouseEvent e){
	
	}
	//Invoked when the mouse button has been clicked (pressed and released) on a component.
	public void mouseClicked(MouseEvent e){
		String b;
		if(e.getSource() instanceof JButton){
			JButton bm = (JButton)e.getSource();
			b = bm.getText();
		}else
			b = "juego";
		switch(b){
			case "8 x 8":
				juego = new Juego(Configuracion.INTER);
				tablero.juego(juego, this);
				break;
			case "16 x 16":
				juego = new Juego(Configuracion.MEDIO);
				tablero.juego(juego, this);
				break;
			case "16 x 30":
				juego = new Juego(Configuracion.EXPER);
				tablero.juego(juego, this);
				break;
			case "juego":
				juego.mostrar(e.getX(), e.getY());
				tablero.rePintar();
				break;
			default:
		}
	}
	//Invoked when the mouse enters a component.
	public void mouseEntered(MouseEvent e){}		
	//Invoked when the mouse exits a component.
	public void mouseExited(MouseEvent e){}		
	
	//Invoked when a mouse button has been released on a component.
	public void mouseReleased(MouseEvent e){}
}
