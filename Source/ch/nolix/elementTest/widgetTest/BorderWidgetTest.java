//package declaration
package ch.nolix.elementTest.widgetTest;

import ch.nolix.core.invalidArgumentExceptions.NonPositiveArgumentException;
import ch.nolix.core.invalidArgumentExceptions.NullArgumentException;
import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.BorderWidget;

//abstract test class
/**
 * A {@link BorderWidgetTest} is a test for {@link BorderWidget}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 90
 * @param <BW> The type of {@link BorderWidget} of a {@link BorderWidgetTest}.
 */
public abstract class BorderWidgetTest<BW extends BorderWidget<BW, ?>> extends WidgetTest<BW> {
	
	//test case
	public final void testCase_removeMaxWidth() {
		
		//setup
		final var borderWidget = createTestObject();
		borderWidget.setMaxWidth(500);
		
		//setup verification
		expect(borderWidget.hasMaxWidth());
		
		//execution
		borderWidget.removeMaxWidtht();
		
		//verification
		expectNot(borderWidget.hasMaxWidth());
	}
	
	public final void testCase_setBaseBorderColors() {
		
		//setup
		final var borderWidget = createTestObject();
		
		//execution
		borderWidget.getRefBaseLook().setBorderColors(Color.RED);
		
		//verification
			expect(borderWidget.getRefBaseLook()
			.getRecursiveOrDefaultLeftBorderColor())
			.isEqualTo(Color.RED);
			
			expect(borderWidget.getRefBaseLook()
			.getRecursiveOrDefaultRightBorderColor())
			.isEqualTo(Color.RED);
			
			expect(borderWidget.getRefBaseLook()
			.getRecursiveOrDefaultTopBorderColor())
			.isEqualTo(Color.RED);
			
			expect(borderWidget.getRefBaseLook()
			.getRecursiveOrDefaultBottomBorderColor())
			.isEqualTo(Color.RED);
	}
	
	//test case
	public final void testCase_setBaseBorderColors_borderColorIsNull() {
		
		//execution & verification
		expect(() -> createTestObject().getRefBaseLook().setBorderColors(null))
		.throwsException()
		.ofType(NullArgumentException.class)
		.withMessage("The given border color is null.");
	}
	
	//test case
	public final void testCase_setMaxWidth() {
		
		//setup
		final var borderWidget = createTestObject();
		
		//execution
		borderWidget.setMaxWidth(500);
		
		//verification
		expect(borderWidget.getMaxWidth()).isEqualTo(500);
	}
	
	//test case
	public final void testCase_setMaxWidth_maxWidthIsNegative() {
		
		//execution & verification
		expect(() -> createTestObject().setMaxWidth(-100))
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given value '-100' is not positive.");
	}
}
