//package declaration
package ch.nolix.system.baseGUIClient;

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
	public static final String GUI_HEADER = "GUI";
	public static final String GUI_TYPE_HEADER = "GUI_Type";
	
	//constants
	public static final String SHOW_ERROR_MESSAGE_HEADER = "ShowErrorMessage";
	public static final String OPEN_FILE_EXPLORER_HEADER = "OpenFileExplorer";
	public static final String ADD_OR_CHANGE_ATTRIBUTES_HEADER = "AddOrChangeAttributes";
	public static final String ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER = "AddOrChangeWidgetAttributes";
	public static final String RESET_HEADER = "Reset";
	public static final String SET_TITLE_HEADER = "SetTitle";
	public static final String SET_PAINT_COMMANDS_HEADER = "SetPaintCommands";
	
	//constants
	public static final String NOTE_KEY_TYPING_HEADER = "NoteKeyTyping";
	public static final String NOTE_KEY_PRESS_HEADER = "NoteKeyPress";
	public static final String NOTE_KEY_RELEASE_HEADER = "NoteKeyRelease";
	public static final String NOTE_LEFT_MOUSE_BUTTON_CLICK_HEADER = "NoteLeftMouseButtonClick";
	public static final String NOTE_LEFT_MOUSE_BUTTON_PRESS_HEADER = "NoteLeftMouseButtonPress";
	public static final String NOTE_LEFT_MOUSE_BUTTON_RELEASE_HEADER = "NoteLeftMouseButtonRelease";
	public static final String NOTE_RIGHT_MOUSE_BUTTON_CLICK_HEADER = "NoteRightMouseButtonClick";
	public static final String NOTE_RIGHT_MOUSE_BUTTON_PRESS_HEADER = "NoteRightMouseButtonPress";
	public static final String NOTE_RIGHT_MOUSE_BUTTON_RELEASE_HEADER = "NoteRightMouseButtonRelease";
	public static final String NOTE_MOUSE_WHEEL_CLICK_HEADER = "NotetMouseWheelClick";
	public static final String NOTE_MOUSE_WHEEL_PRESS_HEADER = "NotetMouseWheelPress";
	public static final String NOTE_MOUSE_WHEEL_RELEASE_HEADER = "NoteMouseWheelRelease";
	public static final String NOTE_MOUSE_WHEEL_ROTATION_STEP_HEADER = "NoteMouseWheelRotationStep";
	public static final String NOTE_MOUSE_MOVE_HEADER = "NoteMouseMove";
	public static final String NOTE_RESIZE_HEADER = "NoteResize";
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Protocol} can be created.
	 */
	private Protocol() {}
}
