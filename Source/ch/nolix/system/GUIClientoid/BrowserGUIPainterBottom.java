//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.Statement;
import ch.nolix.core.validator.Validator;

//package-visible class
final class BrowserGUIPainterBottom {
	
	//attribute
	private final BackGUIClientoid<?> parentBackGUIClient;
	private int nextIndex = 1;
	
	//multi-attribute
	private final List<Statement> painterCommands = new List<Statement>();
	
	//constructor
	public BrowserGUIPainterBottom(final BackGUIClientoid<?> parentBackGUIClient) {
		
		Validator.suppose(parentBackGUIClient).isOfType(BackGUIClientoid.class);
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	public void appendPainterCommand(
		final BackBrowserGUIClientoidPainter browserGUIPainter,
		final String command
	) {
		painterCommands.addAtEnd(
			Statement.fromString(
				Protocol.PAINTER_BY_INDEX_HEADER
				+ '('
				+ browserGUIPainter.getIndex()
				+ ')'
				+ '.'
				+ command
			)
		);
	}
	
	//method
	public int getNextIndexAndUpdateNextIndex() {
		nextIndex++;
		return (nextIndex - 1);
	}

	//method
	public void paintOnCounterpart() {
		
		parentBackGUIClient.paintOnCounterpart(painterCommands);
		
		painterCommands.clear();
	}
}
