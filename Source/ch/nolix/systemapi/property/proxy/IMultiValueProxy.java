/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.proxy;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.property.base.Property;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueProxy extends EmptinessRequestable, NameHolder, Property {
  // This interface is a dedicated union of other interfaces.
}
