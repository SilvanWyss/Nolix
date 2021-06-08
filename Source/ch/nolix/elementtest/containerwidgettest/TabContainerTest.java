//package declaration
package ch.nolix.elementtest.containerwidgettest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.containerwidget.TabContainer;
import ch.nolix.element.gui.containerwidget.TabContainerTab;
import ch.nolix.element.gui.widget.Area;
import ch.nolix.element.gui.widget.Widget;

//class
public final class TabContainerTest extends ContainerWidgetTest<TabContainer> {
	
	//method
	@TestCase
	public void testCase_getContentAreaWidth() {
		
		//setup
		final var testUnit = new TabContainer();
		testUnit.addTab(
			new TabContainerTab()
			.setWidget(
				new Area()
				.setWidth(500)
			)
		);
		testUnit.recalculate();
		
		//execution
		final var result = testUnit.getContentArea().getWidth();
		
		//verification
		expect(result).isEqualTo(500);
	}
	
	//method
	@TestCase
	public void testCase_selectTab() {
		
		//setup
		final var tab1 = new TabContainerTab().setHeader("Tab1");
		final var tab2 = new TabContainerTab().setHeader("Tab2");
		final var tab3 = new TabContainerTab().setHeader("Tab3");
		final var tab4 = new TabContainerTab().setHeader("Tab4");
		final var testUnit =
		new TabContainer()
		.addTab(tab1, tab2, tab3, tab4);
		
		//setup verification
		expect(tab1.isSelected());
		expectNot(tab2.isSelected());
		
		//execution
		testUnit.selectTab("Tab2");
		
		//verification
		expect(tab2.isSelected());
		expectNot(tab1.isSelected());
	}
	
	//method
	@Override
	protected void addWidgetToContainerWidget(final TabContainer tabContainer, final Widget<?, ?> widget) {
		tabContainer.addTab("Tab", widget);
	}
	
	//method
	@Override
	protected TabContainer createTestUnit() {
		return new TabContainer();
	}
}
