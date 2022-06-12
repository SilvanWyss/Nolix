//package declaration
package ch.nolix.systemtest.guitest.containerwidgettest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.system.gui.containerwidget.Accordion;
import ch.nolix.system.gui.containerwidget.AccordionExpansionBehavior;
import ch.nolix.system.gui.containerwidget.AccordionTab;
import ch.nolix.system.gui.widget.Widget;

//class
public final class AccordionTest extends ContainerWidgetTest<Accordion> {
	
	//method
	@TestCase
	public void testCase_addTab() {
		
		//setup
			final var accordion =
			new Accordion()
			.setExpansionBehavior(AccordionExpansionBehavior.OPEN_ONE_TAB);
			
			final var tab1 = new AccordionTab();
		
		//execution
		accordion.addTab(tab1);
		
		//verification
		expect(tab1.isExpanded());
	}
	
	//method
	@TestCase
	public void testCase_clear() {
		
		//setup
		final var accordion =
		new Accordion()
		.addTab(
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
	
	//method
	@TestCase
	public void testCase_expandTab() {
		
		//setup
			final var accordion =
			new Accordion()
			.setExpansionBehavior(AccordionExpansionBehavior.OPEN_ONE_TAB_OR_NONE);
			
			final var tab1 = new AccordionTab();
			final var tab2 = new AccordionTab();
			final var tab3 = new AccordionTab();
			final var tab4 = new AccordionTab();
			accordion.addTab(tab1, tab2, tab3, tab4);
		
		//execution
		accordion.expandTab(tab2);
		
		//verification
		expectNot(tab1.isExpanded());
		expect(tab2.isExpanded());
		expectNot(tab3.isExpanded());
		expectNot(tab4.isExpanded());
	}
	
	//method
	@TestCase
	public void testCase_getType() {
		
		//setup
		final var accordion = new Accordion();
		
		//execution & verification
		expect(accordion.getType()).isEqualTo("Accordion");
	}
	
	//method
	@Override
	protected void addWidgetToContainerWidget(final Accordion accordion, final Widget<?, ?> widget) {
		accordion.addTab("Tab", widget);
	}
	
	//method
	@Override
	protected Accordion createTestUnit() {
		return new Accordion();
	}
}
