//package declaration
package ch.nolix.system.GUIClientoid;

//package-visible class
/**
 * Of the {@link Protocol} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
final class Protocol {
	
	//constants
	static final String COUNTERPART_HEADER = "Counterpart";
	static final String GUI_HEADER = "GUI";
	static final String WIDGET_BY_INDEX_PATH_HEADER = "WidgetByIndexPath";
	
	//constants
	public static final String FRONT_END_TYPE_HEADER = "FrontEndType";
	public static final String SHOW_ERROR_MESSAGE_COMMAND = "ShowErrorMessage";
	public static final String CREATE_FILE_COMMAND = "CreateFile";
	public static final String OPEN_FILE_EXPLORER_COMMAND = "OpenFileExplorer";
	public static final String READ_FILE_HEADER = "ReadFile";
	public static final String PAINT_HEADER = "Paint";
	public static final String PAINTER_BY_INDEX_HEADER = "PainterByIndex";
	
	//constants
	public static final String ADD_OR_CHANGE_ATTRIBUTES_HEADER = "AddOrChangeAttributes";
	public static final String ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER = "AddOrChangeWidgetAttributes";
	public static final String RESET_HEADER = "Reset";
	public static final String NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER = "NoteLeftMouseButtonPress";
	public static final String NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER = "NoteLeftMouseButtonRelease";
	public static final String NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER = "NoteRightMouseButtonPress";
	public static final String NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER = "NoteRightMouseButtonRelease";
	
	//constants
	public static final String CREATE_PAINTER_HEADER = "CreatePainter";
	public static final String PAINT_FILLED_RECTANGLE_HEADER = "PaintFilledRectangle";
	public static final String PAINT_FILLED_POLYGON_HEADER = "PaintFilledPolygon";
	public static final String PAINT_IMAGE_HEADER = "PaintImage";
	public static final String PAINT_TEXT_HEADER = "PaintText";
	public static final String SET_COLOR_HEADER = "SetColor";
	public static final String SET_COLOR_GRADIENT_HEADER = "SetColorGradient";
	public static final String TRANSLATE_HEADER = "Translate";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Protocol} can be created.
	 */
	private Protocol() {}
}
