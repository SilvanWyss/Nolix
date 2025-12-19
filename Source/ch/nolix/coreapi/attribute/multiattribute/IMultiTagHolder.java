package ch.nolix.coreapi.attribute.multiattribute;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link IMultiTagHolder} can contain several tags.
 * 
 * @author Silvan Wyss
 */
public interface IMultiTagHolder {
  /**
   * @return the tags of the current {@link IMultiTagHolder}.
   */
  IContainer<String> getTags();
}
