package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middata.model.FieldDto;
import ch.nolix.systemapi.middata.valuemapper.IValueMapper;
import ch.nolix.systemapi.midschemaview.model.ColumnViewDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.nodemiddata.modelmapper.IContentFieldDtoMapper;

/**
 * @author Silvan Wyss
 * @version 2021-10-20
 */
public final class ContentFieldDtoMapper implements IContentFieldDtoMapper {

  private static final IValueMapper VALUE_MAPPER = new ValueMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldDto mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final ColumnViewDto columnView) {

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

          final var valueAsString3 = contentFieldNode.getHeader();
          final var value3 = VALUE_MAPPER.mapStringToValue(valueAsString3, dataType);

          return new FieldDto(columnName, value3, null);
        }

        return new FieldDto(columnName, null, null);
      case OPTIONAL_REFERENCE, OPTIONAL_BACK_REFERENCE:

        if (contentFieldNode.hasHeader()) {

          final var valueAsString4 = contentFieldNode.getHeader();
          final var value4 = VALUE_MAPPER.mapStringToValue(valueAsString4, dataType);

          //TODO: Set additionalValue4
          final String additionalValue4 = null;

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
  public IContainer<FieldDto> mapEntityNodeToContentFieldDtos(
    final IMutableNode<?> entityNode,
    final TableViewDto tableView) {

    final ILinkedList<FieldDto> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnViews()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAtOneBasedIndex(c.oneBasedOrdinalIndex());
      final var contentFieldDto = mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }
}
