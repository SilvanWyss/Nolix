/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.element.valueproperty;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.element.baseproperty.Property;

/**
 * @author Silvan Wyss
 */
public interface BaseValueProperty extends EmptinessRequestable, NameHolder, Property {
  // This interface is a dedicated union of other interfaces.
}
