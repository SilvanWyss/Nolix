//package declaration
package ch.nolix.system.gui.main;

//own imports
import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Indexed;
import ch.nolix.core.caching.CachingContainer;
import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.I2ElementTaker;
import ch.nolix.core.independent.independenthelper.CentralArrayHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.gui.painter.BasePainter;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.textformatapi.ITextFormat;

//class
public final class CanvasGUICommandCreatorPainter extends BasePainter implements Indexed {
	
	//constant
	public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat();
	
	//static method
	public static CanvasGUICommandCreatorPainter withImageCacheAndImageRegistrator(
		final CachingContainer<IImage> imageCache,
		final I2ElementTaker<String, IImage> imageRegistrator
	) {
		return
		new CanvasGUICommandCreatorPainter(
			new SingleContainer<>(),
			new CanvasGUIPainterPool(imageRegistrator),
			imageCache
		);
	}
	
	//static method
	public static CanvasGUICommandCreatorPainter withParentPainterAndBottomAndImageCache(
		final CanvasGUICommandCreatorPainter parentPainter,
		final CanvasGUIPainterPool bottom,
		final CachingContainer<IImage> imageCache
	) {
		return new CanvasGUICommandCreatorPainter(new SingleContainer<>(parentPainter), bottom, imageCache);
	}
	
	//attribute
	private final CanvasGUIPainterPool bottom;
	
	//attribute
	private final CachingContainer<IImage> imageCache;
	
	//attribute
	private final int index;
	
	//constructor
	private CanvasGUICommandCreatorPainter(
		final SingleContainer<IPainter> parentPainterContainer,
		final CanvasGUIPainterPool bottom,
		final CachingContainer<IImage> imageCache
	) {
		
		super(parentPainterContainer);
		
		this.bottom = bottom;
		this.imageCache = imageCache;
		this.index = bottom.getNextIndexAndUpdateNextIndex();
	}
	
	//method
	@Override
	public CanvasGUICommandCreatorPainter createPainter(final int xTranslation,	final int yTranslation) {
		
		final var painter = withParentPainterAndBottomAndImageCache(this, bottom, imageCache);
		
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
		
		final var painter = withParentPainterAndBottomAndImageCache(this, bottom, imageCache);
		
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
	public IImage getImageById(final String id) {
		return imageCache.getRefById(id);
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
	public double getOpacity() {
		throw new ArgumentDoesNotSupportMethodException(this, "getOpacity");
	}
	
	//method
	@Override
	public int getTextWidth(final String text, final ITextFormat textFormat) {
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
	public void paintImage(final IImage mutableImage) {
		appendPaintCommand(
			CanvasGUICommandProtocol.PAINT_IMAGE_BY_ID
			+ '('
			+ registerImageIfNotRegisteredAndGetId(mutableImage)
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImage(final IImage mutableImage, final int width, final int height) {
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
	public void paintText(final String text, final ITextFormat textFormat) {
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
	public void paintText(final String text, ITextFormat textFormat, final int maxTextWidth) {
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
	public void setColor(final IColor color) {
		appendPaintCommand(
			CanvasGUICommandProtocol.SET_COLOR
			+ "("
			+ color.getSpecification()
			+ ")"
		);
	}

	//method
	@Override
	public void setColorGradient(final IColorGradient colorGradient) {
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
	public LinkedList<ChainedNode> internalGetRefPaintCommands() {
		return bottom.internalGetRefPaintCommands();
	}
	
	//method
	private void appendPaintCommand(final String command) {
		bottom.appendPaintCommand(this, command);
	}
	
	//method
	private String registerImageIfNotRegisteredAndGetId(final IImage mutableImage) {
		
		final var idContainer = imageCache.getOptionalIdOf(mutableImage);
		
		if (idContainer.isEmpty()) {
			
			final var id = imageCache.registerAndGetId(mutableImage);
			
			bottom.registerImage(id, mutableImage);
			
			return id;
		}
		
		return idContainer.getRefElement();
	}
}
