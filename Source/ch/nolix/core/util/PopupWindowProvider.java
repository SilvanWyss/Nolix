//package declaration
package ch.nolix.core.util;

//Java import
import javax.swing.JOptionPane;

//own imports
import ch.nolix.core.constants.CharacterCatalogue;
import ch.nolix.core.constants.StringCatalogue;

//class
/**
 * The {@link PopupWindowProvider} provides methods to show small pop-up windows.
 * Of the {@link PopupWindowProvider} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 130
 */
public final class PopupWindowProvider {
	
	//constants
	private static final String ERROR_WINDOW_TITLE = "Error";
	private static final String MESSAGE_WINDOW_TITLE = "Message";
	private static final String REQUEST_WINDOW_TITLE = "Request";

	//static method
	/**
	 * Shows an error window with the given error message.
	 * 
	 * @param errorMessage
	 */
	public static void showErrorWindow(final String errorMessage) {
		JOptionPane.showMessageDialog(
			null,
			errorMessage,
			ERROR_WINDOW_TITLE,
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	//static method
	/**
	 * Shows an exception window with the given exception.
	 * 
	 * @param exception
	 */
	public static void showExceptionWindow(final Exception exception) {
		
		String title;
		String text;
		
		//Handles the case that the given exception is null.
		if (exception == null) {
			title = "Exception";
			text = "An exception, that is null, occured.";
		}
		
		//Handles the case that the given exception is not null.
		else {
			
			//Sets the title.
			title = exception.getClass().getSimpleName();
			
			//Sets the text.
				text = StringCatalogue.EMPTY_STRING;
				
				//Handles the case that the given exception has a message.
				if (exception.getMessage() != null && !exception.getMessage().isEmpty()) {
					text += exception.getMessage() + CharacterCatalogue.NEW_LINE + CharacterCatalogue.NEW_LINE;
				}
				
				//Iterates the stack trace of the given exception.
				for (final StackTraceElement ste : exception.getStackTrace()) {
					
					final String[] classPath = ste.getClassName().split("\\.");
					text += classPath[classPath.length - 1];
							
					if (ste.getLineNumber() > 0) {
						text += " (line " + ste.getLineNumber() + ")";
					}
					
					text += CharacterCatalogue.NEW_LINE;
				}
		}
		
		JOptionPane.showMessageDialog(
			null,
			text,
			title,
			JOptionPane.ERROR_MESSAGE
		);
	}
	
	/**
	 * Shows a message window with the given message.
	 * 
	 * @param message
	 */
	public static void showMessageWindow(final String message) {
		JOptionPane.showMessageDialog(
			null,
			message,
			MESSAGE_WINDOW_TITLE,
			JOptionPane.INFORMATION_MESSAGE
		);
	}
	
	//static method
	/**
	 * Shows a request window with the given question.
	 * 
	 * @param question
	 * @return true if the yes-button was selected.
	 */
	public static boolean showRequestWindow(final String question) {
		
		final var result = JOptionPane.showConfirmDialog(
			null,
			question,
			REQUEST_WINDOW_TITLE,
			JOptionPane.YES_NO_OPTION
		);
		
		return (result == 0);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private PopupWindowProvider() {}
}
