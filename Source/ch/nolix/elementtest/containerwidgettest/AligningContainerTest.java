//package declaration
package ch.nolix.elementtest.containerwidgettest;

import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.AligningContainer;

//class
public final class AligningContainerTest extends ContainerWidgetTest<AligningContainer> {
	
	//method
	@Override
	protected void addWidgetToContainerWidget(final AligningContainer aligningContainer, final Widget<?, ?> widget) {
		aligningContainer.setOnTopLeft(widget);
	}
	
	//method
	@Override
	protected AligningContainer createTestUnit() {
		return new AligningContainer();
	}
}
