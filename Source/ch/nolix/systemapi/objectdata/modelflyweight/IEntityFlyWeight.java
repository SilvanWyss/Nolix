/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.modelflyweight;

import ch.nolix.baseapi.generalstate.staterequest.VoidnessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IEntityFlyWeight extends VoidnessRequestable {
  /**
   * Notes that the Entity of the current {@link IEntityFlyWeight} has been
   * inserted into a database.
   */
  void noteInsertIntoDatabase();
}
