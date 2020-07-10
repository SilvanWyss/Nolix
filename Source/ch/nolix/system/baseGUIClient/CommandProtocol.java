//package declaration
package ch.nolix.system.baseGUIClient;

//class
final class CommandProtocol {
	
	//constants
	public static final String ADD_OR_CHANGE_WIDGETS_ATTRIBUTES = "AddOrChangeWidgetAttributes";
	public static final String GET_FILE = "GetFile";
	public static final String GET_TEXT_FROM_CLIPBOARD = "GetTextFromClipboard";
	public static final String NOTE_INPUT = "NoteInput";
	public static final String OPEN_FILE_EXPLORER = "OpenFileExplorer";
	public static final String RESET = "Reset";
	public static final String SAVE_FILE = "SaveFile";
	public static final String SET_PAINT_COMMANDS = "SetPaintCommands";
	public static final String SET_TITLE = "SetTitle";
	public static final String SHOW_ERROR_MESSAGE = "ShowErrorMessage";
	
	//visibility-reducing constructor
	private CommandProtocol() {}
}
