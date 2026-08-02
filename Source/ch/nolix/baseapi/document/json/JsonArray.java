/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.document.json;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.staterequest.EmptinessRequestable;

/**
 * @author Silvan Wyss
 */
public interface JsonArray extends EmptinessRequestable, JsonValue {
  ExtendedIterable<JsonObject> getStoredObjects();
}
