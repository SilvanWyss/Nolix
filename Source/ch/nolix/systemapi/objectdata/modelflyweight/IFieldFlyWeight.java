package ch.nolix.systemapi.objectdata.modelflyweight;

import ch.nolix.coreapi.state.staterequest.VoidnessRequestable;

public interface IFieldFlyWeight extends VoidnessRequestable {

  void noteUpdate();
}
