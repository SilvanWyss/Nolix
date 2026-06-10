/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITitleHolder;

/**
 * A {@link IMutableTitleHolder} is a {@link ITitleHolder} whose title can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableTitleHolder extends ITitleHolder {
  /**
   * Sets the title of the current {@link IMutableTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null or blank
   */
  void setTitle(String title);
}
