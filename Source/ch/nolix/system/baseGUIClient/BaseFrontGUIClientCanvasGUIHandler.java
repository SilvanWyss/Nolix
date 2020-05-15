//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.element.GUI.CanvasFrame;
import ch.nolix.element.GUI.GUI;

//class
final class BaseFrontGUIClientCanvasGUIHandler implements IFrontGUIClientGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public BaseFrontGUIClientCanvasGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		mGUI = new CanvasFrame(new BaseFrontGUIClientInputTaker(parentFrontGuiClientoid));
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
	public GUI<?> getRefGUI() {
		return mGUI;
	}
	
	//method
	@Override
	public void runGUICommand(ChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case Protocol.SET_TITLE_HEADER:
				mGUI.setTitle(pGUICommand.getOneAttributeAsString());
				mGUI.refresh();
				break;
			case Protocol.SET_PAINT_COMMANDS_HEADER:
				setPaintCommands(pGUICommand.getAttributes());
				mGUI.refresh();
				break;
			default:
				throw new InvalidArgumentException("GUI command", pGUICommand, "is not valid");
		}
	}
	
	//method
	private void setPaintCommands(final IContainer<ChainedNode> paintCommands) {
		mGUI.setPaintCommandsFromChainedNodes(paintCommands);
	}
}
