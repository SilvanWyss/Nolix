/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.extensionproperty;

import ch.nolix.systemapi.baseproperty.Property;
import ch.nolix.systemapi.element.mutableelement.RespondingMutableElement;

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
