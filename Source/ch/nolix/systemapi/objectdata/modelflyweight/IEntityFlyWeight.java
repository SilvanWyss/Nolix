package ch.nolix.systemapi.objectdata.modelflyweight;

import ch.nolix.coreapi.state.staterequest.VoidnessRequestable;

/**
 * @author Silvan Wyss
 */
public interface IEntityFlyWeight extends VoidnessRequestable {
  void noteInsertIntoDatabase();
}
