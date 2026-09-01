/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.midschemainfo.model.ColumnInfoDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.nodemiddata.modelmapper.IContentFieldDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {
  private static final ValueMapper VALUE_MAPPER = new ValueMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldDto mapContentFieldNodeToContentFieldDto(
    final Node<?> contentFieldNode,
    final ColumnInfoDto columnView) {
    final var columnName = columnView.name();
    final var fieldType = columnView.fieldType();
    final var dataType = columnView.dataType();

    switch (fieldType) {
      case VALUE_FIELD:
        final var valueAsString = contentFieldNode.getHeader();
        final var value = VALUE_MAPPER.mapStringToValue(valueAsString, dataType);

        return new FieldDto(columnName, value, null);
      case REFERENCE, BACK_REFERENCE:
        final var valueAsString2 = contentFieldNode.getStoredChildNodeAtOneBasedIndex(1).getHeader();
        final var value2 = VALUE_MAPPER.mapStringToValue(valueAsString2, dataType);
        final var additionalValue2 = contentFieldNode.getStoredChildNodeAtOneBasedIndex(2).getHeader();

        return new FieldDto(columnName, value2, additionalValue2);
      case OPTIONAL_VALUE_FIELD:
        if (contentFieldNode.hasHeader()) {
          final var optionalValueFieldValueAsString = contentFieldNode.getHeader();
          final var optionalValueFieldValue = VALUE_MAPPER.mapStringToValue(optionalValueFieldValueAsString, dataType);

          return new FieldDto(columnName, optionalValueFieldValue, null);
        }

        return new FieldDto(columnName, null, null);
      case OPTIONAL_REFERENCE, OPTIONAL_BACK_REFERENCE:
        if (contentFieldNode.containsChildNodes()) {
          final var valueAsString4 = contentFieldNode.getStoredChildNodeAtOneBasedIndex(1).getHeader();
          final var value4 = VALUE_MAPPER.mapStringToValue(valueAsString4, dataType);
          final var additionalValue4 = contentFieldNode.getStoredChildNodeAtOneBasedIndex(2).getHeader();

          return new FieldDto(columnName, value4, additionalValue4);
        }

        return new FieldDto(columnName, null, null);
      case MULTI_VALUE_FIELD, MULTI_REFERENCE, MULTI_BACK_REFERENCE:
        return new FieldDto(columnName, null, null);
      default:
        throw InvalidArgumentException.forArgument(fieldType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<FieldDto> mapEntityNodeToContentFieldDtos(
    final IMutableNode<?> entityNode,
    final TableInfoDto tableView) {
    final ILinkedList<FieldDto> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnViews()) {
      final var contentFieldNode = entityNode.getStoredChildNodeAtOneBasedIndex(c.oneBasedOrdinalIndex());
      final var contentFieldDto = mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }
}
