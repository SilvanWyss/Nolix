//package declaration
package ch.nolix.systemtest.entitytest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new EntityTestPool().run();
	}
	
	//visibility-reduced constructor
	private Launcher() {}
}
