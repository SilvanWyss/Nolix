//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.containers.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.element.painter.IPainter;

//package-visible class
public final class PaintRun {
	
	//multi-attribute
	private List<IPainter> painters = new List<>();
	
	//constructor
	public PaintRun(
		final IPainter painter,
		final List<IElementTaker<PaintRun>> painterCommands
	) {
		addPainter(painter);
		painterCommands.forEach(pc -> pc.run(this));
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
