//package declaration
package ch.nolix.systemTest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new SystemTestPool().run();
	}
	
	//visibility-reducing constructor
	private Launcher() {}
}
