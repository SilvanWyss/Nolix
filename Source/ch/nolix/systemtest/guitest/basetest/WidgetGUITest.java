//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.base.WidgetGUI;

//class
public abstract class WidgetGUITest<WG extends WidgetGUI<WG>> extends GUITest<WG> {
	
	//method
	@TestCase
	public final void testCase_createWidgetGUI() {
		try (final var result = createTestUnit()) {
			
			//verification
			expect(result.isEmpty());
		}
	}
	
	//method
	@TestCase
	public final void testCase_pushLayer() {
		
		//setup
		final var layer = new Layer();
		
		try (final var testUnit = createTestUnit()) {
			
			//execution
			testUnit.pushLayer(layer);
			
			//verification
			expect(testUnit.getRefLayers().getElementCount()).isEqualTo(1);
			expect(layer.getParentGUI()).isSameAs(testUnit);
		}
	}
}
