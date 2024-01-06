//package declaration
package ch.nolix.core.commontypetool.commontypehelper;

import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.StringCatalogue;

//class
public final class GlobalBooleanHelper {

  //constructor
  private GlobalBooleanHelper() {
  }

  //static method
  @SuppressWarnings("all")
  public static String toString(final boolean pBoolean) {
    return (pBoolean ? StringCatalogue.TRUE_HEADER : StringCatalogue.FALSE_HEADER);
  }
}
