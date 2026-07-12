/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface JsonObject extends JsonValue {
  ExtendedIterable<JsonNameValuePair> getStoredNameValuePairs();

  JsonObject toAlphabeticallyOrdered();
}
