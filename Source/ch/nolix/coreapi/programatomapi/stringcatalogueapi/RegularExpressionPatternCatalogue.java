package ch.nolix.coreapi.programatomapi.stringcatalogueapi;

import java.util.regex.Pattern;

/**
 * Of the {@link RegularExpressionPatternCatalogue} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2023-01-09
 */
public final class RegularExpressionPatternCatalogue {

  public static final Pattern DOT_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalogue.DOT_PATTERN);

  public static final Pattern DOUBLE_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalogue.DOUBLE_PATTERN);

  /**
   * Prevents that an instance of the {@link RegularExpressionPatternCatalogue}
   * can be created.
   */
  private RegularExpressionPatternCatalogue() {
  }
}
