//package declaration
package ch.nolix.systemtest.guitest.widgettest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.containerwidget.SingleContainer;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.WidgetLookState;

//class
/**
 * A {@link BorderWidgetTest} is a test for {@link BorderWidget}s.
 * 
 * @author Silvan Wyss
 * @date 2018-12-11
 * @param <BW> is the type of the {@link BorderWidget}s of a {@link BorderWidgetTest}.
 */
public abstract class BorderWidgetTest<BW extends BorderWidget<BW, ?>> extends WidgetTest<BW> {
	
	//method
	@TestCase
	public void testCase_containsPointRelatively_whenBottomLeftPixelIsGiven() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.recalculate();
		
		final var result = testUnit.containsPointRelatively(0, testUnit.getHeight() - 1);
		
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_containsPointRelatively_whenBottomRightPixelIsGiven() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.recalculate();
		
		final var result = testUnit.containsPointRelatively(testUnit.getWidth() - 1, testUnit.getHeight() - 1);
		
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_containsPointRelatively_whenTopLeftPixelIsGiven() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.recalculate();
		
		final var result = testUnit.containsPointRelatively(0, 0);
		
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_containsPointRelatively_whenTopRightPixelIsGiven() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.recalculate();
		
		final var result = testUnit.containsPointRelatively(testUnit.getWidth() - 1, 1);
		
