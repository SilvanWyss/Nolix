//package declaration
package ch.nolix.core.util;

//Java import
import javax.swing.JOptionPane;

//class
/**
 * This class provides methods to show small pop-up windows.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 60
 */
public final class PopupWindowProvider {
	
	//error window title
	private static final String ERROR_WINDOW_TITLE = "Error";
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
	 * Shows a request window with the given question.
	 * 
	 * @param question
	 * @return true if the yes-button was selected.
	 */
	public static boolean showRequestWindow(final String question) {
		
		final int result = JOptionPane.showConfirmDialog(
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
