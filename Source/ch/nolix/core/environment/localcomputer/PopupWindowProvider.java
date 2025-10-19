package ch.nolix.core.environment.localcomputer;

import javax.swing.JOptionPane;

import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;

/**
 * The {@link PopupWindowProvider} provides methods to show small pop-up
 * windows. Of the {@link PopupWindowProvider} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-08-15
 */
public final class PopupWindowProvider {
  private static final String ERROR_WINDOW_TITLE = "Error";

  private static final String MESSAGE_WINDOW_TITLE = "Message";

  private static final String REQUEST_WINDOW_TITLE = "Request";

  /**
   * Prevents that an instance of the {@link PopupWindowProvider} can be created.
   */
  private PopupWindowProvider() {
  }

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
      JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Shows an error window for the given error.
   * 
   * @param error
   */
  public static void showErrorWindow(final Throwable error) {
    String title;
    final var textStringBuilder = new StringBuilder();

    //Handles the case that the given exception is null.
    if (error == null) {
      title = "Exception";
      textStringBuilder.append("An exception, that is null, occured.");

      //Handles the case that the given exception is not null.
    } else {
      //Sets the title.
      title = error.getClass().getSimpleName();

      //Handles the case that the given exception has a message.
      if (error.getMessage() != null && !error.getMessage().isEmpty()) {
        textStringBuilder.append(
          error.getMessage() + CharacterCatalog.NEW_LINE + CharacterCatalog.NEW_LINE);
      }

      //Iterates the stack trace of the given exception.
      for (final StackTraceElement e : error.getStackTrace()) {
        final String[] classPath = e.getClassName().split("\\.");
        textStringBuilder.append(classPath[classPath.length - 1]);

        if (e.getLineNumber() > 0) {
          textStringBuilder.append(" (line ").append(e.getLineNumber()).append(")");
        }

        textStringBuilder.append(CharacterCatalog.NEW_LINE);
      }
    }

    JOptionPane.showMessageDialog(
      null,
      textStringBuilder.toString(),
      title,
      JOptionPane.ERROR_MESSAGE);
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
      JOptionPane.INFORMATION_MESSAGE);
  }

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
      JOptionPane.YES_NO_OPTION);

    return (result == 0);
  }
}
