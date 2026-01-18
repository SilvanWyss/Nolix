/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.objectcreation.builder;

import ch.nolix.coreapi.state.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 * @param <M> is the type of the objects a {@link Materiazable} can be
 *            materialized to.
 */
public interface Materiazable<M> extends MaterializationRequestable {
  M toMaterialized();
}
