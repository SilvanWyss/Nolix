/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.HeaderHolder;

/**
 * A {@link MutableHeaderHolder} is a {@link HeaderHolder} whose header can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableHeaderHolder extends HeaderHolder {
  /**
   * Sets the header of the current {@link MutableHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null or blank
   */
  void setHeader(String header);
}
