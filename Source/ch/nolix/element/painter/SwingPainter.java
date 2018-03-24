//package declaration
package ch.nolix.element.painter;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.element.color.Color;
import ch.nolix.element.font.Font;
import ch.nolix.primitive.validator2.Validator;

//class
public final class SwingPainter implements IPainter {
	
	//attribute
	private final Graphics graphics;
	
	//constructor
	public SwingPainter(final Graphics graphics) {
		
		Validator
		.suppose(graphics)
		.thatIsInstanceOf(Graphics.class)
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
		return font.getTextWidth(text);
	}

	//method
	public void paintFilledRectangle(
		final int xPosition,
		final int yPosition,
		final int width,
		final int	height
	) {
		graphics.fillRect(xPosition, yPosition, width, height);
	}

	//method
	public void paintText(final String text, final Font font) {
		font.paintText(text, graphics);
	}

	//method
	public void setColor(final Color color) {
		graphics.setColor(color.getJavaColor());
	}
}
