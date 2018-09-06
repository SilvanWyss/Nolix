//package declaration
package ch.nolix.system.GUIClient;

//Java import
import java.io.Flushable;

//own imports
import ch.nolix.core.bases.IndexedElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.font.Font;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.UnsupportedMethodException;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class BrowserGUIPainter extends IndexedElement implements IPainter, Flushable {
	
	//attributes
	private final BrowserGUIPainterBottom bottom;
	private int xPosition = 1;
	private int yPosition = 1;
	
	//constructor
	public BrowserGUIPainter() {
		
		super(1);
		
		bottom = new BrowserGUIPainterBottom();
	}
	
	//constructor
	private BrowserGUIPainter(
		final BrowserGUIPainterBottom bottom,
		final int xPosition,
		final int yPosition) {
		
		super(bottom.getNextIndexAndUpdateNextIndex());
		
		Validator
		.suppose(xPosition)
		.thatIsNamed("x-position")
		.isPositive();
		
		Validator
		.suppose(yPosition)
		.thatIsNamed("y-position")
		.isPositive();
		
		Validator
		.suppose(bottom)
		.thatIsNamed("bottom")
		.isInstance();
		
		this.bottom = bottom;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	//method
	//TODO: Add createTranslatedPainter() method to IPainter.
	public BrowserGUIPainter createTranslatedPainter(
		final int xTranslation,
		final int yTranslation
	) {
		return new BrowserGUIPainter(bottom, xTranslation, yTranslation);
	}
	
	//method
	public void flush() {
		bottom.runPainterCommands();
	}
	
	//method
	public void paintFilledRectangle(
		final int xPostiion,
		final int yPosition,
		final int width,
		final int height
	) {
		appendPainterCommand(
			BackGUIClient.PAINT_FILLED_RECTANGLE_HEADER
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
	public void paintImage(final Image image) {
		appendPainterCommand(
			BackGUIClient.PAINT_IMAGE_HEADER + '(' + image.getSpecification() + ')'
		);
	}
	
	//method
	public void paintImage(final Image image, final int width, final int height) {
		appendPainterCommand(
			BackGUIClient.PAINT_IMAGE_HEADER
			+ '('
			+ image.getSpecification()
			+ ','
			+ width
			+ ','
			+ height
			+ ')'
		);
	}
	
	//method
	public void setColor(final Color color) {	
		appendPainterCommand(
			BackGUIClient.SET_COLOR_HEADER + "(" + color.getHexadecimalSpecification(true) + ")"
		); 
	}
	
	@Override
	public IPainter createTranslatedPainter(
		final int xTranslation,
		final int yTranslation,
		final int paintAreaWidth,
		final int paintAreaHeight
	) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int getTextWith(String text, Font font) {
		// TODO Auto-generated method stub
		return 0;
	}

	//method
	public void paintFilledPolygon(final int[] xs, final int[] ys) {
		//TODO
		throw new UnsupportedMethodException(this, "paint filled polygon");
	}

	@Override
	public void paintText(String text, Font font) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintText(String text, Font font, int maxTextWidth) {
		//TODO
	}

	@Override
	public void setColorGradient(ColorGradient colorGradient) {
		appendPainterCommand(
			BackGUIClient.SET_COLOR_GRADIENT_HEADER
			+ '('
			+ colorGradient.getSpecification()
			+ ')'
		);
	}
	
	//method
	public void translate(final int xTranslation, final int yTranslation) {
		appendPainterCommand(
			BackGUIClient.TRANSLATE_HEADER + '(' + "xTranslation" + ',' + yTranslation + ')'
		);
	}
	
	//method
	private void appendPainterCommand(final String command) {
		bottom.appendPainterCommand(this, command);
	}
}
