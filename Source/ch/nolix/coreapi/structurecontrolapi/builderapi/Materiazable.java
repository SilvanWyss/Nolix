package ch.nolix.coreapi.structurecontrolapi.builderapi;

import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

public interface Materiazable<M> extends MaterializationRequestable {

  M toMaterialized();
}
