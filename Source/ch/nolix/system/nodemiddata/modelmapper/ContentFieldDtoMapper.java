package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middataapi.modelapi.ObjectValueFieldDto;
import ch.nolix.systemapi.middataapi.valuemapperapi.IValueMapper;
import ch.nolix.systemapi.midschemaviewapi.modelapi.ColumnViewDto;
import ch.nolix.systemapi.midschemaviewapi.modelapi.TableViewDto;
import ch.nolix.systemapi.nodemiddataapi.modelmapperapi.IContentFieldDtoMapper;

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
  public ObjectValueFieldDto mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final ColumnViewDto columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return new ObjectValueFieldDto(columnInfo.name(), null);
    }

    if (!contentFieldNode.hasHeader()) {
      return new ObjectValueFieldDto(columnInfo.name(), null);
    }

    return //
    new ObjectValueFieldDto(
      columnInfo.name(),
      VALUE_MAPPER.mapStringToValue(contentFieldNode.getHeader(), columnInfo.dataType()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ObjectValueFieldDto> mapEntityNodeToContentFieldDtos(
    final IMutableNode<?> entityNode,
    final TableViewDto tableView) {

    final ILinkedList<ObjectValueFieldDto> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnViews()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAtOneBasedIndex(c.oneBasedOrdinalIndex());
      final var contentFieldDto = mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }
}
