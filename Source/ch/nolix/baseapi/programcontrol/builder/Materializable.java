/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.builder;

import ch.nolix.baseapi.generalstate.staterequest.MaterializationRequestable;

/**
 * @author Silvan Wyss
 * @param <M> the type of the objects a {@link Materializable} can be materialized
 *            to.
 */
public interface Materializable<M> extends MaterializationRequestable {
  /**
   * @return a new materialized object from the current {@link Materializable}
   */
  M toMaterialized();
}
