package ch.nolix.coreapi.creation.builder;

import ch.nolix.coreapi.stateapi.staterequestapi.MaterializationRequestable;

public interface Materiazable<M> extends MaterializationRequestable {

  M toMaterialized();
}
