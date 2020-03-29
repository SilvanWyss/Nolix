//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.attributeAPI.Indexed;
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.independentHelpers.ArrayHelper;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;

//class
final class CanvasGUICommandCreatorPainter implements Indexed, IPainter {
	
	//attributes
	private final CanvasGUIPainterPool bottom;
	private final int index;
	
	//constructor
	public CanvasGUICommandCreatorPainter() {
		this(new CanvasGUIPainterPool());
	}
	
	//constructor
	private CanvasGUICommandCreatorPainter(final CanvasGUIPainterPool bottom) {
		this.bottom = bottom;
		this.index = bottom.getNextIndexAndUpdateNextIndex();
	}
	
	//method
	@Override
	public CanvasGUICommandCreatorPainter createPainter(
		final int xTranslation,
		final int yTranslation
	) {
		final var painter = new CanvasGUICommandCreatorPainter(bottom);
		
		appendPaintCommand(
			CanvasGUIProtocol.CREATE_PAINTER_HEADER
			+ '('
			+ xTranslation
			+ ','
			+ yTranslation
			+ ')'
		);
		
		return painter;
	}
		
	//method
	@Override
	public IPainter createPainter(
		final int xTranslation,
		final int yTranslation,
		final int paintAreaWidth,
		final int paintAreaHeight
	) {
		final var painter = new CanvasGUICommandCreatorPainter(bottom);
		
		appendPaintCommand(
			CanvasGUIProtocol.CREATE_PAINTER_HEADER
			+ '('
			+ xTranslation
			+ ','
			+ yTranslation
			+ ','
			+ paintAreaWidth
			+ ','
			+ paintAreaHeight
			+ ')'
		);
		
		return painter;
	}
	
	//method
	@Override
	public int getIndex() {
		return index;
	}
	
	//method
	public IContainer<ChainedNode> getCommands() {
		return bottom.getPaintCommands();
	}
	
	//method
	@Override
	public int getTextWith(final String text, final TextFormat textFormat) {
		return textFormat.getSwingTextWidth(text);
	}
	
	//method
	@Override
	public void paintFilledPolygon(final int[] x, final int[] y) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_FILLED_POLYGON_HEADER
			+ '('
			+ ArrayHelper.createString(x)
			+ ','
			+ ArrayHelper.createString(y)
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintFilledRectangle(
		final int xPostiion,
		final int yPosition,
		final int width,
		final int height
	) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_FILLED_RECTANGLE_HEADER
			+ "("
			+ xPostiion
			+ ","
			+ yPosition
			+ ","
			+ width
			+ ","
			+ height
			+ ")"
		);
	}
	
	//method
	@Override
	public void paintImage(final Image image) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_IMAGE_HEADER
			+ '('
			+ image.getSpecification()
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImage(final Image image, final int width, final int height) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_IMAGE_HEADER
			+ '('
			+ image.getSpecification()
			+ ','
			+ width
			+ ','
			+ height
			+ ')'
		);
	}

	@Override
	public void paintText(String text, TextFormat textFormat) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_TEXT_HEADER
			+ '('
			+ BaseNode.createReproducingString(text)
			+ ','
			+ textFormat.getSpecification()
			+ ')'
		);
	}

	//method
	@Override
	public void paintText(final String text, TextFormat textFormat, final int maxTextWidth) {
		appendPaintCommand(
			CanvasGUIProtocol.PAINT_TEXT_HEADER
			+ '('
			+ BaseNode.createReproducingString(text)
			+ ','
			+ textFormat.getSpecification()
			+ ','
			+ maxTextWidth
			+ ')'
		);
	}
	
	//method
	@Override
	public void setColor(final Color color) {
		appendPaintCommand(
			CanvasGUIProtocol.SET_COLOR_HEADER
			+ "("
			+ color.getSpecification()
			+ ")"
		);
	}

	//method
	@Override
	public void setColorGradient(final ColorGradient colorGradient) {
		appendPaintCommand(
			CanvasGUIProtocol.SET_COLOR_GRADIENT_HEADER
			+ '('
			+ colorGradient.getSpecification()
			+ ')'
		);
	}
	
	//method
	@Override
	public void translate(final int xTranslation, final int yTranslation) {
		appendPaintCommand(
			CanvasGUIProtocol.TRANSLATE_HEADER
			+ '('
			+ xTranslation
			+ ','
			+ yTranslation
			+ ')'
		);
	}
	
	//method
	private void appendPaintCommand(final String command) {
		bottom.appendPaintCommand(this, command);
	}
}
