/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ITitleHolder} has a title.
 * 
 * @author Silvan Wyss
 */
public interface ITitleHolder {
  /**
   * @return the title of the current {@link ITitleHolder}.
   */
  String getTitle();

  /**
   * @return the title of the current {@link ITitleHolder} in quotes.
   */
  default String getTitleInQuotes() {
    return ("'" + getTitle() + "'");
  }
}
