/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.json.JsonArray;
import ch.nolix.baseapi.document.json.JsonObject;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonArray implements JsonArray {
  public static final ImmutableJsonArray EMPTY = new ImmutableJsonArray();

  private final ImmutableList<JsonObject> objects;

  /**
   * Creates a new empty {@link ImmutableJsonArray}.
   */
  private ImmutableJsonArray() {
    objects = ImmutableList.createEmpty();
  }

  /**
   * Creates a new {@link ImmutableJsonArray} with the given objects.
   * 
   * @param objects
   * @throws RuntimeException if the given objects is null
   * @throws RuntimeException if one of the given objects is null
   */
  private ImmutableJsonArray(final Iterable<JsonObject> objects) {
    this.objects = ImmutableList.fromIterable(objects);
  }

  /**
   * @param objects
   * @return a new {@link ImmutableJsonArray} with the given objects
   * @throws RuntimeException if the given objects is null
   * @throws RuntimeException if one of the given objects is null
   */
  public static ImmutableJsonArray withObjects(final Iterable<JsonObject> objects) {
    return new ImmutableJsonArray(objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<JsonObject> getStoredObjects() {
    return objects;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.ARRAY;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol) {
    final var indentation = indentationSymbol.repeat(indentationLevel);
    if (objects.isEmpty()) {
      return indentation + JsonStringPartCatalog.EMPTY_ARRAY_FLAT_STRING;
    }

    final var incrementedIndentationLevel = indentationLevel + 1;
    final var incrementedIndentation = indentation + StringCatalog.TABULATOR;

    final var objectsFormattedStrings = //
    objects.getViewOf(o -> o.toFormattedStringWithIndentationLevel(incrementedIndentationLevel));

    final var formattedDelimiter = StringCatalog.COMMA + incrementedIndentation;
    final var objectsFormattedString = objectsFormattedStrings.toStringWithDelimiter(formattedDelimiter);

    return //
    indentation + StringCatalog.OPEN_SQUARE_BRACKET + StringCatalog.NEW_LINE // first line
    + objectsFormattedString + StringCatalog.NEW_LINE // middle lines
    + indentation + StringCatalog.CLOSED_SQUARE_BRACKET; // last line
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> toNode() {
    final var childNodes = objects.getViewOf(JsonObject::toNode);

    return ImmutableNode.withChildNodes(childNodes);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    if (objects.isEmpty()) {
      return JsonStringPartCatalog.EMPTY_ARRAY_FLAT_STRING;
    }

    final var objectsStrings = objects.getViewOf(JsonObject::toString);
    final var objectsString = objectsStrings.toStringWithDelimiter(JsonStringPartCatalog.ARRAY_FLAT_DELIMITER);

    return StringCatalog.OPEN_SQUARE_BRACKET + objectsString + StringCatalog.CLOSED_SQUARE_BRACKET;
  }
}
