//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;

//class
final class CanvasGUIPainterPool {
	
	//attribute
	private int nextIndex = 1;
	
	//multi-attribute
	private final LinkedList<ChainedNode> paintCommands = new LinkedList<>();
	
	//method
	public void appendPaintCommand(final CanvasGUICommandCreatorPainter browserGUIPainter,	final String command) {
		paintCommands.addAtEnd(
			ChainedNode.fromString(
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
	public IContainer<ChainedNode> getPaintCommands() {
		return paintCommands;
	}
	
	//method
	public int getNextIndexAndUpdateNextIndex() {
		nextIndex++;
		return (nextIndex - 1);
	}
}
