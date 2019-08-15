//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.element.painter.IPainter;

//class
public final class PaintRun {
	
	//multi-attribute
	private List<IPainter> painters = new List<>();
	
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
