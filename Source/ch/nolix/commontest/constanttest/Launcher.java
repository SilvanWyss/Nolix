//package declaration
package ch.nolix.commontest.constanttest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new ConstantTestPool().run();
	}
	
	//visibility-reduced constructor
	private Launcher() {}
}
