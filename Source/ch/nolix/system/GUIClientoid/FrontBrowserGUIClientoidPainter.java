//package declaration
package ch.nolix.system.GUIClientoid;

//Java import
import java.io.Flushable;

//own imports
import ch.nolix.core.bases.IndexedElement;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.primitiveHelper.ArrayHelper;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.image.Image;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;

//package-visible class
final class FrontBrowserGUIClientoidPainter extends IndexedElement
implements IPainter, Flushable {
	
	//attributes
	private final BrowserGUIPainterBottom bottom;
	
	//constructor
	public FrontBrowserGUIClientoidPainter(final BackGUIClientoid<?> parentBackGUIClient) {
		
		super(1);
		
		bottom = new BrowserGUIPainterBottom(parentBackGUIClient);
	}
	
	//constructor
	private FrontBrowserGUIClientoidPainter(final BrowserGUIPainterBottom bottom) {
		
		super(bottom.getNextIndexAndUpdateNextIndex());
		
		this.bottom = bottom;
	}
	
	//method
	@Override
	public FrontBrowserGUIClientoidPainter createPainter(
		final int xTranslation,
		final int yTranslation
	) {
		final var painter = new FrontBrowserGUIClientoidPainter(bottom);
		
		appendPainterCommand(
			Protocol.CREATE_PAINTER_HEADER
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
	@Override
	public IPainter createPainter(
		final int xTranslation,
		final int yTranslation,
		final int paintAreaWidth,
		final int paintAreaHeight
	) {
		final var painter = new FrontBrowserGUIClientoidPainter(bottom);
		
		appendPainterCommand(
			Protocol.CREATE_PAINTER_HEADER
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
	@Override
	public void flush() {
		bottom.paintOnCounterpart();
	}
	
	//method
	@Override
	public int getTextWith(final String text, final TextFormat textFormat) {
		return textFormat.getSwingTextWidth(text);
	}
	
	//method
	@Override
	public void paintFilledPolygon(final int[] x, final int[] y) {
		appendPainterCommand(
			Protocol.PAINT_FILLED_POLYGON_HEADER
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
		appendPainterCommand(
			Protocol.PAINT_FILLED_RECTANGLE_HEADER
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
		appendPainterCommand(
			Protocol.PAINT_IMAGE_HEADER
			+ '('
			+ image.getSpecification()
			+ ')'
		);
	}
	
	//method
	@Override
	public void paintImage(final Image image, final int width, final int height) {
		appendPainterCommand(
			Protocol.PAINT_IMAGE_HEADER
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
	public void paintText(String text, TextFormat textFormat) {
		appendPainterCommand(
			Protocol.PAINT_TEXT_HEADER
			+ '('
			+ DocumentNodeoid.createReproducingString(text)
			+ ','
			+ textFormat.getSpecification()
			+ ')'
		);
	}

	//method
	@Override
	public void paintText(final String text, TextFormat textFormat, final int maxTextWidth) {
		appendPainterCommand(
			Protocol.PAINT_TEXT_HEADER
			+ '('
			+ DocumentNodeoid.createReproducingString(text)
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
		appendPainterCommand(
			Protocol.SET_COLOR_HEADER
			+ "("
			+ color.getHexadecimalSpecification(true)
			+ ")"
		);
	}

	//method
	@Override
	public void setColorGradient(final ColorGradient colorGradient) {
		appendPainterCommand(
			Protocol.SET_COLOR_GRADIENT_HEADER
			+ '('
			+ colorGradient.getHexadecimalSpecification(true)
			+ ')'
		);
	}
	
	//method
	@Override
	public void translate(final int xTranslation, final int yTranslation) {
		appendPainterCommand(
			Protocol.TRANSLATE_HEADER
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
