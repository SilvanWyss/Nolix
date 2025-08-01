package ch.nolix.coreapi.objectcreation.builder;

import ch.nolix.coreapi.state.staterequest.MaterializationRequestable;

public interface Materiazable<M> extends MaterializationRequestable {

  M toMaterialized();
}
