//package declaration
package ch.nolix.systemtest.elementtest.multistateelementtest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.multistateelement.CascadingProperty;
import ch.nolix.system.element.multistateelement.MultiStateElement;
import ch.nolix.system.graphic.color.Color;

//class
public final class MultiStateElementWithCascadingPropertyTest extends Test {
	
	//static enum
	private enum CustomState {
		A,
		B,
		C,
		D
	}
	
	//static class
	private static final class CustomFormatElement extends MultiStateElement<CustomFormatElement, CustomState> {
		
		//attribute
		public final CascadingProperty<CustomState, Color> color =
		new CascadingProperty<>(
			"Color",
			CustomState.class,
			Color::fromSpecification,
			Color::getSpecification,
			Color.WHITE
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
