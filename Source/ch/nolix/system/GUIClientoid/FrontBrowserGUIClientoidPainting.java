//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.element.painter.IPainter;

//package-visible class
final class FrontBrowserGUIClientoidPainting {
	
	//multi-attribute
	private List<IPainter> painters = new List<IPainter>();
	
	//constructor
	public FrontBrowserGUIClientoidPainting(
		final IPainter painter,
		final List<IElementTaker<FrontBrowserGUIClientoidPainting>> painterCommands
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
