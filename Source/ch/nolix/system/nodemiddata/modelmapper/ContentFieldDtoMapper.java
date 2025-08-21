package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
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
    final ColumnViewDto columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return new FieldDto(columnInfo.name(), null);
    }

    if (!contentFieldNode.hasHeader()) {
      return new FieldDto(columnInfo.name(), null);
    }

    return //
    new FieldDto(
      columnInfo.name(),
      VALUE_MAPPER.mapStringToValue(contentFieldNode.getHeader(), columnInfo.dataType()));
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
