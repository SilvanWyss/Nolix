package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ITagHolder} has a tag.
 * 
 * @author Silvan Wyss
 * @version 2025-11-07
 */
public interface ITagHolder {
  /**
   * @return the tag of the current {@link ITagHolder}.
   */
  String getTag();
}
