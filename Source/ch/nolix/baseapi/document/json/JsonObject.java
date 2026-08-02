/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;

/**
 * @author Silvan Wyss
 */
public interface JsonObject extends EmptinessRequestable, JsonValue {
  ExtendedIterable<JsonNameValuePair> getStoredNameValuePairs();

  JsonObject toAlphabeticallyOrdered();
}
