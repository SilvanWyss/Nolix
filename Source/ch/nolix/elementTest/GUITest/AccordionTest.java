//package declaration
package ch.nolix.elementTest.GUITest;

//own imports
import ch.nolix.element.GUI.Accordion;
import ch.nolix.element.GUI.AccordionExpansionBehavior;
import ch.nolix.element.GUI.AccordionTab;

//test class
public final class AccordionTest extends WidgetTest {
	
	//test case
	public void testCase_addTab() {
		
		//setup
			final var accordion =
			new Accordion()
			.setExpansionBehavior(AccordionExpansionBehavior.Single);
			
			final var tab1 = new AccordionTab();
		
		//execution
		accordion.addTab(tab1);
		
		//verification
		expect(tab1.isExpanded());
	}
	
	//test case
	public void testCase_clear() {
		
		//setup
		final var accordion =
		new Accordion(
			new AccordionTab(),
			new AccordionTab(),
			new AccordionTab(),
			new AccordionTab()
		);
		
		//execution
		accordion.clear();
		
		//verification
		expect(accordion.isEmpty());
	}
	
	//test case
	public void testCase_expandTab() {
		
		//setup
			final var accordion =
			new Accordion()
			.setExpansionBehavior(AccordionExpansionBehavior.SingleOrNone);
			
			final var tab1 = new AccordionTab();
			final var tab2 = new AccordionTab();
			final var tab3 = new AccordionTab();
			final var tab4 = new AccordionTab();
			accordion.addTab(tab1, tab2, tab3, tab4);
		
		//execution
		tab2.expand();
		
		//verification
		expectNot(tab1.isExpanded());
		expect(tab2.isExpanded());
		expectNot(tab3.isExpanded());
		expectNot(tab4.isExpanded());
	}

	//test case
	public void testCase_getType() {
		
		//setup
		final var accordion = new Accordion();
		
		//execution & verification
		expect(accordion.getType()).isEqualTo("Accordion");
	}

	//method
	protected Accordion createTestObject() {
		return new Accordion();
	}
}
