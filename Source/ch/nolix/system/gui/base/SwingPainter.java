//package declaration
package ch.nolix.system.gui.base;

//Java imports
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

//own imports
import ch.nolix.core.caching.CachingContainer;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.SingleContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.colorapi.IColor;
import ch.nolix.systemapi.guiapi.colorapi.IColorGradient;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.textformatapi.ITextFormat;

//class
public final class SwingPainter extends BasePainter {
	
	//constant
	public static final double DEFAULT_OPACITY = 1.0;
	
	//constant
	public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat();
	
	//static method
	public static SwingPainter withImageCacheAndGraphics(
		final CachingContainer<IImage> imageCache,
		final Graphics2D graphics
	) {
		return new SwingPainter(new SingleContainer<>(), imageCache, graphics);
	}
	
	//static method
	public static SwingPainter withParentPainterAndImageCacheAndGraphics(
		final SwingPainter parentPainter,
		final CachingContainer<IImage> imageCache,
		final Graphics2D graphics
	) {
		return new SwingPainter(new SingleContainer<>(parentPainter), imageCache, graphics);
	}
	
	//attribute
	private double opacity = DEFAULT_OPACITY;
	
	//attribute
	private final CachingContainer<IImage> imageCache;
	
	//attribute
	private final Graphics2D graphics;
	
	//optional attribute
	private IColorGradient colorGradient;
	
	//constructor
	private SwingPainter(
		final SingleContainer<IPainter> parentPainterContainer,
		final CachingContainer<IImage> imageCache,
		final Graphics2D graphics
	) {
		
		super(parentPainterContainer);
		
		GlobalValidator.assertThat(imageCache).thatIsNamed("image cache").isNotNull();
		GlobalValidator.assertThat(graphics).thatIsNamed(Graphics.class).isNotNull();
		
		this.imageCache = imageCache;
		this.graphics = graphics;
		
		graphics.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);
	}
	
	//method
	@Override
	public IPainter createPainter(final int xTranslation, final int yTranslation) {
		final var lGraphics = graphics.create();
		lGraphics.translate(xTranslation, yTranslation);
		return SwingPainter.withImageCacheAndGraphics(imageCache, (Graphics2D)lGraphics);
	}
	
	//method
	@Override
	public IPainter createPainter(
			final int xTranslation,
			final int yTranslation,
			final int paintAreaWidth,
			final int paintAreaHeight
	) {
		return
		withParentPainterAndImageCacheAndGraphics(
			this,
			imageCache,
			(Graphics2D)
			graphics.create(
				xTranslation,
				yTranslation,
				paintAreaWidth,
				paintAreaHeight
			)
		);
	}
	
	//method
	@Override
	public TextFormat getDefaultTextFormat() {
		return DEFAULT_TEXT_FORMAT;
	}
	
	//method
	@Override
	public IImage getImageById(final String id) {
		return imageCache.getRefById(id);
	}
	
	//method
	@Override
	public double getOpacity() {
		return opacity;
	}
	
	//method
	@Override
	public int getTextWidth(final String text, final ITextFormat textFormat) {
		return textFormat.getSwingTextWidth(text);
	}
	
	//method
	@Override
	public void paintFilledPolygon(final int[] xs, final int[] ys) {
		
		GlobalValidator
		.assertThat(ys)
		.thatIsNamed("y-points")
		.hasElementCount(xs.length);
		
		graphics.fillPolygon(xs, ys, xs.length);
	}
	
	//method
	@Override
	public void paintFilledRectangle(
		final int xPosition,
		final int yPosition,
		final int width,
		final int height
	) {
		if (!hasColorGradient()) {
			graphics.fillRect(xPosition, yPosition, width, height);
		} else {
			
			final var graphics2D = (Graphics2D)this.graphics;
			
			graphics2D.setPaint(
				colorGradient.createSwingGradientPaint(
					xPosition,
					yPosition,
					width,
					height
				)
			);
			
			graphics2D.fillRect(xPosition, yPosition, width, height);
		}
	}
	
	//method
	@Override
	public void paintImage(final IImage mutableImage) {
		graphics.drawImage(mutableImage.toBufferedImage(), 0, 0, null);
	}
	
	//method
	@Override
	public void paintImage(final IImage mutableImage, final int width, final int height) {
		graphics.drawImage(mutableImage.toBufferedImage(), 0, 0, width, height, null);
	}
	
	//method
	@Override
	public void paintImageById(final String id) {
		/*
		 * Although the register image command will be sent to a client before a painting image by id command,
		 * the painting command could arrive and processed first, because the processing of the register image command
		 * lasts longer.
		 * ->An image is painted by id only if the current SwingPainter has registered an image for the given id yet.
		 */
		if (imageCache.containsWithId(id)) {
			paintImage(getImageById(id));
		}
	}
	
	//method
	@Override
	public void paintImageById(final String id, final int width, final int height) {
		
		//Calls other method.
		paintImage(getImageById(id), width, height);
	}
	
	//method
	@Override
	public void paintText(final String text, final ITextFormat textFormat) {
		textFormat.paintSwingText(graphics, text);
	}
	
	//method
	@Override
	public void paintText(final String text, final ITextFormat textFormat, final int maxTextWidth) {
		textFormat.paintSwingText(graphics, text, maxTextWidth);
	}
	
	//method
	@Override
	public void setColor(final IColor color) {
		graphics.setColor(color.toSwingColor());
		colorGradient = null;
	}
	
	//method
	@Override
	public void setColorGradient(final IColorGradient colorGradient) {
		
		GlobalValidator.assertThat(colorGradient).thatIsNamed(ColorGradient.class).isNotNull();
		
		this.colorGradient = colorGradient;
	}
	
	//method
	@Override
	public void setOpacity(final double opacity) {
		
		GlobalValidator.assertThat(opacity).thatIsNamed(LowerCaseCatalogue.OPACITY).isBetween(0.0, 1.0);
		
		this.opacity = opacity;
		
		applyEffectiveOpacity();
	}
	
	//method
	@Override
	public void translate(final int xTranslation, final int yTranslation) {
		graphics.translate(xTranslation, yTranslation);
	}
	
	//method
	private void applyEffectiveOpacity() {
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)getEffectiveOpacity()));
	}
	
	//method
	private boolean hasColorGradient() {
		return (colorGradient != null);
	}
}
