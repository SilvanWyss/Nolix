/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.xml;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.ValueHolder;

/**
 * @author Silvan Wyss
 */
public interface IXmlAttribute extends NameHolder, ValueHolder<String> {
  //This interface is a dedicated union of other interfaces.
}
