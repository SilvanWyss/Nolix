//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.guiapi.structureproperty.ContentAlignment;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class LayerTest extends Test {
	
	//method
	@TestCase
	public void testCase_clear_whenIsEmpty() {
		
		//setup
		final var testUnit = new Layer();
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution
		testUnit.clear();
		
		//verification
		expect(testUnit.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_clear_whenContainsAny() {
		
		//setup
		final var testUnit = new Layer();
		testUnit.setRootControl(new Label());
		
		//setup verification
		expect(testUnit.containsAny());
		
		//execution
		testUnit.clear();
		
		//verification
		expect(testUnit.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_reset() {
		
		//setup
		final var testUnit =
		new Layer()
		.setId("id")
		.setRole(LayerRole.MAIN_LAYER)
		.setOpacity(0.5)
		.setBackgroundColor(Color.AQUA)
		.setContentAlignment(ContentAlignment.BOTTOM_RIGHT)
		.setRootControl(new Label());
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasId());
		expectNot(testUnit.hasRole());
		expect(testUnit.getOpacity()).isEqualTo(1.0);
		expectNot(testUnit.hasBackground());
		expect(testUnit.getContentAlignment()).is(ContentAlignment.TOP);
		expect(testUnit.isEmpty());
	}
}
