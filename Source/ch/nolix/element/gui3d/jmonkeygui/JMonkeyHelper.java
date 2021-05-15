//package declaration
package ch.nolix.element.gui3d.jmonkeygui;

//own imports
import com.jme3.asset.AssetManager;
import com.jme3.system.JmeSystem;

//class
/**
 * The JMonkey helper provides method to handle the JMonkey engine.
 * Of the {@link JMonkeyHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 30
 */
public final class JMonkeyHelper {
	
	public static AssetManager getAssetManager() {
		return
		JmeSystem.newAssetManager(Thread.currentThread()
		.getContextClassLoader()
		.getResource("com/jme3/asset/Desktop.cfg"));
	}

	//constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private JMonkeyHelper() {}
}
