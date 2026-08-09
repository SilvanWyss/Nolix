/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.exension;

import ch.nolix.systemapi.element.mutableelement.RespondingMutableElement;
import ch.nolix.systemapi.property.base.Property;

/**
 * @author Silvan Wyss
 * @param <E> the type of the extension of a {@link IExtensionProperty}.
 */
public interface IExtensionProperty<E extends RespondingMutableElement<E>> extends Property {
  /**
   * @return the extension of the current {@link IExtensionProperty}.
   */
  E getStoredExtension();
}
