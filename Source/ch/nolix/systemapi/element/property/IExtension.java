package ch.nolix.systemapi.element.property;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the actual extension of a {@link IExtension}.
 */
public interface IExtension<E extends IRespondingMutableElement<E>> extends IProperty {
  /**
   * @return the actual extension of the current {@link IExtension}.
   */
  E getStoredExtension();
}
