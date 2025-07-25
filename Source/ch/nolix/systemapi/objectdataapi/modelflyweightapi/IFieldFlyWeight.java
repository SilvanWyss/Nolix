package ch.nolix.systemapi.objectdataapi.modelflyweightapi;

import ch.nolix.coreapi.state.staterequest.VoidnessRequestable;

public interface IFieldFlyWeight extends VoidnessRequestable {

  void noteUpdate();
}
