/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.exension;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;
import ch.nolix.systemapi.property.base.Property;

/**
 * @author Silvan Wyss
 * @param <E> the type of the extension of a {@link IExtension}.
 */
public interface IExtension<E extends IRespondingMutableElement<E>> extends Property {
  /**
   * @return the extension of the current {@link IExtension}.
   */
  E getStoredExtension();
}
