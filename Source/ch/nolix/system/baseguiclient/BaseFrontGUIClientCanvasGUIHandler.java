//package declaration
package ch.nolix.system.baseguiclient;

import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.GUI.CanvasFrame;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.GUI;

//class
final class BaseFrontGUIClientCanvasGUIHandler implements IFrontGUIClientGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public BaseFrontGUIClientCanvasGUIHandler(final BaseFrontGUIClient<?> parentFrontGuiClientoid) {
		
		mGUI =
		new CanvasFrame(
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
			case CommandProtocol.SET_TITLE:
				mGUI.setTitle(pGUICommand.getOneAttributeAsString());
				mGUI.refresh();
				break;
			case CommandProtocol.SET_CURSOR_ICON:
				mGUI.setCursorIcon(CursorIcon.fromSpecification(pGUICommand.getOneAttributeAsNode()));
				mGUI.refresh();
				break;
			case CommandProtocol.SET_PAINT_COMMANDS:
				setPaintCommands(pGUICommand.getAttributes());
				mGUI.refresh();
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
	private void setPaintCommands(final IContainer<ChainedNode> paintCommands) {
		mGUI.setPaintCommandsFromChainedNodes(paintCommands);
	}
}
