/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.builder;

import ch.nolix.baseapi.generalstate.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 * @param <M> the type of the objects a {@link Materiazable} can be materialized
 *            to.
 */
public interface Materiazable<M> extends MaterializationRequestable {
  M toMaterialized();
}
