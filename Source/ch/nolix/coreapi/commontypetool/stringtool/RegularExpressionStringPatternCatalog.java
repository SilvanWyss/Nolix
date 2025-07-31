package ch.nolix.coreapi.commontypetool.stringtool;

import java.util.regex.Pattern;

/**
 * Of the {@link RegularExpressionStringPatternCatalog} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @version 2023-07-23
 */
public final class RegularExpressionStringPatternCatalog {

  public static final String DOLLAR_PATTERN = Pattern.quote(StringCatalog.DOLLAR);

  public static final String DOT_PATTERN = ".";

  public static final String DOUBLE_PATTERN = "[-]?\\d+.\\d+";

  /**
   * Prevents that an instance of the
   * {@link RegularExpressionStringPatternCatalog} can be created.
   */
  private RegularExpressionStringPatternCatalog() {
  }
}
