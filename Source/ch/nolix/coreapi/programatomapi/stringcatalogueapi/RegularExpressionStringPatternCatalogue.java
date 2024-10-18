package ch.nolix.coreapi.programatomapi.stringcatalogueapi;

/**
 * Of the {@link RegularExpressionStringPatternCatalogue} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2023-07-23
 */
public final class RegularExpressionStringPatternCatalogue {

  public static final String DOT_PATTERN = ".";

  public static final String DOUBLE_PATTERN = "[-]?\\d+.\\d+";

  /**
   * Prevents that an instance of the
   * {@link RegularExpressionStringPatternCatalogue} can be created.
   */
  private RegularExpressionStringPatternCatalogue() {
  }
}
