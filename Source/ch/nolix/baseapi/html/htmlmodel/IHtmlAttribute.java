/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.html.htmlmodel;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;

/**
 * @author Silvan Wyss
 */
public interface IHtmlAttribute extends NameHolder {
  String getValue();
}
