package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.middata.valuemapper.ValueMapper;
import ch.nolix.systemapi.middataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.middataapi.valuemapperapi.IValueMapper;
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
  public ContentFieldDto<Object> mapContentFieldNodeToContentFieldDto(
    final INode<?> contentFieldNode,
    final ColumnSchemaViewDto columnInfo) {

    if (contentFieldNode.containsChildNodes()) {
      return new ContentFieldDto<>(columnInfo.name(), null);
    }

    if (!contentFieldNode.hasHeader()) {
      return new ContentFieldDto<>(columnInfo.name(), null);
    }

    return new ContentFieldDto<>(
      columnInfo.name(),
      VALUE_MAPPER.mapStringToValue(contentFieldNode.getHeader(), columnInfo.dataType()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ContentFieldDto<Object>> mapEntityNodeToContentFieldDtos(
    final IMutableNode<?> entityNode,
    final TableSchemaViewDto tableView) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnSchemaViews()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(c.oneBasedOrdinalIndex());
      final var contentFieldDto = mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }
}
