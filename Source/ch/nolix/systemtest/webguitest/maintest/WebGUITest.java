//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.WebGUI;

//class
public final class WebGUITest extends Test {
	
	//method
	@TestCase
	public void testCase_constructor() {
		
		//execution
		final var result = new WebGUI();
		
		//verification
		expect(result.getTitle()).isEqualTo(WebGUI.DEFAULT_TITLE);
		expect(result.getIcon()).isEqualTo(WebGUI.DEFAULT_ICON);
		expect(result.getBackgroundColor()).isEqualTo(WebGUI.DEFAULT_BACKGROUND_COLOR);
		expect(result.isEmpty());
		expect(result.getTokens()).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getOriControls_whenIsEmpty() {
		
		//setup
		final var testUnit = new WebGUI();
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution
		final var result = testUnit.getOriControls();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getOriControls_whenContains1Control() {
		
		//setup
		final var testUnit = new WebGUI();
		final var label = new Label();
		testUnit.pushLayerWithRootControl(label);
		
		//execution
		final var result = testUnit.getOriControls();
		
		//verification
		expect(result).containsExactlyInSameOrder(label);
	}
	
	//method
	@TestCase
	public void testCase_getOriControls_whenContains4Control() {
		
		//setup
		final var testUnit = new WebGUI();
		final var verticalStack = new VerticalStack();
		final var label1 = new Label();
		final var label2 = new Label();
		final var label3 = new Label();
		verticalStack.addControl(label1, label2, label3);
		testUnit.pushLayerWithRootControl(verticalStack);
		
		//execution
		final var result = testUnit.getOriControls();
		
		//verification
		expect(result).containsExactlyInSameOrder(verticalStack, label1, label2, label3);
	}
}
