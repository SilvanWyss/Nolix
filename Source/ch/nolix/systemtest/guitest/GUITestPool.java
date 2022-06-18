//package declaration
package ch.nolix.systemtest.guitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.guitest.colortest.ColorTestPool;
import ch.nolix.systemtest.guitest.containerwidgettest.ContainerWidgetTestPool;
import ch.nolix.systemtest.guitest.imagetest.ImageTestPool;
import ch.nolix.systemtest.guitest.maintest.MainTestPool;
import ch.nolix.systemtest.guitest.structurepropertytest.StructurePropertyTestPool;
import ch.nolix.systemtest.guitest.widgettest.WidgetTestPool;

//class
public final class GUITestPool extends TestPool {
	
	//constructor
	public GUITestPool() {
		super(
			new MainTestPool(),
			new ColorTestPool(),
			new ContainerWidgetTestPool(),
			new ImageTestPool(),
			new StructurePropertyTestPool(),
			new WidgetTestPool()
		);
	}
}
