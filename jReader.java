public class jReader{
	public static void main(String[] args) {
		Boolean done = false;
		Reader reader = new Reader();
		String path;
		Menu menu = new Menu();
		int selectedOption;
		menu.showMainOptions();
		while(!done){
			selectedOption = menu.chooseOption();
			switch (selectedOption) {
				case 1:
					reader.goUp();
					break;
				case 2:
					// reader.setPath( menu.promptPath() );
					reader.showCurrentPath();
					break;
				case 3:
					reader.readDir();
					break;
				case 9:
					menu.showMainOptions();
					break;
				default:
					menu.sayGoodBye();
					done = true;
			}
		}
	}
}