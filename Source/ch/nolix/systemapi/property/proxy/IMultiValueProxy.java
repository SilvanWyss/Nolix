/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.proxy;

import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 */
public interface IMultiValueProxy extends EmptinessRequestable, INameHolder, IProperty {
  //This interface is a dedicated union of other interfaces.
}
