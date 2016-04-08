import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import java.io.RandomAccessFile;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Reader{
	private File folder;

	public Reader(){
		this.folder = new File(".");
		showCurrentPath();
	}

	public Reader( String pathString ){
		this.folder = new File(pathString);
		showCurrentPath();
	}

	public void showCurrentPath(){
        System.out.println("Curren path " + this.folder.getAbsolutePath() );
	}

    public String getCurrentPath(){
        try{
            return this.folder.getCanonicalPath();
        } catch ( IOException ioe ){
            return this.folder.getAbsolutePath();
        }
    }

	public File[] getChildren(){
		return this.folder.listFiles();
	}

    public void setCurrentPath( String newPath ){
        this.folder = new File( getCurrentPath() + "/" + newPath  );
        showCurrentPath();
    }

	public void readDir(){
		String[] fileNames = this.folder.list();
		for(int i=0;i<fileNames.length;i++){
			System.out.println( fileNames[i] );
		}
	}

	public void goUp(){
		if( this.folder.getParentFile() == null ){
			this.folder = new File( folder.getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath() );
		} else {
			this.folder = new File( folder.getAbsoluteFile().getParentFile().getAbsolutePath() );
		}
		showCurrentPath();
	}

    public void cleanDir() {

        File[] files = this.folder.listFiles();
        for(int i=0;i<files.length;i++){
            if(files[i].isFile()){
                try {
                    readMp3File( this.folder.getAbsolutePath() + "/" + files[i].getName() );
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printId3v1Content( Mp3File mp3file ){
        if( mp3file.hasId3v1Tag() ){
            ID3v1 id3v1Tag = mp3file.getId3v1Tag();
            System.out.println(
               "Id3v1:"
                +"\nTrack: "       + id3v1Tag.getTrack()  
                +"\nArtist: "      + id3v1Tag.getArtist() 
                +"\nTitle: "       + id3v1Tag.getTitle()  
                +"\nAlbum: "       + id3v1Tag.getAlbum()  
                +"\nYear: "        + id3v1Tag.getYear()   
                +"\nGenre: "       + id3v1Tag.getGenre()  
                +"\nComment: "     + id3v1Tag.getComment()
                +"\n"
            );
            waitSecs( 1.5 );
        } else {
            System.out.print("No Id3v1 tags were found. \n\n");
        }
    }

    private void waitSecs( double seconds ){
        double milisecs = seconds * 1000;
        try {
            Thread.sleep( (long)milisecs );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printId3v2Content( Mp3File mp3file ){
        if (mp3file.hasId3v2Tag()) {
            ID3v2 id3v2Tag = mp3file.getId3v2Tag();
            System.out.print(
                "Id3v2:"
                +"\nTrack: " + id3v2Tag.getTrack()                                               
                +"\nArtist: " + id3v2Tag.getArtist()                                            
                +"\nTitle: " + id3v2Tag.getTitle()                                              
                +"\nAlbum: " + id3v2Tag.getAlbum()                                              
                +"\nYear: " + id3v2Tag.getYear()                                                
                +"\nGenre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")"
                +"\nComment: " + id3v2Tag.getComment()                                          
                +"\nComposer: " + id3v2Tag.getComposer()                                        
                +"\nPublisher: " + id3v2Tag.getPublisher()                                      
                +"\nOriginal artist: " + id3v2Tag.getOriginalArtist()                           
                +"\nAlbum artist: " + id3v2Tag.getAlbumArtist()                                 
                +"\nCopyright: " + id3v2Tag.getCopyright()                                      
                +"\nURL: " + id3v2Tag.getUrl()                                                  
                +"\nEncoder: " + id3v2Tag.getEncoder()
                +"\n"
                +"\n"                                          
            );
            waitSecs( 1.5 );
        } else {
            System.out.print("No Id3v2 tags were found. \n\n");
        }
    }

    private void readMp3File(String route) throws InvalidDataException, IOException, UnsupportedTagException {
        System.out.println( "File: " + route );
        try{
            Mp3File mp3file = new Mp3File( route );
            printId3v1Content( mp3file );
            printId3v2Content( mp3file );
        } catch(InvalidDataException ide){
            System.out.println("Error: " + ide.getMessage() );
        }
    }
}