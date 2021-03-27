//package declaration
package ch.nolix.elementtest.formatelementtest;

//own imports
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;
import ch.nolix.element.formatelement.CascadingProperty;
import ch.nolix.element.formatelement.FormatElement;
import ch.nolix.element.formatelement.ValueDetermination;
import ch.nolix.element.gui.color.Color;

//class
public final class FormatElementWithCascadingPropertyTest extends Test {
	
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
			ValueDetermination.CASCADING,
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
		public void addChild(final CustomFormatElement child) {
			internalAddChild(child);
		}
		
		//method
		public void switchToState(final CustomState state) {
			internalSwitchToState(state);
		}
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
	public void testCase_getValue_whenBelongsToParent_1A() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.A, Color.BLUE);
		
		//setup part 2
		testUnit.switchToState(CustomState.A);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_1B() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.A, Color.BLUE);
		
		//setup part 2
		testUnit.switchToState(CustomState.B);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_1C() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.A, Color.BLUE);
		
		//setup part 2
		testUnit.switchToState(CustomState.C);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_1D() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		testUnit.color.setValueForState(CustomState.A, Color.BLUE);
		
		//setup part 2
		testUnit.switchToState(CustomState.D);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLUE);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_2A() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		
		//setup part 2
		testUnit.switchToState(CustomState.A);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_2B() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		
		//setup part 2
		testUnit.switchToState(CustomState.B);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_2C() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		
		//setup part 2
		testUnit.switchToState(CustomState.C);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
	
	//method
	@TestCase
	public void testCase_getValue_whenBelongsToParent_2D() {
		
		//setup part 1
		final var testUnit = new CustomFormatElement();
		final var parent = new CustomFormatElement();
		parent.addChild(testUnit);
		parent.color.setValueForState(CustomState.A, Color.BLACK);
		
		//setup part 2
		testUnit.switchToState(CustomState.D);
		
		//execution
		final var result = testUnit.color.getValue();
		
		//verification
		expect(result).isEqualTo(Color.BLACK);
	}
}
