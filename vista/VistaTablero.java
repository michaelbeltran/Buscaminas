package vista;
import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;
import vista.Tablero;
import modelo.Juego;
import vista.Tablero;
import conf.Configuracion;

public class VistaTablero extends JFrame{
	private Juego juego;
	private Tablero tablero=null;
	private JButton bInter;
	private JButton bMedio;
	private JButton bExperto;
	public VistaTablero(){
		super("Buscaminas");
	}
	public void juego(Juego datosJuego, MouseListener l){
		int w,h;
		if(System.getProperty("os.name").equals("Windows 7")){
			w=16;
			h=38;
		}else{
			w=0;
			h=23;
		}
		juego = datosJuego;
		tablero = new Tablero(juego);
		tablero.addMouseListener(l);
		this.remove(bInter);
		this.remove(bMedio);
		this.remove(bExperto);
		this.add(tablero, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setSize(Configuracion.filas*Configuracion.INCREMENTO+Configuracion.filas+w, 
					Configuracion.columnas*Configuracion.INCREMENTO+Configuracion.columnas+h);
	}
	public void rePintar(){
		tablero.repaint();
	}
	public void menu(){
		bInter = new JButton("8 x 8");
		bMedio = new JButton("16 x 16");
		bExperto = new JButton("16 x 30");
		if(tablero != null)
			this.remove(tablero);
		this.add(BorderLayout.WEST, bInter);
		this.add(BorderLayout.CENTER, bMedio);
		this.add(BorderLayout.EAST, bExperto);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setSize(250, 100);
	}
	public void mouseListMenu(MouseListener l){
		bInter.addMouseListener(l);
		bMedio.addMouseListener(l);
		bExperto.addMouseListener(l);
	}
		
}
