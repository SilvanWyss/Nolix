//package declaration
package ch.nolix.system.GUIClient;

//Java import
import java.io.Flushable;

//own imports
import ch.nolix.core.bases.IndexedElement;
import ch.nolix.core.specification.Specification;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.font.Font;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.helper.ArrayHelper;

//package-visible class
final class BrowserGUIPainter extends IndexedElement
implements IPainter, Flushable {
	
	//attributes
	private final BrowserGUIPainterBottom bottom;
	
	//constructor
	public BrowserGUIPainter() {
		
		super(1);
		
		bottom = new BrowserGUIPainterBottom();
	}
	
	//constructor
	private BrowserGUIPainter(final BrowserGUIPainterBottom bottom) {
		
		super(bottom.getNextIndexAndUpdateNextIndex());
		
		this.bottom = bottom;
	}
	
	//method
	public BrowserGUIPainter createPainter(
		final int xTranslation,
		final int yTranslation
	) {
		final var painter = new BrowserGUIPainter(bottom);
		
		appendPainterCommand(
			BackGUIClient.CREATE_PAINTER_HEADER
			+ painter.getIndex()
			+ '('
			+ xTranslation
			+ ','
			+ yTranslation
			+ ')'
		);
		
		return painter;
	}
		
	//method
	public IPainter createPainter(
		final int xTranslation,
		final int yTranslation,
		final int paintAreaWidth,
		final int paintAreaHeight
	) {
		final var painter = new BrowserGUIPainter(bottom);
		
		appendPainterCommand(
			BackGUIClient.CREATE_PAINTER_HEADER
			+ painter.getIndex()
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
	public void flush() {
		bottom.runPainterCommands();
	}
	
	//method
	public int getTextWith(final String text, final Font font) {
		return font.getSwingTextWidth(text);
	}
	
	//method
	public void paintFilledPolygon(final int[] x, final int[] y) {
		appendPainterCommand(
			BackGUIClient.PAINT_FILLED_POLYGON_HEADER
			+ '('
			+ ArrayHelper.createString(x)
			+ ','
			+ ArrayHelper.createString(y)
			+ ')'
		);
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
			BackGUIClient.PAINT_IMAGE_HEADER
			+ '('
			+ image.getSpecification()
			+ ')'
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

	@Override
	public void paintText(String text, Font font) {
		appendPainterCommand(
			BackGUIClient.PAINT_TEXT_HEADER
			+ '('
			+ Specification.createReproducingString(text)
			+ ','
			+ font.getSpecification()
			+ ')'
		);
	}

	//method
	public void paintText(final String text, Font font, final int maxTextWidth) {
		appendPainterCommand(
			BackGUIClient.PAINT_TEXT_HEADER
			+ '('
			+ Specification.createReproducingString(text)
			+ ','
			+ font.getSpecification()
			+ ','
			+ maxTextWidth
			+ ')'
		);
	}
	
	//method
	public void setColor(final Color color) {	
		appendPainterCommand(
			BackGUIClient.SET_COLOR_HEADER
			+ "("
			+ color.getHexadecimalSpecification(true)
			+ ")"
		); 
	}

	//method
	public void setColorGradient(final ColorGradient colorGradient) {
		appendPainterCommand(
			BackGUIClient.SET_COLOR_GRADIENT_HEADER
			+ '('
			+ colorGradient.getHexadecimalSpecification(true)
			+ ')'
		);
	}
	
	//method
	public void translate(final int xTranslation, final int yTranslation) {
		appendPainterCommand(
			BackGUIClient.TRANSLATE_HEADER
			+ '('
			+ xTranslation
			+ ','
			+ yTranslation
			+ ')'
		);
	}
	
	//method
	private void appendPainterCommand(final String command) {
		bottom.appendPainterCommand(this, command);
	}
}
