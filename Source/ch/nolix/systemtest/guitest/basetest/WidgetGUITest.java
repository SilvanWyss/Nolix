//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
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
			expect(result.getBackgroundColor()).isEqualTo(WidgetGUI.DEFAULT_BACKGROUND_COLOR);
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
	
	//method
	@TestCase
	public final void testCase_removeTopLayer_whenContains1Layer() {
		
		//setup
		final var layer = new Layer();
		
		try (final var testUnit = createTestUnit()) {
			
			//setup
			testUnit.pushLayer(layer);
			
			//execution
			testUnit.removeTopLayer();
			
			//verification
			expect(testUnit.isEmpty());
		}
	}
	
	//method
	@TestCase
	public final void testCase_removeTopLayer_whenIsEmpty() {
		try (final var testUnit = createTestUnit()) {
			
			//execution & verification
			expectRunning(testUnit::removeTopLayer).throwsException().ofType(EmptyArgumentException.class);
		}
	}
}
