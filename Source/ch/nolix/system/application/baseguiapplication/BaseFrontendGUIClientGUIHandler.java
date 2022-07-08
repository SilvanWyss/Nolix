//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.gui.image.Image;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.main.CanvasFrame;
import ch.nolix.system.gui.main.GUI;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;

//class
final class BaseFrontendGUIClientGUIHandler {
	
	//attribute
	private final CanvasFrame mGUI;
	
	//constructor
	public BaseFrontendGUIClientGUIHandler(final BaseFrontendGUIClient<?> parentFrontGuiClientoid) {
		
		mGUI =
		new CanvasFrame(
			new BaseFrontendGUIClientGUIInputTaker(
				parentFrontGuiClientoid::noteInputOnCounterpart,
				this::getCursorXPositionOnViewArea,
				this::getCursorYPositionOnViewArea
			)
		);
		
		parentFrontGuiClientoid.createCloseDependencyTo(mGUI);
	}
	
	//method
	public GUI<?> getRefGUI() {
		return mGUI;
	}
	
	//method
	public void runGUICommand(ChainedNode pGUICommand) {
		switch (pGUICommand.getHeader()) {
			case CommandProtocol.REGISTER_IMAGE:
				
				final var id = pGUICommand.getChildNodeAt1BasedIndex(1).getHeader();
				final var image = MutableImage.fromSpecification(pGUICommand.getChildNodeAt1BasedIndex(2).toNode());
				
				mGUI.getRefImageCache().registerAtId(id, image);
				
				break;
			case CommandProtocol.SET_TITLE:
				mGUI.setTitle(pGUICommand.getSingleChildNodeAsString());
				mGUI.refresh();
				break;
			case CommandProtocol.SET_ICON:
				mGUI.setIcon(Image.fromSpecification(pGUICommand.getSingleChildNodeAsNode()));
				mGUI.refresh();
				break;
			case CommandProtocol.SET_CURSOR_ICON:
				mGUI.setCursorIcon(CursorIcon.fromSpecification(pGUICommand.getSingleChildNodeAsNode()));
				mGUI.refresh();
				break;
			case CommandProtocol.SET_PAINT_COMMANDS:
				setPaintCommands(pGUICommand.getChildNodes());
				mGUI.refresh();
				break;
			default:
				throw InvalidArgumentException.forArgumentNameAndArgument("GUI command", pGUICommand);
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
