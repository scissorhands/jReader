import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.ArrayList;

public class Menu{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private int selection;

	public Menu(){
	}

	public void showMainOptions(){
		System.out.println("\nOptions:"
			+"\n 1. Go up"
			+"\n 2. Go to folder"
			+"\n 3. Read current"
			+"\n  "
			+"\n Default: exit "
		+"");
	}

	public File selectFolder(File[] files){
		boolean isItDone;
		isItDone = false;
        int count = 0;
        ArrayList<File> listFolders = new ArrayList<File>();

		while( !isItDone ){
			System.out.println("Choose: " + files.length );
            count = 0;
			for(int i=0;i<files.length;i++){
				if(files[i].isDirectory()){
					System.out.println( "[ " + count + " ]  " + files[i].getName() );
                    listFolders.add( files[i] );
                    count++;
				}
			}
			selection = chooseOption();
			if( selection >= 0 && selection < files.length ){
				isItDone = true;
			}
		}
		return listFolders.get(selection);
	}

	public int chooseOption(){
		int i = 0;
		try{
			i = Integer.parseInt(br.readLine());
		} catch( IOException e ){
			System.out.println( "Error: " + e.getMessage() );
		} catch( NumberFormatException nfe ){
			System.out.println( "Error: " + nfe.toString() );
		}
		return i;
	}

	public void sayGoodBye(){
		System.out.println( "Good bye (:");
	}
}