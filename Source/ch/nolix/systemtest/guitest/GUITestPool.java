//package declaration
package ch.nolix.systemtest.guitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.guitest.basetest.BaseTestPool;
import ch.nolix.systemtest.guitest.colortest.ColorTestPool;
import ch.nolix.systemtest.guitest.containerwidgettest.ContainerWidgetTestPool;
import ch.nolix.systemtest.guitest.imagetest.ImageTestPool;
import ch.nolix.systemtest.guitest.widgettest.WidgetTestPool;

//class
public final class GUITestPool extends TestPool {
	
	//constructor
	public GUITestPool() {
		super(
			new BaseTestPool(),
			new ColorTestPool(),
			new ContainerWidgetTestPool(),
			new ImageTestPool(),
			new WidgetTestPool()
		);
	}
}
