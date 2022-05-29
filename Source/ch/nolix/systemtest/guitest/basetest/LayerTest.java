//package declaration
package ch.nolix.systemtest.guitest.basetest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.gui.widgetgui.Layer;

//class
public final class LayerTest extends Test {
	
	@TestCase
	public void testCase_addOrChangeAttribute_whenNoneOpacityAttributeIsGiven() {
		
		//setup
		final var testUnit = new Layer();
		
		//setup verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(1.0);
		
		//execution
		testUnit.addOrChangeAttribute("Background(Color(White))");
		
		//verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(1.0);
	}
	
	@TestCase
	public void testCase_addOrChangeAttribute_whenCorrectOpacityAttributeIsGiven() {
		
		//setup
		final var testUnit = new Layer();
		
		//setup verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(1.0);
		
		//execution
		testUnit.addOrChangeAttribute("Opacity(25%)");
		
		//verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(0.25);
	}
	
	@TestCase
	public void testCase_addOrChangeAttribute_whenIncorrectOpacityAttributeIsGiven() {
		
		//setup
		final var testUnit = new Layer();
		
		//setup verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(1.0);
		
		//execution & verification
		expectRunning(() -> testUnit.addOrChangeAttribute("Opacity(25)"))
		.throwsException()
		.ofType(InvalidArgumentException.class)
		.withMessage("The given String '25' does not end with '%' symbol.");
		
		//verification
		expect(testUnit.getOpacityPercentage()).isEqualTo(1.0);
	}
}
