package ch.nolix.core.programatom.function;

import java.util.Objects;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;

/**
 * Of the {@link FunctionService} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public final class FunctionService {

  private static final double ZERO = 0.0;

  private static final double ONE = 1.0;

  private static final boolean FALSE = false;

  private static final boolean TRUE = true;

  /**
   * Prevents that an instance of the {@link FunctionService} can be
   * created.
   */
  private FunctionService() {
  }

  /**
   * @param object1
   * @param object2
   * @return true if the given object1 and the given object2 are equal.
   */
  public static boolean areEqual(final Object object1, final Object object2) {
    return Objects.equals(object1, object2);
  }

  /**
   * Does nothing.
   */
  public static void doNothing() {
    //Does nothing.
  }

  /**
   * @return false.
   */
  public static boolean getFalse() { //NOSONAR: This method returns a boolean constant.
    return FALSE;
  }

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

  /**
   * @return null.
   */
  public static Object getNull() {
    return null;
  }

  /**
   * @return 1.0.
   */
  public static double getOne() {
    return ONE;
  }

  /**
   * @param object
   * @param <O>    is the type of the given object.
   * @return the given object.
   */
  public static <O> O getSelf(final O object) {
    return object;
  }

  /**
   * @param object
   * @return the {@link String} representation of the given object.
   */
  public static String getStringRepresentationOf(final Object object) {

    if (object == null) {
      return StringCatalog.NULL_HEADER;
    }

    return object.toString();
  }

  /**
   * @return true.
   */
  public static boolean getTrue() { //NOSONAR: This method returns a boolean constant.
    return TRUE;
  }

  /**
   * @param object
   * @return the type of the given object.
   */
  public static Class<?> getTypeOf(final Object object) {
    return object.getClass();
  }

  /**
   * @return 0.0.
   */
  public static double getZero() {
    return ZERO;
  }

  /**
   * Does nothing.
   * 
   * @param object
   */
  public static void takeObjectAndDoNothing(final Object object) { //NOSONAR: The parameter is necessary.
    //Does nothing.
  }

  /**
   * @throws GeneralException
   */
  public static void throwException() {
    throw GeneralException.withErrorMessage("An error was provoked.");
  }
}
