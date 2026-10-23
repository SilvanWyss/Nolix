/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface IXmlAttribute extends NameHolder {
  /**
   * @return the value of the current {@link IXmlAttribute}
   */
  String getValue();
}
