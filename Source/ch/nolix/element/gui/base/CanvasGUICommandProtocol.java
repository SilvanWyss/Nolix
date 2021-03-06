//package declaration
package ch.nolix.element.gui.base;

//class
final class CanvasGUICommandProtocol {
	
	//constants
	public static final String CREATE_PAINTER = "CreatePainter";
	public static final String PAINT_FILLED_RECTANGLE = "PaintFilledRectangle";
	public static final String PAINT_FILLED_POLYGON = "PaintFilledPolygon";
	public static final String PAINT_IMAGE_BY_ID = "PaintImageById";
	public static final String PAINT_TEXT = "PaintText";
	public static final String REGISTER_IMAGE = "RegisterImage";
	public static final String SET_COLOR = "SetColor";
	public static final String SET_COLOR_GRADIENT = "SetColorGradient";
	public static final String TRANSLATE = "Translate";
	
	//visibility-reduced constructor
	private CanvasGUICommandProtocol() {}
}
