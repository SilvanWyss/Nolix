//package declaration
package ch.nolix.core.commontype.commontypeconstant;

//own imports
import java.util.regex.Pattern;

//class
/**
 * Of the {@link RegularExpressionPatternCatalogue} an instance cannot be
 * created.
 * 
 * @author Silvan Wyss
 * @date 2023-01-09
 */
public final class RegularExpressionPatternCatalogue {

  // constant
  public static final Pattern DOT_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalogue.DOT_PATTERN);

  // constant
  public static final Pattern DOUBLE_PATTERN = Pattern.compile(RegularExpressionStringPatternCatalogue.DOUBLE_PATTERN);

  // constructor
  /**
   * Prevents that an instance of the {@link RegularExpressionPatternCatalogue}
   * can be created.
   */
  private RegularExpressionPatternCatalogue() {
  }
}
