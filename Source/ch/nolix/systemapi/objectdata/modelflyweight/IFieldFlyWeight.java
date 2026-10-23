/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelflyweight;

import ch.nolix.baseapi.generalstate.staterequest.VoidnessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IFieldFlyWeight extends VoidnessRequestable {
  /**
   * Notes that the Field of the current {@link IFieldFlyWeight} has been updated.
   */
  void noteUpdate();
}
