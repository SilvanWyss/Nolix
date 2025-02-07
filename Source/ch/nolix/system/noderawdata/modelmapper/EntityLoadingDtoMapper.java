package ch.nolix.system.noderawdata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.noderawdataapi.modelmapperapi.IEntityLoadingDtoMapper;
import ch.nolix.systemapi.noderawdataapi.nodesearcherapi.IEntityNodeSearcher;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-31
 */
public final class EntityLoadingDtoMapper implements IEntityLoadingDtoMapper {

  private static final IEntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final IContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityLoadingDto mapEntityNodeToEntityLoadingDto(
    final IMutableNode<?> entityNode,
    final TableSchemaViewDto tableView) {

    final var id = ENTITY_NODE_SEARCHER.getIdFromEntityNode(entityNode);
    final var saveStamp = ENTITY_NODE_SEARCHER.getSaveStampFromEntityNode(entityNode);
    final var contentFields = CONTENT_FIELD_DTO_MAPPER.mapEntityNodeToContentFieldDtos(entityNode, tableView);

    return new EntityLoadingDto(id, saveStamp, contentFields);
  }
}
