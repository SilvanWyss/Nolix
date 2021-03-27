//package declaration
package ch.nolix.elementtest.formatelementtest;

//own imports
import ch.nolix.common.document.node.Node;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.element.formatelement.CascadingProperty;
import ch.nolix.element.formatelement.FormatElement;
import ch.nolix.element.formatelement.ValueDetermination;
import ch.nolix.element.gui.color.Color;

//class
public final class FormatElementWithNonCascadingPropertyTest extends Test {
	
	//static enum
	private enum CustomState {
		A,
		B,
		C,
		D
	}
	
	//static class
	private static final class CustomFormatElement extends FormatElement<CustomState> {
		
		//attribute
		public final CascadingProperty<CustomState, Color> color =
		new CascadingProperty<>(
			"Color",
			ValueDetermination.NON_CASCADING,
			CustomState.class,
			Color::fromSpecification,
			Color::getSpecification
		);
		
		//constructor
		public CustomFormatElement() {
			
			super(CustomState.A);
			
			reset();
		}
		
		//method
		public void switchToState(final CustomState state) {
			internalSwitchToState(state);
		}
	}
	
	//method
	@TestCase
	public void testCase_addOrChangeAttribute_1A() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.addOrChangeAttribute(Node.fromString("AColor(0xFF0000)"));
		
		//verification
		expect(testUnit.color.getValueOfState(CustomState.A)).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public void testCase_addOrChangeAttribute_1B() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.addOrChangeAttribute(Node.fromString("BColor(0xFF0000)"));
		
		//verification
		expect(testUnit.color.getValueOfState(CustomState.B)).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public void testCase_addOrChangeAttribute_1C() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.addOrChangeAttribute(Node.fromString("CColor(0xFF0000)"));
		
		//verification
		expect(testUnit.color.getValueOfState(CustomState.C)).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public void testCase_addOrChangeAttribute_1D() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.addOrChangeAttribute(Node.fromString("DColor(0xFF0000)"));
		
		//verification
		expect(testUnit.color.getValueOfState(CustomState.D)).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var testUnit = new CustomFormatElement();
		
		//verification
		expect(testUnit.getCurrentState()).isSameAs(CustomState.A);
		expect(testUnit.color.getName()).isEqualTo("Color");
		expectNot(testUnit.color.hasValue());
	}
	
	//method
	@TestCase
	public void testCase_getSpecification() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.B, Color.BLUE);
		testUnit.color.setValueForState(CustomState.C, Color.RED);
		testUnit.color.setValueForState(CustomState.D, Color.GREEN);
		
		//execution
		final var result = testUnit.getSpecification();
		
		//verification
		expect(result).hasStringRepresentation(
			"CustomFormatElement(AColor(0x000000),BColor(0x0000FF),CColor(0xFF0000),DColor(0x008000))"
		);
	}
	
	//method
	@TestCase
	public void testCase_getValue_1A() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.switchToState(CustomState.A);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_1B() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.switchToState(CustomState.B);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_1C() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.switchToState(CustomState.C);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_1D() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.switchToState(CustomState.D);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_2A() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.B, Color.BLUE);
		testUnit.color.setValueForState(CustomState.C, Color.RED);
		testUnit.color.setValueForState(CustomState.D, Color.GREEN);
		testUnit.switchToState(CustomState.A);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_2B() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.B, Color.BLUE);
		testUnit.color.setValueForState(CustomState.C, Color.RED);
		testUnit.color.setValueForState(CustomState.D, Color.GREEN);
		testUnit.switchToState(CustomState.B);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_getValue_2C() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.B, Color.BLUE);
		testUnit.color.setValueForState(CustomState.C, Color.RED);
		testUnit.color.setValueForState(CustomState.D, Color.GREEN);
		testUnit.switchToState(CustomState.C);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public void testCase_getValue_2D() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		testUnit.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.B, Color.BLUE);
		testUnit.color.setValueForState(CustomState.C, Color.RED);
		testUnit.color.setValueForState(CustomState.D, Color.GREEN);
		testUnit.switchToState(CustomState.D);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.GREEN);
	}
	
	//method
	@TestCase
	public void testCase_switchToState_1A() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.switchToState(CustomState.A);
		
		//verification
		expect(testUnit.getCurrentState()).isSameAs(CustomState.A);
	}
	
	//method
	@TestCase
	public void testCase_switchToState_1B() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.switchToState(CustomState.B);
		
		//verification
		expect(testUnit.getCurrentState()).isSameAs(CustomState.B);
	}
	
	//method
	@TestCase
	public void testCase_switchToState_1C() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.switchToState(CustomState.C);
		
		//verification
		expect(testUnit.getCurrentState()).isSameAs(CustomState.C);
	}
	
	//method
	@TestCase
	public void testCase_switchToState_1D() {
		
		//setup
		final var testUnit = new CustomFormatElement();
		
		//execution
		testUnit.switchToState(CustomState.D);
		
		//verification
		expect(testUnit.getCurrentState()).isSameAs(CustomState.D);
	}
}
