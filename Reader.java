import java.io.File;

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

	public void readDir(){
		String[] fileNames = this.folder.list();
		for(int i=0;i<fileNames.length;i++){
			System.out.println( fileNames[i] );
		}
	}
}