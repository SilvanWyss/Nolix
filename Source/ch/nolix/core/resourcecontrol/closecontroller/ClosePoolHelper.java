package ch.nolix.core.resourcecontrol.closecontroller;

import ch.nolix.core.errorcontrol.logging.Logger;
import ch.nolix.coreapi.resourcecontrol.closecontroller.GroupCloseable;

/**
 * Of the {@link ClosePoolHelper} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2025-11-01
 */
public final class ClosePoolHelper {
  /**
   * Prevents that an instance of the {@link ClosePoolHelper} can be created.
   */
  private ClosePoolHelper() {
  }

  /**
   * Lets the given element note a close.
   * 
   * @param element
   */
  public static void letNoteClose(final GroupCloseable element) {
    try {
      element.noteClose();
    } catch (final Throwable exception) { //NOSONAR: All Throwables must be caught.
      Logger.logError(exception);
    }
  }
}
