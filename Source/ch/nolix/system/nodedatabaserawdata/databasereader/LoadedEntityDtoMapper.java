//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.structure.EntityNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.databasedto.LoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public final class LoadedEntityDtoMapper {

  // constant
  private static final ContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  // constant
  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  // method
  public ILoadedEntityDto createLoadedEntityDtoFromEntityNode(
      final IMutableNode<?> entityNode,
      final ITableInfo tableInfo) {
    return new LoadedEntityDto(
        getIdFromEntityNode(entityNode),
        getSaveStampFromEntityNode(entityNode),
        createContentFieldsFromEntityNode(entityNode, tableInfo));
  }

  // method
  private IContainer<ILoadedContentFieldDto> createContentFieldsFromEntityNode(
      final IMutableNode<?> entityNode,
      final ITableInfo tableInfo) {

    final var contentFields = new LinkedList<ILoadedContentFieldDto>();
    for (final var ci : tableInfo.getColumnInfos()) {

      final var contentFieldNode = entityNode.getStoredChildNodeAt1BasedIndex(ci.getColumnIndexOnEntityNode());

      contentFields.addAtEnd(CONTENT_FIELD_DTO_MAPPER.createContentFieldDtoFromContentFieldNode(contentFieldNode, ci));
    }

    return contentFields;
  }

  // method
  private String getIdFromEntityNode(final IMutableNode<?> entityNode) {

    final var idNode = ENTITY_NODE_SEARCHER.getStoredIdNodeFromEntityNode(entityNode);

    return idNode.getHeader();
  }

  // method
  private String getSaveStampFromEntityNode(IMutableNode<?> entityNode) {

    final var saveStampNode = ENTITY_NODE_SEARCHER.getStoredSaveStampNodeFromEntityNode(entityNode);

    return saveStampNode.getHeader();
  }
}
