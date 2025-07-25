package ch.nolix.coreapi.commontypetool.doubletool;

/**
 * A {@link IDoubleTool} provides methods to handle doubles.
 * 
 * @author Silvan Wyss
 * @version 2022-07-22
 */
public interface IDoubleTool {

  /**
   * @param value
   * @return a {@link String} representation of the given value. The
   *         {@link String} representation will have the following properties:
   * 
   *         -The separator symbol of the decimal places is a dot.
   * 
   *         -The decimal places and the separator symbol are shown only when
   *         there exist decimal places.
   */
  String toString(double value);
}
