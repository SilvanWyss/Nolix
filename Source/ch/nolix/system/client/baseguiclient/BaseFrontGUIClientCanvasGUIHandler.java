//package declaration
package ch.nolix.system.client.baseguiclient;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.element.gui.base.CanvasFrame;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.GUI;
import ch.nolix.element.gui.image.Image;

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
		return BaseFrontGUIClientGUIType.CANVAS_GUI;
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
			case CommandProtocol.SET_ICON:
				mGUI.setIcon(Image.fromSpecification(pGUICommand.getOneAttributeAsNode()));
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
