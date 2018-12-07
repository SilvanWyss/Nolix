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
	@Override
	public boolean isRootGUI() {
		return true;
	}
	
	//method
	@Override
	protected void paint() {}
}
