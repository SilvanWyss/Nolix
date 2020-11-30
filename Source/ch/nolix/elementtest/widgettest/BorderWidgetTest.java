//package declaration
package ch.nolix.elementtest.widgettest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.BorderWidget;

//class
/**
 * A {@link BorderWidgetTest} is a test for {@link BorderWidget}s.
 * 
 * @author Silvan Wyss
 * @date 2018-12-11
 * @lines 150
 * @param <BW> The type of the {@link BorderWidget}s a {@link BorderWidgetTest} is for.
 */
public abstract class BorderWidgetTest<BW extends BorderWidget<BW, ?>> extends WidgetTest<BW> {
	
	//method
	@TestCase
	public final void testCase_getWidth_whenHasMinWidthThatIsBiggerThanTheWidth() {
		
		//setup part 1
		final var testUnit = createTestUnit();
		testUnit.recalculate();
				
		//setu part 2
		final var targetMinWidth = testUnit.getWidth() + 100;
		testUnit.setMinWidth(targetMinWidth);
		testUnit.recalculate();
		
		//execution
		final var result = testUnit.getWidth();
		
		//verification
		expect(result).isEqualTo(targetMinWidth);
	}
	
	//method
	@TestCase
	public final void testCase_removeMaxWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMaxWidth(500);
		
		//setup verification
		expect(testUnit.hasMaxWidth());
		
		//execution
		testUnit.removeMaxWidtht();
		
		//verification
		expectNot(testUnit.hasMaxWidth());
	}
	
	//method
	@TestCase
	public final void testCase_resetBorderWidget() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.setMaxWidth(500);
		testUnit.setMaxHeight(200);
		
		//setup verification
		expect(testUnit.hasMinWidth());
		expect(testUnit.hasMinHeight());
		expect(testUnit.hasMaxWidth());
		expect(testUnit.hasMaxHeight());
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasMinWidth());
		expectNot(testUnit.hasMinHeight());
		expectNot(testUnit.hasMaxWidth());
		expectNot(testUnit.hasMaxHeight());
	}
	
	//method
	@TestCase
	public final void testCase_setBorderColorsOfBaseLook() {
		
		//setup
		final var testUnit = createTestUnit();
				
		//execution
		testUnit.getRefBaseLook().setBorderColors(Color.RED);
		
		//verification
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultLeftBorderColor()).isEqualTo(Color.RED);
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultRightBorderColor()).isEqualTo(Color.RED);
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultTopBorderColor()).isEqualTo(Color.RED);
		expect(testUnit.getRefBaseLook().getRecursiveOrDefaultBottomBorderColor()).isEqualTo(Color.RED);
	}
	
	//method
	@TestCase
	public final void testCase_setBorderColorsOfBaseLook_whenTheGivenBorderColorIsNull() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution & verification
		expect(() -> testUnit.getRefBaseLook().setBorderColors(null))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given border color is null.");
	}
	
	//method
	@TestCase
	public final void testCase_setMaxWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setMaxWidth(500);
		
		//verification
		expect(testUnit.getMaxWidth()).isEqualTo(500);
	}
	
	//method
	@TestCase
	public final void testCase_setMaxWidth_whenTheGivenMaxWidthIsNegative() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution & verification
		expect(() -> testUnit.setMaxWidth(-100))
		.throwsException()
		.ofType(NonPositiveArgumentException.class)
		.withMessage("The given max width '-100' is not positive.");
	}
	
	//method
	@TestCase
	public final void testCase_setMinWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setMinWidth(500);
		
		//verification
		expect(testUnit.getMinWidth()).isEqualTo(500);
	}
}
