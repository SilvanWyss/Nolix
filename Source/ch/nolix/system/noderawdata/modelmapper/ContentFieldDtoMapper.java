package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.rawdata.valuemapper.ValueMapper;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.valuemapperapi.IValueMapper;

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
