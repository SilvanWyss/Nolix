//package declaration
package ch.nolix.system.client.baseguiclient;

import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.GUI;

//class
final class BaseFrontGUIClientWidgetGUIHandler implements IFrontGUIClientGUIHandler {
	
	//attribute
	private Frame mGUI;
	
	//constructor
	public BaseFrontGUIClientWidgetGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		
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
		return BaseFrontGUIClientGUIType.WIDGET_GUI;
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
		return mGUI.getCursorXPositionOnViewArea();
	}
	
	//method
	private int getCursorYPositionOnViewArea() {
		return mGUI.getCursorYPositionOnViewArea();
	}
	
	//method
	private void resetGUI(final Iterable<? extends BaseNode> attributes) {
		mGUI.resetFrom(attributes);
		mGUI.updateFromConfiguration();
	}
}
