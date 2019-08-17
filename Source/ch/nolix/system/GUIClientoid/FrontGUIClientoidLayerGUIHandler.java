//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.chainedNode.ChainedNode;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.BaseNode;
import ch.nolix.element.GUI.Frame;

//package-visible class
final class FrontGUIClientoidLayerGUIHandler extends FrontGUIClientoidGUIHandler {
	
	//attribute
	private final Frame mGUI;
	
	//constructor
	public FrontGUIClientoidLayerGUIHandler(final FrontGUIClientoid<?> parentFrontGuiClientoid) {
		mGUI = new Frame(new FrontGUIClientoidEventTaker(parentFrontGuiClientoid));;
	}
	
	//method
	@Override
	public boolean canRunCommandOfType(final String command) {
		switch (command) {
			case "GUI.SetTitle":
			case Protocol.GUI_HEADER:
				return true;
			default:
				return false;
			}
	}
	
	//method
	@Override
	public FrontGUIClientoidGUIType getGUIType() {
		return FrontGUIClientoidGUIType.LayerGUI;
	}
	
	//method
	@Override
	public String getUpdateCommandForCounterpart() {
		return
		Protocol.GUI_HEADER
		+ '.'
		+ Protocol.ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER
		+ '('
		+ mGUI.getInteractionAttributesOfWidgets().to(ias -> '(' + ias.toString() + ')')
		+ ')';
	}

	//method
	@Override
	public void noteClose() {
		mGUI.close();
	}
	
	//method
	@Override
	public boolean providesUpdateCommandForCounterpart() {
		return true;
	}

	//method
	@Override
	public void run(final ChainedNode command) {
		switch (command.getHeader()) {
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextNode());
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//TODO
	@Override
	protected void noteResize() {
		mGUI.noteResize(mGUI.getWidth(), mGUI.getHeight());
		
	}

	//method
	private void resetGUI(final Iterable<? extends BaseNode> attributes) {
		mGUI.reset(attributes);
		mGUI.updateFromConfiguration();
	}
	
	//method
	private void runGUICommand(final ChainedNode GUICommand) {
		switch (GUICommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			case "SetTitle":
				mGUI.setTitle(GUICommand.getOneAttributeAsString());
				mGUI.refresh();
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}
}
