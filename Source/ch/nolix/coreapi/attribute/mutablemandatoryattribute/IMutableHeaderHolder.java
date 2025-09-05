package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.IHeaderHolder;

/**
 * A {@link IMutableHeaderHolder} is a {@link IHeaderHolder} whose header can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-01-29
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
