//package declaration
package ch.nolix.core.commontype.commontypehelper;

//own imports
import ch.nolix.coreapi.commontypeapi.stringutilapi.StringCatalogue;

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
