/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.modelmapper;

import ch.nolix.baseapi.document.node.IMutableNode;
import ch.nolix.system.nodemiddata.nodesearcher.EntityNodeSearcher;
import ch.nolix.systemapi.middata.model.EntityLoadingDto;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.nodemiddata.modelmapper.IEntityLoadingDtoMapper;

/**
 * @author Silvan Wyss
 */
public final class EntityLoadingDtoMapper implements IEntityLoadingDtoMapper {
  private static final EntityNodeSearcher ENTITY_NODE_SEARCHER = new EntityNodeSearcher();

  private static final ContentFieldDtoMapper CONTENT_FIELD_DTO_MAPPER = new ContentFieldDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public EntityLoadingDto mapEntityNodeToEntityLoadingDto(
    final IMutableNode<?> entityNode,
    final TableInfoDto tableView) {
    final var id = ENTITY_NODE_SEARCHER.getIdFromEntityNode(entityNode);
    final var saveStamp = ENTITY_NODE_SEARCHER.getSaveStampFromEntityNode(entityNode);
    final var contentFields = CONTENT_FIELD_DTO_MAPPER.mapEntityNodeToContentFieldDtos(entityNode, tableView);

    return new EntityLoadingDto(id, saveStamp, contentFields);
  }
}
