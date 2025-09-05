package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTextHolder} can have a text.
 * 
 * @author Silvan Wyss
 * @version 2023-02-03
 */
public interface IOptionalTextHolder {
  /**
   * @return the text of the current {@link IOptionalTextHolder}.
   * @throws RuntimeException if the current {@link IOptionalTextHolder} does not
   *                          have a text.
   */
  String getText();

  /**
   * @return true if the current {@link IOptionalTextHolder} has a text.
   */
  boolean hasText();
}
