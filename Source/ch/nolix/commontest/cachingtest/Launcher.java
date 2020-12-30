//package declaration
package ch.nolix.commontest.cachingtest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new CachingTestPool().run();
	}
	
	//visibility-reduced constructor
	private Launcher() {}
}
