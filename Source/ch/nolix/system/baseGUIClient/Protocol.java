//package declaration
package ch.nolix.system.baseGUIClient;

//class
/**
 * Of the {@link Protocol} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
final class Protocol {
	
	//constants
	public static final String GUI_HEADER = "GUI";
	public static final String GUI_TYPE_HEADER = "GUI_Type";
	
	//constants
	public static final String ADD_OR_CHANGE_ATTRIBUTES_HEADER = "AddOrChangeAttributes";
	public static final String ADD_OR_CHANGE_WIDGETS_ATTRIBUTES_HEADER = "AddOrChangeWidgetAttributes";
	public static final String GET_FILE_HEADER = "GetFile";
	public static final String OPEN_FILE_EXPLORER_HEADER = "OpenFileExplorer";
	public static final String RESET_HEADER = "Reset";
	public static final String SAVE_FILE_HEADER = "SaveFile";
	public static final String SET_TITLE_HEADER = "SetTitle";
	public static final String SET_PAINT_COMMANDS_HEADER = "SetPaintCommands";
	public static final String SHOW_ERROR_MESSAGE_HEADER = "ShowErrorMessage";
	
	//constants
	public static final String NOTE_INPUT = "NoteInput";
	public static final String NOTE_RESIZE_HEADER = "NoteResize";
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link Protocol} can be created.
	 */
	private Protocol() {}
}
