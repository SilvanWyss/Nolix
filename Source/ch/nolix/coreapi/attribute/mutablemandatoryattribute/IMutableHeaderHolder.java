/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IHeaderHolder;

/**
 * A {@link IMutableHeaderHolder} is a {@link IHeaderHolder} whose header can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableHeaderHolder extends IHeaderHolder {
  /**
   * Sets the header of the current {@link IMutableHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
