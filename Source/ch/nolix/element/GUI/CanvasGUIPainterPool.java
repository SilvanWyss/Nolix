//package declaration
package ch.nolix.element.GUI;

import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.statement.Statement;

//package-visible class
final class CanvasGUIPainterPool {
	
	//attribute
	private int nextIndex = 1;
	
	//multi-attribute
	private final List<Statement> paintCommands = new List<>();
	
	//method
	public void appendPaintCommand(final CanvasGUICommandCreatorPainter browserGUIPainter,	final String command) {
		paintCommands.addAtEnd(
			Statement.fromString(
				CanvasGUIProtocol.PAINTER_BY_INDEX_HEADER
				+ '('
				+ browserGUIPainter.getIndex()
				+ ')'
				+ '.'
				+ command
			)
		);
	}
	
	//method
	public IContainer<Statement> getPaintCommands() {
		return paintCommands;
	}
	
	//method
	public int getNextIndexAndUpdateNextIndex() {
		nextIndex++;
		return (nextIndex - 1);
	}
}
