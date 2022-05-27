//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Indexed;
import ch.nolix.core.caching.CachingContainer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.I2ElementTaker;
import ch.nolix.core.independent.independenthelper.CentralArrayHelper;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
final class CanvasGUICommandCreatorPainter implements Indexed, IPainter {
	
	//constant
	public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat();
	
	//attribute
	private final CanvasGUIPainterPool bottom;
	
	//attribute
	private final CachingContainer<IImage<?>> imageCachingContainer;
	
	//attribute
	private final int index;
	
	//constructor
	public CanvasGUICommandCreatorPainter(
		final CachingContainer<IImage<?>> imageCachingContainer,
		final I2ElementTaker<String, IImage<?>> imageRegistrator
	) {
		this(new CanvasGUIPainterPool(imageRegistrator), imageCachingContainer);
	}
	
	//constructor
	private CanvasGUICommandCreatorPainter(
		final CanvasGUIPainterPool bottom,
		final CachingContainer<IImage<?>> imageCachingContainer
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
	public IImage<?> getImageById(final String id) {
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
			+ CentralArrayHelper.createString(x)
			+ ','
			+ CentralArrayHelper.createString(y)
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
	public void paintImage(final IImage<?> mutableImage) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ registerImageIfNotRegisteredAndGetId(mutableImage)
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImage(final IImage<?> mutableImage, final int width, final int height) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ registerImageIfNotRegisteredAndGetId(mutableImage)
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
			+ ChainedNode.getEscapeStringFor(text)
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
			+ ChainedNode.getEscapeStringFor(text)
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
	public void setOpacity(final double opacity) {
		
		GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseCatalogue.OPACITY).isBetween(0.0, 1.0);
		
		appendPaintCommand(
			ChainedNode.withHeaderAndAttribute(
				CanvasGUICommandProtocol.SET_OPACITY_PERCENTAGE,
				ChainedNode.withHeader(String.valueOf(opacity))
			)
			.toString()
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
	LinkedList<ChainedNode> internalGetRefPaintCommands() {
		return bottom.internalGetRefPaintCommands();
	}
	
	//method
	private void appendPaintCommand(final String command) {
		bottom.appendPaintCommand(this, command);
	}
	
	//method
	private String registerImageIfNotRegisteredAndGetId(final IImage<?> mutableImage) {
		
		final var idContainer = imageCachingContainer.getOptionalIdOf(mutableImage);
		
		if (idContainer.isEmpty()) {
			
			final var id = imageCachingContainer.registerAndGetId(mutableImage);
			
			bottom.registerImage(id, mutableImage);
			
			return id;
		}
		
		return idContainer.getRefElement();
	}
}
