/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.datamodel.cardinality.Cardinality;
import ch.nolix.baseapi.datamodel.cardinality.ICardinalityHolder;

/**
 * A {@link FluentMutableCardinalityHolder} is a {@link ICardinalityHolder}
 * whose cardinality can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableCardinalityHolder}
 */
public interface FluentMutableCardinalityHolder<H extends FluentMutableCardinalityHolder<H>>
extends ICardinalityHolder {
  /**
   * Sets the cardinality of the current {@link FluentMutableCardinalityHolder}.
   * 
   * @param cardinality
   * @return the current {@link FluentMutableCardinalityHolder}
   * @throws RuntimeException if the given cardinality is null
   */
  H setCardinality(Cardinality cardinality);
}
