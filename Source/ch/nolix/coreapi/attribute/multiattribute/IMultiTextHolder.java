package ch.nolix.coreapi.attribute.multiattribute;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link IMultiTextHolder} can contain several texts.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 */
public interface IMultiTextHolder {
  /**
   * @return the texts of the current {@link IMultiTextHolder}.
   */
  IContainer<String> getTexts();
}
