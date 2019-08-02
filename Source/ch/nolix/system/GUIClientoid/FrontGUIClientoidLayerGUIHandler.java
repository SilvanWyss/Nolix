//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.statement.Statement;
import ch.nolix.element.GUI.LayerFrame;

//package-visible class
final class FrontGUIClientoidLayerGUIHandler extends FrontGUIClientoidGUIHandler {
	
	//attribute
	private final LayerFrame mGUI;
	
	//constructor
	public FrontGUIClientoidLayerGUIHandler(final FrontGUIClientoid<?> parentFrontGuiClientoid) {
		mGUI = new LayerFrame(new FrontGUIClientoidEventTaker(parentFrontGuiClientoid));;
	}
	
	//method
	@Override
	public boolean canRunCommandOfType(final String command) {
		switch (command) {
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
	public void run(final Statement command) {
		switch (command.getHeader()) {
			case Protocol.GUI_HEADER:
				runGUICommand(command.getRefNextStatement());
				break;
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.COMMAND, command, "is not valid");
		}
	}
	
	//method
	private void resetGUI(final Iterable<? extends DocumentNodeoid> attributes) {
		mGUI.reset(attributes);
		mGUI.updateFromConfiguration();
	}
	
	//method
	private void runGUICommand(final Statement GUICommand) {
		switch (GUICommand.getHeader()) {
			case Protocol.RESET_HEADER:
				resetGUI(GUICommand.getRefAttributes());
				break;
			default:
				throw new InvalidArgumentException("GUI command", GUICommand, "is not valid");
		}
	}
}
