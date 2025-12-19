package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTextHolder} can have a text.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalTextHolder {
  /**
   * @return the text of the current {@link IOptionalTextHolder}.
   * @throws RuntimeException if the current {@link IOptionalTextHolder} does not
   *                          have a text.
   */
  String getText();

  /**
   * @return true if the current {@link IOptionalTextHolder} has a text, false
   *         otherwise.
   */
  boolean hasText();
}
