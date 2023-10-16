//package declaration
package ch.nolix.core.math.base;

//class
/**
 * Of the {@link NumberCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-12-08
 */
public final class NumberCatalogue {

  //constant
  public static final double EULERS_NUMBER = Math.E;

  //constant
  public static final double LOGARITHMUS_DECIMALIS_OF_2 = Math.log(2.0);

  //constant
  public static final double PI = Math.PI;

  //constant
  public static final double SQUARE_ROOT_OF_2 = Math.sqrt(2.0);

  //constant
  public static final double SQUARE_ROOT_OF_3 = Math.sqrt(3.0);

  //constant
  public static final double SQUARE_ROOT_OF_5 = Math.sqrt(5.0);

  //constructor
  /**
   * Prevents that an instance of the {@link NumberCatalogue} can be created.
   */
  private NumberCatalogue() {
  }
}
