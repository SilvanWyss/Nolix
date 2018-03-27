//package declaration
package ch.nolix.element.painter;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.font.Font;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SwingPainter implements IPainter {
	
	//attribute
	private final Graphics graphics;
	
	//optional attribute
	private ColorGradient colorGradient;
	
	//constructor
	public SwingPainter(final Graphics graphics) {
		
		Validator
		.suppose(graphics)
		.thatIsOfType(Graphics.class)
		.isNotNull();
		
		this.graphics = graphics;
	}
	
	//method
	public IPainter createTranslatedPainter(
		final int xTranslation,
		final int yTranslation
	) {
		final Graphics graphics = this.graphics.create();
		graphics.translate(xTranslation, yTranslation);
		return new SwingPainter(graphics);
	}

	//method
	public int getTextWith(final String text, final Font font) {
		return font.getSwingTextWidth(text);
	}

	//method
	public void paintFilledRectangle(
		final int xPosition,
		final int yPosition,
		final int width,
		final int	height
	) {
		if (hasColorGradient()) {
			
		}
		
		graphics.fillRect(xPosition, yPosition, width, height);
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
		graphics.setColor(color.getJavaColor());
		colorGradient = null;
	}

	//method
	public void setColorGradient(ColorGradient colorGradient) {
		
		Validator
		.suppose(colorGradient)
		.thatIsOfType(ColorGradient.class)
		.isNotNull();
		
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
