//package declaration
package ch.nolix.commonTest.licenseTest;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new LicenseTestPool().run();
	}
	
	//access-reducing constructor
	private Launcher() {}
}
