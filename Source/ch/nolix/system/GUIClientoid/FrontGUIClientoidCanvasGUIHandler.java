//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.chainedNode.ChainedNode;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.invalidArgumentExceptions.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.Node;
import ch.nolix.element.GUI.CanvasFrame;

//package-visible class
final class FrontGUIClientoidCanvasGUIHandler extends FrontGUIClientoidGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public FrontGUIClientoidCanvasGUIHandler(final FrontGUIClientoid<?> parentFrontGuiClientoid) {
		mGUI = new CanvasFrame(new FrontGUIClientoidEventTaker(parentFrontGuiClientoid));
	}
	
	//method
	@Override
	public boolean canRunCommandOfType(String command) {
		switch (command) {
			case Protocol.GUI_HEADER:
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
	public void run(ChainedNode command) {
		switch (command.getHeader()) {
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextNode());
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
	private void runGUICommand(ChainedNode GUICommand) {
		switch (GUICommand.getHeader()) {
			case Protocol.SET_TITLE_HEADER:
				mGUI.setTitle(GUICommand.getOneAttributeAsString());
				break;
			case Protocol.SET_PAINT_COMMANDS_HEADER:
				setPaintCommands(
					GUICommand
					.getRefAttributes()
					.to(a -> ChainedNode.fromString(Node.createOriginStringFromReproducingString(a.getHeader())))
				);
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}

	//method
	private void setPaintCommands(final IContainer<ChainedNode> paintCommands) {
		mGUI.setPaintCommandsFromStatements(paintCommands);
	}
}
