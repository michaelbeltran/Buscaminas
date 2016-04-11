package vista;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import vista.Tablero;
import modelo.Juego;
import vista.Tablero;

public class VistaTablero extends JFrame implements MouseListener{
	private Juego juego;
	private Tablero tablero;
	public VistaTablero(Juego datosJuego){
		super("Buscaminas");
		juego = datosJuego;
		tablero = new Tablero(juego);
		tablero.addMouseListener(this);
		this.add(tablero, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setSize(juego.filas*Juego.INCREMENTO, 
					juego.columnas*Juego.INCREMENTO+23);
	}
	//Invoked when a mouse button has been pressed on a component.
	public void mousePressed(MouseEvent e){
		System.out.println(e.getX());
		System.out.println(e.getY());
	}
	//Invoked when the mouse button has been clicked (pressed and released) on a component.
	public void mouseClicked(MouseEvent e){}
	//Invoked when the mouse enters a component.
	public void mouseEntered(MouseEvent e){}		
	//Invoked when the mouse exits a component.
	public void mouseExited(MouseEvent e){}		
	
	//Invoked when a mouse button has been released on a component.
	public void mouseReleased(MouseEvent e){}
		
}
