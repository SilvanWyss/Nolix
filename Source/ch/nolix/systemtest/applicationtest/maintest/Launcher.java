//package declaration
package ch.nolix.systemtest.applicationtest.maintest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new MainTestPool().run();
	}
	
	//constructor
	private Launcher() {}
}