//package declaration
package ch.nolix.element.JMonkeyGUI;

//own imports
import com.jme3.asset.AssetManager;
import com.jme3.system.JmeSystem;

//class
/**
 * The JMonkey helper provides method to handle the JMonkey engine.
 * Of the {@link JMonkeyHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 10
 */
public final class JMonkeyHelper {
	
	public static AssetManager getAssetManager() {
		return
		JmeSystem.newAssetManager(Thread.currentThread()
		.getContextClassLoader()
		.getResource("com/jme3/asset/Desktop.cfg"));
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private JMonkeyHelper() {}
}
