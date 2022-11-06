//package declaration
package ch.nolix.systemtest.webguitest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new WebGUITestPool().run();
	}
	
	//constructor
	private Launcher() {}
}
