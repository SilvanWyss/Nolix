//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.attributeapi.Indexed;
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.independenthelper.ArrayHelper;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;

//class
final class CanvasGUICommandCreatorPainter implements Indexed, IPainter {
	
	//constant
	public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat();
	
	//attributes
	private final CanvasGUIPainterPool bottom;
	private final CachingContainer<Image> imageCachingContainer;
	private final int index;
	
	//constructor
	public CanvasGUICommandCreatorPainter(final CachingContainer<Image> imageCachingContainer) {
		this(new CanvasGUIPainterPool(), imageCachingContainer);
	}
	
	//constructor
	private CanvasGUICommandCreatorPainter(
		final CanvasGUIPainterPool bottom,
		final CachingContainer<Image> imageCachingContainer
	) {
		this.bottom = bottom;
		this.imageCachingContainer = imageCachingContainer;
		this.index = bottom.getNextIndexAndUpdateNextIndex();
	}
	
	//method
	@Override
	public CanvasGUICommandCreatorPainter createPainter(final int xTranslation,	final int yTranslation) {
		
		final var painter = new CanvasGUICommandCreatorPainter(bottom, imageCachingContainer);
		
		appendPaintCommand(
			CanvasGUICommandProtocol.CREATE_PAINTER
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
		final var painter = new CanvasGUICommandCreatorPainter(bottom, imageCachingContainer);
		
		appendPaintCommand(
			CanvasGUICommandProtocol.CREATE_PAINTER
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
	public Image getImageById(final String id) {
		return imageCachingContainer.getRefById(id);
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
	public TextFormat getDefaultTextFormat() {
		return DEFAULT_TEXT_FORMAT;
	}
	
	//method
	@Override
	public int getTextWidth(final String text, final TextFormat textFormat) {
		return textFormat.getSwingTextWidth(text);
	}
	
	//method
	@Override
	public void paintFilledPolygon(final int[] x, final int[] y) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_FILLED_POLYGON
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
			CanvasGUICommandProtocol.PAINT_FILLED_RECTANGLE
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
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ registerImageIfNotRegisteredAndGetId(image)
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImage(final Image image, final int width, final int height) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ registerImageIfNotRegisteredAndGetId(image)
			+ ','
			+ width
			+ ','
			+ height
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImageById(final String id) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ id
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImageById(final String id, final int width, final int height) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ id
			+ ','
			+ width
			+ ','
			+ height
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintText(final String text, final TextFormat textFormat) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_TEXT
			+ '('
			+ BaseNode.getEscapeStringFor(text)
			+ ','
			+ textFormat.getSpecification()
			+ ')'
		);
	}

	//method
	@Override
	public void paintText(final String text, TextFormat textFormat, final int maxTextWidth) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_TEXT
			+ '('
			+ BaseNode.getEscapeStringFor(text)
			+ ','
			+ textFormat.getSpecification()
			+ ','
			+ maxTextWidth
			+ ')'
		);
	}
	
	//method
	@Override
	public void registerImageAtId(final String id, final Image image) {
		
		if (imageCachingContainer.containsWithId(id)) {
			return;
		}
		
		imageCachingContainer.registerAtId(id, image);
		
		appendPaintCommand(
			CanvasGUICommandProtocol.REGISTER_IMAGE
			+ "("
			+ PascalCaseNameCatalogue.ID
			+ "("
			+ id
			+ "),"
			+ image.getSpecification()
			+ ")"
		);
	}
	
	//method
	@Override
	public void setColor(final Color color) {
		appendPaintCommand(
			CanvasGUICommandProtocol.SET_COLOR
			+ "("
			+ color.getSpecification()
			+ ")"
		);
	}

	//method
	@Override
	public void setColorGradient(final ColorGradient colorGradient) {
		appendPaintCommand(
			CanvasGUICommandProtocol.SET_COLOR_GRADIENT
			+ '('
			+ colorGradient.getSpecification()
			+ ')'
		);
	}
	
	//method
	@Override
	public void translate(final int xTranslation, final int yTranslation) {
		appendPaintCommand(
			CanvasGUICommandProtocol.TRANSLATE
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
	
	//method
	private String registerImageIfNotRegisteredAndGetId(final Image image) {
		
		final var idContainer = imageCachingContainer.getOptionallyIdOf(image);
		
		if (idContainer.isEmpty()) {
			
			final var id = imageCachingContainer.registerAndGetId(image);
			
			appendPaintCommand(
				CanvasGUICommandProtocol.REGISTER_IMAGE
				+ "("
				+ id
				+ ","
				+ image.getSpecification()
				+ ")"
			);
			
			return id;
		}
		
		return idContainer.getRefElement();
	}
}
