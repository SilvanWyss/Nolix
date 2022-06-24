//package declaration
package ch.nolix.system.gui.main;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.coreapi.functionuniversalapi.I2ElementTaker;
import ch.nolix.systemapi.guiapi.imageapi.IImage;

//class
final class CanvasGUIPainterPool {
	
	//attribute
	private int nextIndex = 1;
	
	//attribute
	private final I2ElementTaker<String, IImage> imageRegistrator;
	
	//multi-attribute
	private final LinkedList<ChainedNode> paintCommands = new LinkedList<>();
	
	//constructor
	public CanvasGUIPainterPool(final I2ElementTaker<String, IImage> imageRegistrator) {
		
		GlobalValidator.assertThat(imageRegistrator).thatIsNamed("image registrator").isNotNull();
		
		this.imageRegistrator = imageRegistrator;
	}
	
	//method
	public void appendPaintCommand(final CanvasGUICommandCreatorPainter browserGUIPainter,	final String command) {
		paintCommands.addAtEnd(
			ChainedNode.fromString(
				CanvasGUIObjectProtocol.PAINTER_BY_INDEX
				+ '('
				+ browserGUIPainter.getIndex()
				+ ')'
				+ '.'
				+ command
			)
		);
	}
	
	//method
	public IContainer<ChainedNode> getPaintCommands() {
		return paintCommands;
	}
	
	//method
	public int getNextIndexAndUpdateNextIndex() {
		nextIndex++;
		return (nextIndex - 1);
	}
	
	//method
	public void registerImage(final String imageId, final IImage image) {
		imageRegistrator.run(imageId, image);
	}
	
	//method
	LinkedList<ChainedNode> internalGetRefPaintCommands() {
		return paintCommands;
	}
}
