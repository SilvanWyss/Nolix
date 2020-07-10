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
	private Frame mGUI;
	
	//constructor
	public BaseFrontGUIClientLayerGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		
		mGUI =
		new Frame(
			new BaseFrontGUIClientInputTaker(
				parentFrontGuiClientoid::noteInputOnCounterpart,
				this::getCursorXPositionOnViewArea,
				this::getCursorYPositionOnViewArea
			)
		);
		
		parentFrontGuiClientoid.createCloseDependencyTo(mGUI);
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
	public void runGUICommand(final ChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case CommandProtocol.RESET:
				resetGUI(pGUICommand.getAttributesAsNodes());
				break;
			default:
				throw new InvalidArgumentException("GUI command", pGUICommand, "is not valid");
		}
	}
	
	//method
	private int getCursorXPositionOnViewArea() {
		return mGUI.getViewAreaCursorXPosition();
	}
	
	//method
	private int getCursorYPositionOnViewArea() {
		return mGUI.getViewAreaCursorYPosition();
	}
	
	//method
	private void resetGUI(final Iterable<? extends BaseNode> attributes) {
		mGUI.reset(attributes);
		mGUI.updateFromConfiguration();
	}
}
