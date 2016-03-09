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
}