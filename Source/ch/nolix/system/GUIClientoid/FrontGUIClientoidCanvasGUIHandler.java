//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.invalidArgumentException.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.GUI.CanvasFrame;

//package-visible class
final class FrontGUIClientoidCanvasGUIHandler extends FrontGUIClientoidGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public FrontGUIClientoidCanvasGUIHandler(final FrontGUIClientoid<?> parentFrontGuiClientoid) {
		mGUI = new CanvasFrame(new FrontGUIClientoidEventTaker(parentFrontGuiClientoid));;
	}
	
	//method
	@Override
	public boolean canRunCommandOfType(String command) {
		switch (command) {
			case Protocol.SET_PAINT_COMMANDS_HEADER:
				return true;
			default:
				return false;
		}
	}
	
	//method
	@Override
	public void noteClose() {
		mGUI.close();
	}
	
	//method
	@Override
	public FrontGUIClientoidGUIType getGUIType() {
		return FrontGUIClientoidGUIType.CanvasGUI;
	}
	
	//method
	@Override
	public String getUpdateCommandForCounterpart() {
		throw new ArgumentDoesNotSupportMethodException(this, "getUpdateCommandForCounterpart");
	}
	
	//method
	@Override
	public void run(Statement command) {
		switch (command.getHeader()) {
			case Protocol.SET_PAINT_COMMANDS_HEADER:
				setPainterCommands(
					command
					.getRefAttributes()
					.to(a -> Statement.fromString(DocumentNode.createOriginStringFromReproducingString(a.getHeader())))
				);
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	@Override
	public boolean providesUpdateCommandForCounterpart() {
		return false;
	}
	
	//method
	private void setPainterCommands(final IContainer<Statement> painterCommands) {
		//TODO
	}
}
