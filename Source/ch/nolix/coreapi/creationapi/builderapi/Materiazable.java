package ch.nolix.coreapi.creationapi.builderapi;

import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

public interface Materiazable<M> extends MaterializationRequestable {

  M toMaterialized();
}
