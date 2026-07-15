/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.attribute.mandatoryattribute.ValueHolder;
import ch.nolix.baseapi.document.node.NodeRepresentable;

/**
 * @author Silvan Wyss
 */
public interface JsonNameValuePair extends NameHolder, NodeRepresentable, ValueHolder<JsonValue> {
  // This interface is a dedicated union of other interfaces.
}
