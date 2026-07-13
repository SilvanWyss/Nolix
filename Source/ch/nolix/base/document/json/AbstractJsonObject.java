/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonNameValuePair;
import ch.nolix.baseapi.document.json.JsonObject;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.INode;

/**
 * @author Silvan Wyss
 */
public abstract class AbstractJsonObject implements JsonObject {
  /**
   * {@inheritDoc}
   */
  @Override
  public final JsonValueType getType() {
    return JsonValueType.OBJECT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final INode<?> toNode() {
    final var childNodes = getStoredNameValuePairs().getViewOf(JsonNameValuePair::toNode);

    return ImmutableNode.withChildNodes(childNodes);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String toString() {
    final var nameValuePairsFlatString = getNameValuePairsFlatString();

    return //
    JsonStringPartCatalog.OBJECT_BEGIN_FLAT_STRING
    + nameValuePairsFlatString
    + JsonStringPartCatalog.OBJECT_END_FLAT_STRING;
  }

  private String getNameValuePairsFlatString() {
    final var nameValuePairsStrings = getStoredNameValuePairs().getViewOf(JsonNameValuePair::toString);

    return nameValuePairsStrings.toStringWithDelimiter(JsonStringPartCatalog.NAME_VALUE_PAIR_FLAT_DELIMITER);
  }
}
