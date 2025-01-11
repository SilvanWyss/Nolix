package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.rawdataapi.model.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.model.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

public final class LoadedEntityDtoMapper {

  private static final ContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  public EntityLoadingDto createLoadedEntityDtoFromEntityNode(
    final IMutableNode<?> entityNode,
    final TableViewDto tableView) {
    return //
    new EntityLoadingDto(
      getIdFromEntityNode(entityNode),
      getSaveStampFromEntityNode(entityNode),
      createContentFieldsFromEntityNode(entityNode, tableView));
  }

  private IContainer<ContentFieldDto<Object>> createContentFieldsFromEntityNode(
    final IMutableNode<?> entityNode,
    final TableViewDto tableView) {

    final ILinkedList<ContentFieldDto<Object>> contentFields = LinkedList.createEmpty();

    for (final var c : tableView.columnViews()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(c.oneBasedOrdinalIndex());
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
