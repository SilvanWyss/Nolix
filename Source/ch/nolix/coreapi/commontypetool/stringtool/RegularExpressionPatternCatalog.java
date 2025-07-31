package ch.nolix.coreapi.commontypetool.stringtool;

import java.util.regex.Pattern;

/**
 * Of the {@link RegularExpressionPatternCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2023-01-09
 */
public final class RegularExpressionPatternCatalog {

  public static final Pattern DOLLAR_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalog.DOLLAR_PATTERN);

  public static final Pattern DOT_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalog.DOT_PATTERN);

  public static final Pattern DOUBLE_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalog.DOUBLE_PATTERN);

  /**
   * Prevents that an instance of the {@link RegularExpressionPatternCatalog} can
   * be created.
   */
  private RegularExpressionPatternCatalog() {
  }
}
