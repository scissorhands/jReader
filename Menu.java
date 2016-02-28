import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Menu{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Menu(){
	}

	public void showMainOptions(){
		System.out.println("\nOptions:"
			+"\n 1. Go up"
			+"\n 2. Set path"
			+"\n 3. Read current"
			+"\n  "
			+"\n Default: exit "
		+"");
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