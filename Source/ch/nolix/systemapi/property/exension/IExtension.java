/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.exension;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the extension of a {@link IExtension}.
 */
public interface IExtension<E extends IRespondingMutableElement<E>> extends IProperty {
  /**
   * @return the extension of the current {@link IExtension}.
   */
  E getStoredExtension();
}
