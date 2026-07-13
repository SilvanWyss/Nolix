/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.json.JsonBoolean;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class JsonTrueBoolean //NOSONAR: JsonTrueBoolean is a singleton.
implements JsonBoolean {
  public static final JsonTrueBoolean INSTANCE = new JsonTrueBoolean();

  private JsonTrueBoolean() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean getBoolean() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.BOOLEAN;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedString() {
    return StringCatalog.LOWER_CASE_TRUE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> toNode() {
    return ImmutableNode.withHeader(StringCatalog.PASCAL_CASE_TRUE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return StringCatalog.LOWER_CASE_TRUE;
  }
}
