//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.LayerStack;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class LayerStackTest extends Test {
	
	//method
	@TestCase
	public void testCase_getStoredControls_whenIsEmpty() {
		
		//setup
		final var testUnit = LayerStack.forWebGui(new WebGui());
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution
		final var result = testUnit.getStoredControls();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getStoredControls_whenContains1Control() {
		
		//setup
		final var testUnit = LayerStack.forWebGui(new WebGui());
		final var label = new Label();
		testUnit.pushLayerWithRootControl(label);
		
		//execution
		final var result = testUnit.getStoredControls();
		
		//verification
		expect(result).containsExactlyInSameOrder(label);
	}
	
	//method
	@TestCase
	public void testCase_getStoredControls_whenContains4Control() {
		
		//setup
		final var testUnit = LayerStack.forWebGui(new WebGui());
		final var verticalStack = new VerticalStack();
		final var label1 = new Label();
		final var label2 = new Label();
		final var label3 = new Label();
		verticalStack.addControl(label1, label2, label3);
		testUnit.pushLayerWithRootControl(verticalStack);
		
		//execution
		final var result = testUnit.getStoredControls();
		
		//verification
		expect(result).containsExactlyInSameOrder(verticalStack, label1, label2, label3);
	}
}
