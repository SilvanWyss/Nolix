/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelflyweight;

import ch.nolix.coreapi.state.staterequest.VoidnessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IFieldFlyWeight extends VoidnessRequestable {
  void noteUpdate();
}
