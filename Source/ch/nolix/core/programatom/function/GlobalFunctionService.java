//package declaration
package ch.nolix.core.programatom.function;

//Java imports
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;

//class
/**
 * Of the {@link GlobalFunctionService} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public final class GlobalFunctionService {

  //constant
  private static final double ZERO = 0.0;

  //constant
  private static final double ONE = 1.0;

  //constant
  private static final boolean FALSE = false;

  //constant
  private static final boolean TRUE = true;

  //constructor
  /**
   * Prevents that an instance of the {@link GlobalFunctionService} can be
   * created.
   */
  private GlobalFunctionService() {
  }

  //static method
  /**
   * @param object1
   * @param object2
   * @return true if the given object1 and the given object2 are equal.
   */
  public static boolean areEqual(final Object object1, final Object object2) {
    return Objects.equals(object1, object2);
  }

  //static method
  /**
   * Does nothing.
   */
  public static void doNothing() {
    //Does nothing.
  }

  //static method
  /**
   * @return false.
   */
  public static boolean getFalse() { //NOSONAR: This method returns a boolean constant.
    return FALSE;
  }

  //static method
  /**
   * @param object
   * @return the hash code of the given object.
   */
  public static int getHashCode(final Object object) {

    if (object == null) {
      return 0;
    }

    return object.hashCode();
  }

  //static method
  /**
   * @return null.
   */
  public static Object getNull() {
    return null;
  }

  //static method
  /**
   * @return 1.0.
   */
  public static double getOne() {
    return ONE;
  }

  //static method
  /**
   * @param object
   * @param <O>    is the type of the given object.
   * @return the given object.
   */
  public static <O> O getSelf(final O object) {
    return object;
  }

  //static method
  /**
   * @param object
   * @return the {@link String} representation of the given object.
   */
  public static String getStringRepresentationOf(final Object object) {

    if (object == null) {
      return StringCatalogue.NULL_HEADER;
    }

    return object.toString();
  }

  //static method
  /**
   * @return true.
   */
  public static boolean getTrue() { //NOSONAR: This method returns a boolean constant.
    return TRUE;
  }

  //static method
  /**
   * @param object
   * @return the type of the given object.
   */
  public static Class<?> getTypeOf(final Object object) {
    return object.getClass();
  }

  //static method
  /**
   * @return 0.0.
   */
  public static double getZero() {
    return ZERO;
  }

  //static method
  /**
   * Does nothing.
   * 
   * @param object
   */
  public static void takeObjectAndDoNothing(final Object object) { //NOSONAR: The parameter is necessary.
    //Does nothing.
  }

  //static method
  /**
   * @throws GeneralException
   */
  public static void throwException() {
    throw GeneralException.withErrorMessage("An error was provoked.");
  }
}
