/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TitleHolder;

/**
 * A {@link MutableTitleHolder} is a {@link TitleHolder} whose title can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableTitleHolder extends TitleHolder {
  /**
   * Sets the title of the current {@link MutableTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null or blank
   */
  void setTitle(String title);
}
