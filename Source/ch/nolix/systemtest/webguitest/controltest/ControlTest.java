//package declaration
package ch.nolix.systemtest.webguitest.controltest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.systemapi.guiapi.structureproperty.CursorIcon;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlTest<C extends IControl<C, ?>> extends Test {
	
	//method
	@TestCase
	public final void testCase_getFixedId() {
		
		//setup
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.getFixedId();
		
		//verification
		expect(result).startsWith("i");
		expect(result).hasLength(11);
	}
	
	//method
	@TestCase
	public final void testCase_getFixedId_whenMethodIsCalledSeveralTimes() {
		
		//setup
		final var testUnit = createTestUnit();
		final var fixedId = testUnit.getFixedId();
		
		for (var i = 1; i <= 10_000; i++) {
			
			//execution
			final var result = testUnit.getFixedId();
			
			//verification
			expect(result).isEqualTo(fixedId);
		}
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
		expect(testUnit.getCursorIcon()).isSameAs(CursorIcon.ARROW);
	}
	
	//method declaration
	protected abstract C createTestUnit();
}
