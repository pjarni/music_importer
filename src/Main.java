import javax.swing.SwingUtilities;

import net.alexove.musicimporter.gui.MainFrm;



public class Main {
	public static void main(String[] args) {
//		MusicImporter mi=new MusicImporter("/home/alexove/Descargas/Alejandro camara - Contra puntos", "/home/alexove/prueba_importacion");
//		mi.importar();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				MainFrm mainFrm=new MainFrm();
				mainFrm.setVisible(true);
			}
		});
		
	}
}
