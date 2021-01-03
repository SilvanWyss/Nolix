//package declaration
package ch.nolix.elementtest.containerwidgettest;

//own imports
import ch.nolix.element.containerwidget.AligningContainer;
import ch.nolix.elementtest.widgettest.BorderWidgetTest;

//class
public final class AligningContainerTest extends BorderWidgetTest<AligningContainer> {
	
	//method
	@Override
	protected AligningContainer createTestUnit() {
		return new AligningContainer();
	}
}
