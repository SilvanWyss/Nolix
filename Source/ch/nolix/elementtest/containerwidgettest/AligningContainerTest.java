//package declaration
package ch.nolix.elementtest.containerwidgettest;

//own imports
import ch.nolix.element.containerwidget.AligningContainer;
import ch.nolix.element.gui.Widget;

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
