/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.property.value;

import ch.nolix.baseapi.state.staterequest.EmptinessRequestable;
import ch.nolix.baseapi.state.staterequest.MutabilityRequestable;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 */
public interface IBaseValueProperty extends EmptinessRequestable, IProperty, MutabilityRequestable {
  //This interface is just an union of other interfaces.
}
