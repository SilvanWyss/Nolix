/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.htmlmodel;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface IHtmlAttribute extends NameHolder {
  /**
   * @return the value of the current {@link IHtmlAttribute}
   */
  String getValue();
}
