/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.document.base.FormattedStringRepresentable;
import ch.nolix.baseapi.document.node.NodeRepresentable;

/**
 * @author Silvan Wyss
 */
public interface JsonNameValuePair
extends FormattedStringRepresentable, NameHolder, NodeRepresentable {
  JsonValue getStoredValue();
}
