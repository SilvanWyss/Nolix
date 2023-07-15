//package declaration
package ch.nolix.systemtest.webguitest.atomiccontroltest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.webapi.htmlapi.IHtmlElement;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public abstract class ControlHtmlBuilderTest<
	CHB extends IControlHtmlBuilder<C>,
	C extends IControl<C, ?>
>
extends Test{
	
	//method
	@TestCase
	public final void testCase_createHtmlElementForNewControl() {
		
		//setup
		final var control = createControl();
		final var testUnit = createTestUnit();
		
		//execution
		final var result = testUnit.createHtmlElementForControl(control);
		
		//verification part 1
		final var idAttribute = result.getStoredAttributes().getStoredOne(a -> a.hasName("id"));
		expect(idAttribute.getValue()).isEqualTo(control.getInternalId());
		
		//verification part 2
		expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(result);
	}
	
	//method declaration
	protected abstract C createControl();
	
	//method declaration
	protected abstract CHB createTestUnit();
	
	//method declaration
	protected abstract void expectSpecificPropertiesOnHtmlElementCreatedOfNewControl(IHtmlElement<?, ?> htmlElement);
}
