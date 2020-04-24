//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.GUI;

//class
final class BaseFrontGUIClientLayerGUIHandler implements IFrontGUIClientGUIHandler {
	
	//attribute
	private final Frame mGUI;
	
	//constructor
	public BaseFrontGUIClientLayerGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		mGUI = new Frame(new BaseFrontGUIClientEventTaker(parentFrontGuiClientoid));
	}
	
	//method
	@Override
	public BaseFrontGUIClientGUIType getGUIType() {
		return BaseFrontGUIClientGUIType.LayerGUI;
	}
	
	//method
	@Override
	public GUI<?> getRefGUI() {
		return mGUI;
	}
	
	//method
	@Override
	public void noteClose() {
		mGUI.close();
	}
		
	//method
	@Override
	public void runGUICommand(final ChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case Protocol.SET_TITLE_HEADER:
				mGUI.setTitle(pGUICommand.getOneAttributeAsString());
				mGUI.refresh();
				break;
			case Protocol.RESET_HEADER:
				resetGUI(pGUICommand.getAttributesAsNodes());
				break;
			default:
				throw new InvalidArgumentException("GUI command", pGUICommand, "is not valid");
		}
	}
	
	//method
	private void resetGUI(final Iterable<? extends BaseNode> attributes) {
		mGUI.reset(attributes);
		mGUI.updateFromConfiguration();
	}
}
