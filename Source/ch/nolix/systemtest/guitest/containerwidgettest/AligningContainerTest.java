//package declaration
package ch.nolix.systemtest.guitest.containerwidgettest;

//own imports
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.widget.Widget;

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
