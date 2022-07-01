//package declaration
package ch.nolix.templatetest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.templatetest.guilooktest.GUILooksTestPool;
import ch.nolix.templatetest.texturetest.TextureTestPool;

//class
public final class TemplatesTestPool extends TestPool {
	
	//constructor
	public TemplatesTestPool() {
		super(new GUILooksTestPool(), new TextureTestPool());
	}
}
