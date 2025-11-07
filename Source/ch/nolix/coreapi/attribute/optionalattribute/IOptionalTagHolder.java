package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTagHolder} can have a tag.
 * 
 * @author Silvan Wyss
 * @version 2025-11-07
 */
public interface IOptionalTagHolder {
  /**
   * @return the tag of the current {@link IOptionalTagHolder}.
   * @throws RuntimeException if the current {@link IOptionalTagHolder} does not
   *                          have a tag.
   */
  String getTag();

  /**
   * @return true if the current {@link IOptionalTagHolder} has a tag, false
   *         otherwise.
   */
  boolean hasTag();
}
