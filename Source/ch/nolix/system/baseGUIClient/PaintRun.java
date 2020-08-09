//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.element.painter.IPainter;

//class
public final class PaintRun {
	
	//multi-attribute
	private LinkedList<IPainter> painters = new LinkedList<>();
	
	//constructor
	public PaintRun(final IPainter painter,	final IContainer<IElementTaker<PaintRun>> paintCommands) {
		addPainter(painter);
		paintCommands.forEach(pc -> pc.run(this));
	}
	
	//method
	public void addPainter(final IPainter painter) {
		painters.addAtEnd(painter);
	}
	
	//method
	public IPainter getRefPainterByIndex(final int index) {
		return painters.getRefAt(index);
	}
}
