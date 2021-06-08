//package declaration
package ch.nolix.elementtest.containerwidgettest;

import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.element.gui.containerwidget.ContainerWidget;
import ch.nolix.element.gui.widget.Area;
import ch.nolix.element.gui.widget.Widget;
import ch.nolix.elementtest.widgettest.BorderWidgetTest;

//class
public abstract class ContainerWidgetTest<CW extends ContainerWidget<CW, ?>> extends BorderWidgetTest<CW> {
	
	//method
	@TestCase
	public final void testCase_clear_whenContainsWidget() {
		
		//setup
		final var testUnit = createTestUnit();
		addWidgetToContainerWidget(testUnit, new Area());
		
		//setup verification
		expect(testUnit.containsAny());
		
		//execution
		testUnit.clear();
		
		//verification
		expect(testUnit.isEmpty());
	}
	
	//method
	@TestCase
	public final void testCase_getChildWidgets_whenContainsWidget() {
		
		//setup
		final var testUnit = createTestUnit();
		addWidgetToContainerWidget(testUnit, new Area());
		
		//setup verification
		expect(testUnit.containsAny());
		
		//execution
		final var result = testUnit.getChildWidgets();
		
		//verification
		expect(result).isNotEmpty();
	}
	
	//method
	@TestCase
	public final void testCase_getChildWidgets_whenIsEmpty() {
		
		//setup
		final var testUnit = createTestUnit();
		testUnit.clear();
		
		//setup verification
		expect(testUnit.isEmpty());
		
		//execution
		final var result = testUnit.getChildWidgets();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method declaration
	protected abstract void addWidgetToContainerWidget(CW containerWidget, Widget<?, ?> widget);
}
