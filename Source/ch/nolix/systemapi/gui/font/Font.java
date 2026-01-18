/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.gui.font;

import ch.nolix.coreapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public enum Font {
  ARIAL(FontCodeCatalog.ARIAL),
  ARIAL_BLACK(FontCodeCatalog.ARIAL_BLACK),
  COMIC_SANS_MS(FontCodeCatalog.COMIC_SANS_MS),
  IMPACT(FontCodeCatalog.IMPACT),
  LUCIDA_CONSOLE(FontCodeCatalog.LUCIDA_CONSOLE),
  PAPYRUS(FontCodeCatalog.PAPYRUS),
  TAHOMA(FontCodeCatalog.TAHOMA),
  VERDANA(FontCodeCatalog.VERDANA);

  private final String code;

  /**
   * Creates a new {@link Font} with the given code.
   * 
   * @param code
   */
  Font(final String code) {
    this.code = code;
  }

  /**
   * @param specification
   * @return a new {@link Font} from the given specification.
   * @throws RuntimeException if the given specification does not represent a
   *                          {@link Font}.
   */
  public static Font fromSpecification(final INode<?> specification) {
    return valueOf(specification.getSingleChildNodeHeader());
  }

  /**
   * @return the code of the current {@link Font}.
   */
  public String getCode() {
    return code;
  }
}
