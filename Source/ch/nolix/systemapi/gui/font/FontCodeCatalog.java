/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.font;

/**
 * Of the {@link FontCodeCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class FontCodeCatalog {
  public static final String ARIAL = "Arial";

  public static final String ARIAL_BLACK = "Arial Black";

  public static final String COMIC_SANS_MS = "Comic Sans MS";

  public static final String IMPACT = "Impact";

  public static final String LUCIDA_CONSOLE = "Lucida Console";

  public static final String PAPYRUS = "Papyrus";

  public static final String TAHOMA = "Tahoma";

  public static final String VERDANA = "Verdana";

  /**
   * Prevents that an instance of the {@link FontCodeCatalog} can be created.
   */
  private FontCodeCatalog() {
  }
}
