package ch.nolix.system.noderawdata.dtomapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.rawdataapi.dto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.dto.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;

public final class LoadedEntityDtoMapper {

  private static final ContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  public EntityLoadingDto createLoadedEntityDtoFromEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo) {
    return //
    new EntityLoadingDto(
      getIdFromEntityNode(entityNode),
      getSaveStampFromEntityNode(entityNode),
      createContentFieldsFromEntityNode(entityNode, tableInfo));
  }

  private IContainer<ContentFieldDto<Object>> createContentFieldsFromEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();

    for (final var c : tableInfo.getColumnInfos()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(c.getColumnIndexOnEntityNode());
      final var contentFieldDto = CONTENT_FIELD_DTO_MAPPER.mapContentFieldNodeToContentFieldDto(contentFieldNode, c);

      contentFields.addAtEnd(contentFieldDto);
    }

    return contentFields;
  }

  private String getIdFromEntityNode(final IMutableNode<?> entityNode) {

    final var idNode = ENTITY_NODE_SEARCHER.getStoredIdNodeFromEntityNode(entityNode);

    return idNode.getHeader();
  }

  private String getSaveStampFromEntityNode(IMutableNode<?> entityNode) {

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    return saveStampNode.getHeader();
  }
}
