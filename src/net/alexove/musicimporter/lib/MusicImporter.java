package net.alexove.musicimporter.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class MusicImporter {
	private File dir_origen;
	private File dir_destino;
	
	public MusicImporter(String dir_origen,String dir_destino) {
		this.dir_origen=new File(dir_origen);
		this.dir_destino=new File(dir_destino);
	}
	
	public void importar(){
		File[] archivos=dir_origen.listFiles();
		for(File archivo:archivos){
			if(archivo.isDirectory()){
				importarDirectorio(archivo);
			}else{
				importarArchivo(archivo.getAbsolutePath());
			}
		}
	}

	private void importarDirectorio(File directorio) {
		
	}
	private void importarArchivo(String path) {
		if(path.endsWith("mp3") || path.endsWith("MP3")){
			try {
				ID3v2 tags=new Mp3File(path).getId3v2Tag();
				
				String artista=tags.getArtist();
				String album=tags.getAlbum();
				String archivo=tags.getTrack()+" - "+tags.getTitle()+".mp3";
				
				File directorio_destino=new File(dir_destino.getAbsolutePath()+"/"+artista+"/"+album);
				if(!directorio_destino.exists()){
					directorio_destino.mkdirs();
				}
				OutputStream out=new FileOutputStream(new File(directorio_destino+"/"+archivo));
				Files.copy(new File(path).getAbsoluteFile().toPath(), out);
				
//				Files.copy(new InputStream(new File(dir_origen.getAbsolutePath()+"/"+path)), new FileOutputStream(new File(directorio+"/"+archivo)));
				
			} catch (UnsupportedTagException | InvalidDataException
					| IOException e) {
				Logger.getLogger(MusicImporter.class.getName()).log(Level.SEVERE, e.toString());
			}
		}
	}	
}