		expect(result);
	}
	
	//method
	@TestCase
	public final void testCase_getHeight_whenHasTopBorder() {
		
		//parameter definition
		final var topBorderThickness = 5;
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.getRefActiveLook().setTopBorderThicknessForState(WidgetLookState.BASE, topBorderThickness);
		testUnit.getRefActiveLook().removeBottomBorderThicknesses();
		testUnit.recalculate();
		
		//execution
		final var result = testUnit.getHeight();
		
		//execution
		expect(result).isEqualTo(testUnit.getBorderedArea().getHeight() + topBorderThickness);
	}
	
	//method
	@TestCase
	public final void testCase_getHeight_whenHasMinHeightThatIsBiggerThanTheHeight() {
		
		//setup part 1
		final var testUnit = createTestUnit();
		testUnit.recalculate();
		
		//setup part 2
		final var targetMinHeight = testUnit.getHeight() + 100;
		testUnit.setMinHeight(targetMinHeight);
		testUnit.recalculate();
		
		//execution
		final var result = testUnit.getHeight();
		
		//verification
		expect(result).isEqualTo(targetMinHeight);
	}
	
	//method
	@TestCase
	public final void testCase_getHeight_whenHasProposalHeightThatIsBiggerThanTheHeight() {
		
		//setup part 1
		final var testUnit = createTestUnit();
		testUnit.recalculate();
		
		//setup part 2
		final var targetProposalHeight = testUnit.getHeight() + 100;
		testUnit.setProposalHeight(targetProposalHeight);
		testUnit.recalculate();
				
		//execution
		final var result = testUnit.getHeight();
		
		//verification
		expect(result).isEqualTo(targetProposalHeight);
	}
	
	//method
	@TestCase
	public final void testCase_getMaxHeight_whenHasSetMaxHeightInPercentOfGUIViewAreaHeightAndBelongsToGUI() {
		try (final var invisibleGUI = new InvisibleGUI()) {
		
			//setup
			final var testUnit = createTestUnit();
			testUnit.setMaxHeightInPercentOfGUIViewAreaHeight(0.9);
			invisibleGUI.pushLayerWithWidget(new SingleContainer().setWidget(testUnit));
			invisibleGUI.noteResize(1000, 800);
			
			//execution
			final var result = testUnit.getMaxHeight();
			
			//verification
			expect(result).isEqualTo(720);
		}
	}
	
	//method
	@TestCase
	public final void testCase_getMaxHeight_whenHasSetMaxHeightInPercentOfGUIViewAreaHeightAndDoesNotBelongToGUI() {
				
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMaxHeightInPercentOfGUIViewAreaHeight(0.9);
		
		//execution & verification
		expectRunning(testUnit::getMaxHeight)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public final void testCase_getMaxWidth_whenHasSetMaxWidthInPercentOfGUIViewAreaWidthAndBelongsToGUI() {
		try (final var invisibleGUI = new InvisibleGUI()) {
		
			//setup
			final var testUnit = createTestUnit();
			testUnit.setMaxWidthInPercentOfGUIViewAreaWidth(0.9);
			invisibleGUI.pushLayerWithWidget(new SingleContainer().setWidget(testUnit));
			invisibleGUI.noteResize(1000, 800);
			
			//execution
			final var result = testUnit.getMaxWidth();
			
			//verification
			expect(result).isEqualTo(900);
		}
	}
	
	//method
	@TestCase
	public final void testCase_getMaxWidth_whenHasSetMaxWidthInPercentOfGUIViewAreaWidthAndDoesNotBelongToGUI() {
				
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMaxWidthInPercentOfGUIViewAreaWidth(0.9);
		
		//execution & verification
		expectRunning(testUnit::getMaxWidth)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public final void testCase_getMinHeight_whenHasSetMinHeightInPercentOfGUIViewAreaHeightAndBelongsToGUI() {
		try (final var invisibleGUI = new InvisibleGUI()) {
		
			//setup
			final var testUnit = createTestUnit();
			testUnit.setMinHeightInPercentOfGUIViewAreaHeight(0.9);
			invisibleGUI.pushLayerWithWidget(testUnit);
			invisibleGUI.noteResize(1000, 800);
			
			//execution
			final var result = testUnit.getMinHeight();
			
			//verification
			expect(result).isEqualTo(720);
		}
	}
	
	//method
	@TestCase
	public final void testCase_getMinHeight_whenHasSetMinHeightInPercentOfGUIViewAreaHeightAndDoesNotBelongToGUI() {
				
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinHeightInPercentOfGUIViewAreaHeight(0.9);
		
		//execution & verification
		expectRunning(testUnit::getMinHeight)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}

	//method
	@TestCase
	public final void testCase_getMinWidth_whenHasSetMinWidthInPercentOfGUIViewAreaWidthAndBelongsToGUI() {
		try (final var invisibleGUI = new InvisibleGUI()) {
		
			//setup
			final var testUnit = createTestUnit();
			testUnit.setMinWidthInPercentOfGUIViewAreaWidth(0.9);
			invisibleGUI.pushLayerWithWidget(testUnit);
			invisibleGUI.noteResize(1000, 800);
			
			//execution
			final var result = testUnit.getMinWidth();
			
			//verification
			expect(result).isEqualTo(900);
		}
	}
	
	//method
	@TestCase
	public final void testCase_getMinWidth_whenHasSetMinWidthInPercentOfGUIViewAreaWidthAndDoesNotBelongToGUI() {
				
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidthInPercentOfGUIViewAreaWidth(0.9);
		
		//execution & verification
		expectRunning(testUnit::getMinWidth)
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public final void testCase_getWidth_whenHasLeftBorder() {
		
		//parameter definition
		final var leftBorderThickenss = 5;
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.getRefActiveLook().setLeftBorderThicknessForState(WidgetLookState.BASE, leftBorderThickenss);
		testUnit.getRefActiveLook().removeRightBorderThicknesses();
		testUnit.recalculate();
		
		//execution
		final var result = testUnit.getWidth();
		
		//verification
		expect(result).isEqualTo(testUnit.getBorderedArea().getWidth() + leftBorderThickenss);
	}
	
	//method
	@TestCase
	public final void testCase_getWidth_whenHasMinWidthThatIsBiggerThanTheWidth() {
		
		//setup part 1
		final var testUnit = createTestUnit();
		testUnit.recalculate();
				
		//setup part 2
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
	public final void testCase_getWidth_whenHasProposalWidthThatIsBiggerThanTheWidth() {
		
		//setup part 1
		final var testUnit = createTestUnit();
		testUnit.recalculate();
		
		//setup part 2
		final var targetProposalWidth = testUnit.getWidth() + 100;
		testUnit.setProposalWidth(targetProposalWidth);
		testUnit.recalculate();
				
		//execution
		final var result = testUnit.getWidth();
		
		//verification
		expect(result).isEqualTo(targetProposalWidth);
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
	public final void testCase_removeMinWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setMinWidth(500);
		
		//setup verification
		expect(testUnit.hasMinWidth());
		
		//execution
		testUnit.removeMinWidth();
		
		//verification
		expectNot(testUnit.hasMinWidth());
	}
	
	//method
	@TestCase
	public final void testCase_removeProposalWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setProposalWidth(500);
		
		//setup verification
		expect(testUnit.hasProposalWidth());
		
		//execution
		testUnit.removeProposalWidth();
		
		//verification
		expectNot(testUnit.hasProposalWidth());
	}
	
	//method
	@TestCase
	public final void testCase_resetBorderWidget() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setProposalWidth(200);
		testUnit.setProposalHeight(100);
		testUnit.setMinWidth(200);
		testUnit.setMinHeight(100);
		testUnit.setMaxWidth(200);
		testUnit.setMaxHeight(100);
		
		//setup verification
		expect(testUnit.hasMinWidth());
		expect(testUnit.hasMinHeight());
		expect(testUnit.hasMaxWidth());
		expect(testUnit.hasMaxHeight());
		expect(testUnit.hasProposalWidth());
		expect(testUnit.hasProposalHeight());
		
		//execution
		testUnit.reset();
		
		//verification
		expectNot(testUnit.hasMinWidth());
		expectNot(testUnit.hasMinHeight());
		expectNot(testUnit.hasMaxWidth());
		expectNot(testUnit.hasMaxHeight());
		expectNot(testUnit.hasProposalWidth());
		expectNot(testUnit.hasProposalHeight());
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
		expectRunning(() -> testUnit.setMaxWidth(-100))
		.throwsException()
		.ofType(InvalidArgumentException.class);
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
	
	//method
	@TestCase
	public final void testCase_setMinWidth_whenTheGivenMinWidthIsNegative() {

		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setMinWidth(-100);
		
		//verification
		expect(testUnit.getMinWidth()).isEqualTo(-100);
	}
	
	//method
	@TestCase
	public final void testCase_setProposalWidth() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		testUnit.setProposalWidth(500);
		
		//verification
		expect(testUnit.getProposalWidth()).isEqualTo(500);
	}
	
	//method
	@TestCase
	public final void testCase_setProposalWidth_whenTheGivenProposalWidthIsNegative() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution & verification
		expectRunning(() -> testUnit.setProposalWidth(-100))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
}
