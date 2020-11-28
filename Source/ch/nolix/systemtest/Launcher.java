//package declaration
package ch.nolix.systemtest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new SystemTestPool().run();
	}
	
	//visibility-reduced constructor
	private Launcher() {}
}
