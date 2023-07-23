//package declaration
package ch.nolix.systemtest.webguitest.controlcomponenttest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlCssBuilderTest<
	CCB extends IControlCssBuilder<C, ?>,
	C extends IControl<C, ?>
>
extends Test {
	
	//method
	@TestCase
	public final void testCase_whenGivenControlIsNew() {
		
		//setup
		final var control = createControl();
		final var controlInternalId = control.getInternalId();
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.createCssRulesForControl(control);
		
		//verification part 1
		expect(result).hasElementCount(4);
		final var cssRuleForAllStates =
		result.getStoredFirst(r -> r.getSelectors().containsEqualing("#" + controlInternalId));
		expect(cssRuleForAllStates.getStoredProperties().containsAny(p -> p.hasName("cursor")));
		
		//verification part 2
		expect(result.containsAny(r -> r.getSelectors().containsEqualing("#" + controlInternalId + ":hover")));
		
		//verification part 3
		expect(result.containsAny(r -> r.getSelectors().containsEqualing("#" + controlInternalId + ":focus")));
	}
	
	//method declaration
	protected abstract C createControl();
	
	//method declaration
	protected abstract CCB createTestUnit();
}
