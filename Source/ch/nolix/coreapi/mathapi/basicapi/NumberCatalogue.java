package ch.nolix.coreapi.mathapi.basicapi;

/**
 * The {@link NumberCatalogue} provides numbers that are important mathematical
 * constants.
 * 
 * Of the {@link NumberCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-12-08
 */
public final class NumberCatalogue {

  public static final double EULERS_NUMBER = 2.718281828459045;

  public static final double GOLDEN_RATIO = 1.618033988749894;

  public static final double PI = 3.141592653589793;

  public static final double SQUARE_ROOT_OF_2 = 1.414213562373095;

  public static final double SQUARE_ROOT_OF_3 = 1.732050807568877;

  public static final double SQUARE_ROOT_OF_5 = 2.236067977499790;

  /**
   * Prevents that an instance of the {@link NumberCatalogue} can be created.
   */
  private NumberCatalogue() {
  }
}
