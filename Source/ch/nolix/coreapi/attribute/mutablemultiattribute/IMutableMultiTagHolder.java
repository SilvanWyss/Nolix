package ch.nolix.coreapi.attribute.mutablemultiattribute;

import ch.nolix.coreapi.attribute.multiattribute.IMultiTagHolder;

/**
 * A {@link IMutableMultiTagHolder} is a {@link IMultiTagHolder} whose tags can
 * be added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableMultiTagHolder extends IMultiTagHolder {
  /**
   * Adds the given tag to the current {@link IMutableMultiTagHolder} if the
   * current {@link IMutableMultiTagHolder} does not contain already such a tag.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank.
   */
  void addTag(String tag);

  /**
   * Removes the given tag from the current {@link IMutableMultiTagHolder} if the
   * current {@link IMutableMultiTagHolder} contains such a tag.
   * 
   * @param tag
   */
  void removeTag(String tag);

  /**
   * Removes all tags from the current {@link IMutableMultiTagHolder}.
   */
  void removeTags();
}
