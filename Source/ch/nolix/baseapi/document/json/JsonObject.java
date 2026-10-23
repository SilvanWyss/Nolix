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
  /**
   * @return the {@link JsonNameValuePair}s of the current {@link JsonObject}
   */
  ExtendedIterable<JsonNameValuePair> getStoredNameValuePairs();

  /**
   * @return a new {@link JsonObject} with the {@link JsonNameValuePair}s of the
   *         current {@link JsonObject} ordered alphabetically by their names
   */
  JsonObject toAlphabeticallyOrdered();
}
