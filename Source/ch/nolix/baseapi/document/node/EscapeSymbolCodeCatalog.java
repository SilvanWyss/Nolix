/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.node;

/**
 * Of the {@link EscapeSymbolCodeCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class EscapeSymbolCodeCatalog {
  public static final String OPEN_BRACKET = "$O";

  public static final String CLOSED_BRACKET = "$C";

  public static final String COMMA = "$M";

  public static final String DOLLAR = "$D";

  public static final String SPACE = "$S";

  private EscapeSymbolCodeCatalog() {
  }
}
