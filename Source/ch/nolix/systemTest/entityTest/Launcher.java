//package declaration
package ch.nolix.systemTest.entityTest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new EntityTestPool().run();
	}
	
	//visibility-reducing constructor
	private Launcher() {}
}
