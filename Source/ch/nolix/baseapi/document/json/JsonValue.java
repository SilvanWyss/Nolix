/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.document.node.NodeRepresentable;

/**
 * @author Silvan Wyss
 */
public interface JsonValue extends NodeRepresentable {
  JsonValueType getType();
}
