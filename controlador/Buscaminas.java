package controlador;
import modelo.Juego;
import vista.VistaTablero;
public class Buscaminas{
	public Juego juego;
	public VistaTablero tab;
	public Buscaminas(){
		juego = new Juego();
		tab = new VistaTablero(juego);
	}
	public static void main(String args[]){
		Buscaminas busMinas = new Buscaminas();
	}
}
