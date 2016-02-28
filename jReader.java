public class jReader{
	public static void main(String[] args) {
		Boolean done = false;
		Reader reader = new Reader();
		String path;
		Menu menu = new Menu();
		int selectedOption;

		while(!done){
			menu.showMainOptions();
			selectedOption = menu.chooseOption();
			switch (selectedOption) {
				case 1:
					// reader.goUp();
					reader.showCurrentPath();
					break;
				case 2:
					// reader.setPath( menu.promptPath() );
					reader.showCurrentPath();
					break;
				case 3:
					reader.readDir();
					break;
				default:
					menu.sayGoodBye();
					done = true;
			}
		}
	}
}