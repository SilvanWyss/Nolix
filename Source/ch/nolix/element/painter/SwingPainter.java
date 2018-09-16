//package declaration
package ch.nolix.element.painter;

//Java imports
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.font.Font;
import ch.nolix.element.image.Image;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SwingPainter implements IPainter {
	
	//attribute
	private final Graphics graphics;
	
	//optional attribute
	private ColorGradient colorGradient;
	
	//constructor
	public SwingPainter(final Graphics graphics) {
		
		Validator.suppose(graphics).isInstanceOf(Graphics.class);
		
		this.graphics = graphics;
		
		((Graphics2D)graphics).setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
		);
	}
	
	//method
	public IPainter createPainter(
		final int xTranslation,
		final int yTranslation
	) {
		final Graphics graphics = this.graphics.create();
		graphics.translate(xTranslation, yTranslation);
		return new SwingPainter(graphics);
	}
	
	//method
	public IPainter createPainter(
			final int xTranslation,
			final int yTranslation,
			final int paintAreaWidth,
			final int paintAreaHeight
	) {
		return new SwingPainter(
			graphics.create(
				xTranslation,
				yTranslation,
				paintAreaWidth,
				paintAreaHeight
			)
		);
	}

	//method
	public int getTextWith(final String text, final Font font) {
		return font.getSwingTextWidth(text);
	}
	
	//method
	public void paintFilledPolygon(final int[] xs, final int[] ys) {
		
		Validator
		.suppose(ys)
		.thatIsNamed("y-points")
		.hasElementCount(xs.length);		
		
		graphics.fillPolygon(xs, ys, xs.length);
	}

	//method
	public void paintFilledRectangle(
		final int xPosition,
		final int yPosition,
		final int width,
		final int	height
	) {
		if (!hasColorGradient()) {			
			graphics.fillRect(xPosition, yPosition, width, height);
		}
		else {
			
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
	public void paintImage(
		final Image image,
		final int width,
		final int height
	) {
		graphics.drawImage(image.toBufferedImage(), 0, 0, width, height, null);
	}
	
	//method
	public void paintText(final String text, final Font font) {
		font.paintSwingText(text, graphics);
	}
	
	//method
	public void paintText(
		String text,
		Font font,
		int maxTextWidth
	) {
		font.paintSwingText(text, maxTextWidth, graphics);
	}

	//method
	public void setColor(final Color color) {
		graphics.setColor(color.createSwingColor());
		colorGradient = null;
	}

	//method
	public void setColorGradient(ColorGradient colorGradient) {
		
		Validator.suppose(colorGradient).isInstanceOf(ColorGradient.class);
		
		this.colorGradient = colorGradient;
	}
	
	//method
	public void translate(
		final int xTranslation,
		final int yTranslation
	) {
		graphics.translate(xTranslation, yTranslation);	
	}
	
	//method
	private boolean hasColorGradient() {
		return (colorGradient != null);
	}
}
