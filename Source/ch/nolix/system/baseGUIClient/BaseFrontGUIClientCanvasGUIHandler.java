//package declaration
package ch.nolix.system.baseGUIClient;

import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotSupportMethodException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.Node;
import ch.nolix.element.GUI.CanvasFrame;

//package-visible class
final class BaseFrontGUIClientCanvasGUIHandler extends BaseFrontGUIClientGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public BaseFrontGUIClientCanvasGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		mGUI = new CanvasFrame(new BaseFrontGUIClientEventTaker(parentFrontGuiClientoid));
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
	public BaseFrontGUIClientGUIType getGUIType() {
		return BaseFrontGUIClientGUIType.CanvasGUI;
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
	
	//TODO
	@Override
	String getSetViewAreaSizeCommand() {
			return "SetViewAreaSize(" + mGUI.getViewAreaWidth()  + "," + mGUI.getViewAreaHeight() + ")";
	}
}
