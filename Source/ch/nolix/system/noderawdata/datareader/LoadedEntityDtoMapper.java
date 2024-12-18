package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.system.sqlrawdata.datadto.LoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public final class LoadedEntityDtoMapper {

  private static final ContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  public ILoadedEntityDto createLoadedEntityDtoFromEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo) {
    return new LoadedEntityDto(
      getIdFromEntityNode(entityNode),
      getSaveStampFromEntityNode(entityNode),
      createContentFieldsFromEntityNode(entityNode, tableInfo));
  }

  private IContainer<ContentFieldDto<Object>> createContentFieldsFromEntityNode(
    final IMutableNode<?> entityNode,
    final ITableInfo tableInfo) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();
    for (final var ci : tableInfo.getColumnInfos()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(ci.getColumnIndexOnEntityNode());

      contentFields.addAtEnd(CONTENT_FIELD_DTO_MAPPER.createContentFieldDtoFromContentFieldNode(contentFieldNode, ci));
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
