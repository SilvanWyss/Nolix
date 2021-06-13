//package declaration
package ch.nolix.element.gui.base;

//Java imports
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

//own imports
import ch.nolix.common.caching.CachingContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.color.ColorGradient;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.textformat.TextFormat;

//class
public final class SwingPainter implements IPainter {
	
	//constant
	public static final TextFormat DEFAULT_TEXT_FORMAT = new TextFormat();
	
	//attributes
	private final CachingContainer<MutableImage> imageCache;
	private final Graphics graphics;
	
	//optional attribute
	private ColorGradient colorGradient;
	
	//constructor
	public SwingPainter(final CachingContainer<MutableImage> imageCache, final Graphics graphics) {
		
		Validator.assertThat(imageCache).thatIsNamed("image cache").isNotNull();
		Validator.assertThat(graphics).thatIsNamed(Graphics.class).isNotNull();
		
		this.imageCache = imageCache;
		this.graphics = graphics;
		
		((Graphics2D)graphics).setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON
		);
	}
	
	//method
	@Override
	public IPainter createPainter(final int xTranslation, final int yTranslation) {
		final var lGraphics = graphics.create();
		lGraphics.translate(xTranslation, yTranslation);
		return new SwingPainter(imageCache, lGraphics);
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
		new SwingPainter(
			imageCache,
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
	public MutableImage getImageById(final String id) {
		return imageCache.getRefById(id);
	}
	
	//method
	@Override
	public int getTextWidth(final String text, final TextFormat textFormat) {
		return textFormat.getSwingTextWidth(text);
	}
	
	//method
	@Override
	public void paintFilledPolygon(final int[] xs, final int[] ys) {
		
		Validator
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
	public void paintImage(final MutableImage mutableImage) {
		graphics.drawImage(mutableImage.toBufferedImage(), 0, 0, null);
	}
	
	//method
	@Override
	public void paintImage(final MutableImage mutableImage, final int width, final int height) {
		graphics.drawImage(mutableImage.toBufferedImage(), 0, 0, width, height, null);
	}
	
	//method
	@Override
	public void paintImageById(final String id) {
		
		//Calls other method.
		paintImage(getImageById(id));
	}
	
	//method
	@Override
	public void paintImageById(final String id, final int width, final int height) {
		
		//Calls other method.
		paintImage(getImageById(id), width, height);
	}
	
	//method
	@Override
	public void paintText(final String text, final TextFormat textFormat) {
		textFormat.paintSwingText(graphics, text);
	}
	
	//method
	@Override
	public void paintText(final String text, final TextFormat textFormat, final int maxTextWidth) {
		textFormat.paintSwingText(graphics, text, maxTextWidth);
	}
	
	//method
	@Override
	public void registerImageAtId(final String id, final MutableImage mutableImage) {
		
		if (imageCache.containsWithId(id)) {
			return;
		}
		
		imageCache.registerAtId(id, mutableImage);
	}
	
	//method
	@Override
	public void setColor(final Color color) {
		graphics.setColor(color.createSwingColor());
		colorGradient = null;
	}
	
	//method
	@Override
	public void setColorGradient(final ColorGradient colorGradient) {
		
		Validator.assertThat(colorGradient).thatIsNamed(ColorGradient.class).isNotNull();
		
		this.colorGradient = colorGradient;
	}
	
	//method
	@Override
	public void translate(final int xTranslation, final int yTranslation) {
		graphics.translate(xTranslation, yTranslation);
	}
	
	//method
	private boolean hasColorGradient() {
		return (colorGradient != null);
	}
}
