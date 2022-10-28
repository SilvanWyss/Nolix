//package declaration
package ch.nolix.systemtest.guitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.guitest.colortest.ColorTestPool;
import ch.nolix.systemtest.guitest.imagetest.ImageTestPool;
import ch.nolix.systemtest.guitest.structurepropertytest.StructurePropertyTestPool;

//class
public final class GUITestPool extends TestPool {
	
	//constructor
	public GUITestPool() {
		super(
			new ColorTestPool(),
			new ImageTestPool(),
			new StructurePropertyTestPool()
		);
	}
}
