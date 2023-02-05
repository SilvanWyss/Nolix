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
	}
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//execution
		final var testUnit = new CustomFormatElement();
		
		//verification
		expect(testUnit.color.getName()).isEqualTo("Color");
	}
}
