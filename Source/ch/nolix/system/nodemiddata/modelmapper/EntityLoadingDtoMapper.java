package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.nodemiddata.modelmapper.IContentFieldDtoMapper;
import ch.nolix.systemapi.nodemiddata.modelmapper.IEntityLoadingDtoMapper;
import ch.nolix.systemapi.nodemiddata.nodesearcher.IEntityNodeSearcher;

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
