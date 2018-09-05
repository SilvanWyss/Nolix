//package declaration
package ch.nolix.system.GUIClient;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.util.Package;

//package-visible class
final class BrowserGUIPainterBottom {
	
	//attribute
	private int nextIndex = 1;
	
	//multi-attribute
	private final List<Package<String>> painterCommands = new List<Package<String>>();
	
	//method
	public void appendPainterCommand(
		final BrowserGUIPainter browserGUIPainter,
		final String command
	) {
		painterCommands.addAtEnd(new Package<String>(browserGUIPainter.getIndex(), command));
	}
	
	//method
	public int getNextIndexAndUpdateNextIndex() {
		nextIndex++;
		return (nextIndex - 1);
	}

	//method
	public void runPainterCommands() {
		//TODO
	}
}
