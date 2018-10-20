//package declaration
package ch.nolix.system.GUIClientoid;

//package-visible class
final class BackGUIClientoidApplicationGUI extends BackGUIClientoidGUI {

	//constructor
	public BackGUIClientoidApplicationGUI() {
		reset();
		approveProperties();
	}
	
	//method
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	protected void paint() {}
}
