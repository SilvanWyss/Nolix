//package declaration
package ch.nolix.core.commontype.commontypehelper;

import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;

//class
public final class GlobalBooleanHelper {

  // static method
  @SuppressWarnings("all")
  public static String toString(final boolean pBoolean) {
    return (pBoolean ? StringCatalogue.TRUE_HEADER : StringCatalogue.FALSE_HEADER);
  }

  // constructor
  private GlobalBooleanHelper() {
  }
}
