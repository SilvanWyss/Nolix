package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.schemaviewapi.TableViewDto;
import ch.nolix.systemapi.nodemiddataapi.modelmapperapi.IContentFieldDtoMapper;
import ch.nolix.systemapi.nodemiddataapi.modelmapperapi.IEntityLoadingDtoMapper;
import ch.nolix.systemapi.nodemiddataapi.nodesearcherapi.IEntityNodeSearcher;

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
    final TableViewDto tableView) {

    final var id = ENTITY_NODE_SEARCHER.getIdFromEntityNode(entityNode);
    final var saveStamp = ENTITY_NODE_SEARCHER.getSaveStampFromEntityNode(entityNode);
    final var contentFields = CONTENT_FIELD_DTO_MAPPER.mapEntityNodeToContentFieldDtos(entityNode, tableView);

    return new EntityLoadingDto(id, saveStamp, contentFields);
  }
}
