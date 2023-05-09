//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlTest<C extends IControl<C, ?>> extends Test {
	
	//method
	@TestCase
	public final void testCase_editStyle() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//setup verification
		final var defaultBaseTextColor = testUnit.getRefStyle().getTextColorWhenHasState(ControlState.BASE);
		expect(defaultBaseTextColor).isEqualTo(Color.BLACK);
		
		//execution
		testUnit.editStyle(s -> s.setTextColorForState(ControlState.BASE, Color.DARK_CYAN));
		
		//verification
		final var actualBaseTextColor = testUnit.getRefStyle().getTextColorWhenHasState(ControlState.BASE);
		expect(actualBaseTextColor).isEqualTo(Color.DARK_CYAN);
	}
	
	//method
	@TestCase
	public final void testCase_getFixedId() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.getInternalId();
		
		//verification
		expect(result).startsWith("i");
		expect(result).hasLength(11);
	}
	
	//method
	@TestCase
	public final void testCase_getFixedId_whenMethodIsCalledSeveralTimes() {
		
		//setup
		final var testUnit = createTestUnit();
		final var fixedId = testUnit.getInternalId();
		
		for (var i = 1; i <= 10_000; i++) {
			
			//execution
			final var result = testUnit.getInternalId();
			
			//verification
			expect(result).isEqualTo(fixedId);
		}
	}
	
	//method
	@TestCase
	public final void testCase_getRefChildControls() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.getRefChildControls();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public final void testCase_reset() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.setInvisible();
		testUnit.setMinWidth(1000);
		testUnit.setMinHeight(500);
		testUnit.setMaxWidth(1200);
		testUnit.setMaxHeight(600);
		testUnit.setCursorIcon(CursorIcon.HAND);
		
		//execution
		testUnit.reset();
		
		//verification
		expect(testUnit.isVisible());
		expectNot(testUnit.hasMinWidth());
		expectNot(testUnit.hasMinHeight());
		expectNot(testUnit.hasMaxWidth());
		expectNot(testUnit.hasMaxHeight());
		expect(testUnit.getCursorIcon()).is(CursorIcon.ARROW);
	}
	
	//method declaration
	@TestCase
	public final void testCase_setCursorIcon() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//setup verification
		expect(testUnit.getCursorIcon()).is(CursorIcon.ARROW);
		
		//execution
		testUnit.setCursorIcon(CursorIcon.HAND);
		
		//verification
		expect(testUnit.getCursorIcon()).is(CursorIcon.HAND);
	}
	
	//method declaration
	protected abstract C createTestUnit();
}
