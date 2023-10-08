//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class WebGuiTest extends Test {
	
	//method
	@TestCase
	public void testCase_constructor() {
		
		//execution
		final var result = new WebGui();
		
		//verification
		expect(result.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
		expect(result.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
		expect(result.getBackgroundColor()).isEqualTo(WebGui.DEFAULT_BACKGROUND_COLOR);
		expect(result.isEmpty());
		expect(result.getTokens()).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_getStoredControlByInternalId_whenDoesNotContainSuchAControl() {
		
		//setup
		final var control = new Label();
		final var testUnit = new WebGui();
		
		//execution & verification
		expectRunning(() -> testUnit.getStoredControlByInternalId(control.getInternalId()))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_getStoredControlByInternalId_A1() {
		
		//setup
		final var control = new Label();
		final var testUnit = new WebGui();
		testUnit.pushLayerWithRootControl(control);
		
		//execution
		final var result = testUnit.getStoredControlByInternalId(control.getInternalId());
		
		//verification
		expect(result).is(control);
	}
	
	//method
	@TestCase
	public void testCase_getStoredControlByInternalId_A2() {
		
		//setup
		final var control = new Label();
		final var testUnit = new WebGui();
		testUnit.pushLayerWithRootControl(new VerticalStack().addControl(new Label(), new Label(), new Label()));
		testUnit.pushLayerWithRootControl(new VerticalStack().addControl(new Label(), new Label(), control));
		
		//execution
		final var result = testUnit.getStoredControlByInternalId(control.getInternalId());
		
		//verification
		expect(result).is(control);
	}
	
	//method
	@TestCase
	public void testCase_getStoredControls_whenIsEmpty() {
		
		//setup
		final var testUnit = new WebGui();
		
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
		final var testUnit = new WebGui();
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
		final var testUnit = new WebGui();
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
