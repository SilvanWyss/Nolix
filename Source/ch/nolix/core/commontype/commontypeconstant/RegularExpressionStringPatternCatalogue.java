//package declaration
package ch.nolix.core.commontype.commontypeconstant;

//class
/**
 * Of the {@link RegularExpressionStringPatternCatalogue} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @date 2023-07-23
 */
public final class RegularExpressionStringPatternCatalogue {

  // constant
  public static final String DOT_PATTERN = ".";

  // constant
  public static final String DOUBLE_PATTERN = "\\d+.\\d+";

  // constructor
  /**
   * Prevents that an instance of the
   * {@link RegularExpressionStringPatternCatalogue} can be created.
   */
  private RegularExpressionStringPatternCatalogue() {
  }
}
