//package declaration
package ch.nolix.techTest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new TechTestPool().run();
	}
	
	//access-reducing constructor
	private Launcher() {}
}
