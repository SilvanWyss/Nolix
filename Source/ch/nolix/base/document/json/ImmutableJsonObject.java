/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.document.json;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.foundation.arrayiterableview.ArrayIterableView;
import ch.nolix.base.foundation.util.FunctionService;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.json.JsonNameValuePair;
import ch.nolix.baseapi.document.json.JsonObject;
import ch.nolix.baseapi.document.json.JsonValueType;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * @author Silvan Wyss
 */
public final class ImmutableJsonObject implements JsonObject {
  public static final ImmutableJsonObject EMPTY = new ImmutableJsonObject();

  private final ImmutableList<JsonNameValuePair> nameValuePairs;

  private final boolean alphabeticallyOrdered;

  /**
   * Creates a new empty {@link ImmutableJsonObject}.
   */
  private ImmutableJsonObject() {
    nameValuePairs = ImmutableList.createEmpty();
    alphabeticallyOrdered = true;
  }

  /**
   * Creates a new {@link ImmutableJsonObject} with the given nameValuePairs and
   * alphabeticallyOrderedTag.
   * 
   * @param nameValuePairs
   * @param alphabeticallyOrderedFlag
   * @throws RuntimeException if the given nameValuePairs is null
   * @throws RuntimeException if one of the given nameValuePairs is null
   * @throws RuntimeException if several of the given nameValuePairs have the same
   *                          name
   */
  private ImmutableJsonObject(final Iterable<JsonNameValuePair> nameValuePairs, boolean alphabeticallyOrderedFlag) {
    this.nameValuePairs = ImmutableList.fromIterable(nameValuePairs);
    this.alphabeticallyOrdered = alphabeticallyOrderedFlag;

    if (this.nameValuePairs.containsAny() && !alphabeticallyOrderedFlag) {
      final var orderedNames = //
      this.nameValuePairs.to(JsonNameValuePair::getName).toOrdered(FunctionService::getSelf);

      var previousName = orderedNames.getStoredFirst();

      for (final var n : orderedNames.getViewWithoutFirst()) {
        if (n.equals(previousName)) {
          throw //
          InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
            nameValuePairs,
            "name value paires",
            "contains at least 2 name value pairs with the same name");
        }

        previousName = n;
      }
    }
  }

  /**
   * @param nameValuePairs
   * @return a new {@link ImmutableJsonObject} with the given nameValuePairs
   * @throws RuntimeException if the given nameValuePairs is null
   * @throws RuntimeException if one of the given nameValuePairs is null
   * @throws RuntimeException if several of the given nameValuePairs have the same
   *                          name
   */
  public static ImmutableJsonObject withNameValuePairs(final Iterable<JsonNameValuePair> nameValuePairs) {
    return new ImmutableJsonObject(nameValuePairs, false);
  }

  /**
   * @param nameValuePairs
   * @return a new {@link ImmutableJsonObject} with the given nameValuePairs
   * @throws RuntimeException if the given nameValuePairs is null
   * @throws RuntimeException if one of the given nameValuePairs is null
   * @throws RuntimeException if several of the given nameValuePairs have the same
   *                          name
   */
  public static ImmutableJsonObject withNameValuePairs(final JsonNameValuePair... nameValuePairs) {
    final var nameValuePairsIterableView = ArrayIterableView.forArray(nameValuePairs);

    return new ImmutableJsonObject(nameValuePairsIterableView, false);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean formattedStringWillHaveMultipleLines() {
    return nameValuePairs.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<JsonNameValuePair> getStoredNameValuePairs() {
    return nameValuePairs;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JsonValueType getType() {
    return JsonValueType.OBJECT;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return nameValuePairs.isEmpty();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public JsonObject toAlphabeticallyOrdered() {
    if (alphabeticallyOrdered) {
      return this;
    }

    final var alphabeticallyOrderedNameValuePairs = nameValuePairs.toOrdered(p -> p.getName());

    return new ImmutableJsonObject(alphabeticallyOrderedNameValuePairs, true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toFormattedStringWithIndentationLevelAndIndentationSymbol(
    final int indentationLevel,
    final String indentationSymbol,
    final boolean startMultiLinerWithIndentation) {
    final var indentation = indentationSymbol.repeat(indentationLevel);

    if (nameValuePairs.isEmpty()) {
      return indentation + JsonStringPartCatalog.EMPTY_OBJECT_FLAT_STRING;
    }

    final var incrementedIndentationLevel = indentationLevel + 1;

    final var nameValuePairsFormattedStrings = //
    nameValuePairs.getViewOf(
      p -> p.toFormattedStringWithIndentationLevelAndIndentationSymbol(
        incrementedIndentationLevel,
        indentationSymbol,
        false));

    final var formattedDelimiter = StringCatalog.COMMA + StringCatalog.NEW_LINE;
    final var nameValuePairsFormattedString = nameValuePairsFormattedStrings.toStringWithDelimiter(formattedDelimiter);

    if (formattedStringWillHaveMultipleLines() && startMultiLinerWithIndentation) {
      return //
      StringCatalog.OPEN_BRACE + StringCatalog.NEW_LINE // first line
      + nameValuePairsFormattedString + StringCatalog.NEW_LINE // middle lines
      + indentation + StringCatalog.CLOSED_BRACE; // last line
    }

    return //
    StringCatalog.OPEN_BRACE + StringCatalog.NEW_LINE // first line
    + nameValuePairsFormattedString + StringCatalog.NEW_LINE // middle lines
    + indentation + StringCatalog.CLOSED_BRACE; // last line
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> toNode() {
    final var childNodes = nameValuePairs.getViewOf(JsonNameValuePair::toNode);

    return ImmutableNode.withChildNodes(childNodes);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    if (nameValuePairs.isEmpty()) {
      return JsonStringPartCatalog.EMPTY_OBJECT_FLAT_STRING;
    }

    final var nameValuePairsStrings = getStoredNameValuePairs().getViewOf(JsonNameValuePair::toString);

    final var nameValuePairsFlatString = //
    nameValuePairsStrings.toStringWithDelimiter(JsonStringPartCatalog.NAME_VALUE_PAIR_FLAT_DELIMITER);

    return //
    JsonStringPartCatalog.OBJECT_BEGIN_FLAT_STRING
    + nameValuePairsFlatString
    + JsonStringPartCatalog.OBJECT_END_FLAT_STRING;
  }
}
