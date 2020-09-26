//package declaration
package ch.nolix.elementTest.widgetTest;

import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.NonPositiveArgumentException;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.BorderWidget;

//class
/**
 * A {@link BorderWidgetTest} is a test for {@link BorderWidget}.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 90
 * @param <BW> The type of {@link BorderWidget} of a {@link BorderWidgetTest}.
 */
public abstract class BorderWidgetTest<BW extends BorderWidget<BW, ?>> extends WidgetTest<BW> {
	
	//method
	@TestCase
	public final void testCase_removeMaxWidth() {
		
		//setup
		final var borderWidget = createTestUnit();
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
		final var borderWidget = createTestUnit();
		
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
	
	//method
	@TestCase
	public final void testCase_setBaseBorderColors_borderColorIsNull() {
		
		//execution & verification
		expect(() -> createTestUnit().getRefBaseLook().setBorderColors(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given border color is null.");
	}
	
	//method
	@TestCase
	public final void testCase_setMaxWidth() {
		
		//setup
		final var borderWidget = createTestUnit();
		
		//execution
		borderWidget.setMaxWidth(500);
		
		//verification
		expect(borderWidget.getMaxWidth()).isEqualTo(500);
	}
	
	//method
	@TestCase
	public final void testCase_setMaxWidth_maxWidthIsNegative() {
		
		//execution & verification
		expect(() -> createTestUnit().setMaxWidth(-100))
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given max width '-100' is not positive.");
	}
}
