package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;
import ch.nolix.systemapi.rawdataapi.modelapi.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public final class EntityLoadingDtoMapper {

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final IContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  public EntityLoadingDto mapEntiyNodeToEntityLoadingDto(
    final IMutableNode<?> entityNode,
    final TableSchemaViewDto tableView) {

    final var id = ENTITY_NODE_SEARCHER.getIdFromEntityNode(entityNode);
    final var saveStamp = ENTITY_NODE_SEARCHER.getSaveStampFromEntityNode(entityNode);
    final var contentFields = createContentFieldsFromEntityNode(entityNode, tableView);

    return new EntityLoadingDto(id, saveStamp, contentFields);
  }

  private IContainer<ContentFieldDto<Object>> createContentFieldsFromEntityNode(
    final IMutableNode<?> entityNode,
    final TableSchemaViewDto tableView) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnSchemaViews()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(c.oneBasedOrdinalIndex());
      final var contentFieldDto = CONTENT_FIELD_DTO_MAPPER.mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }
}
