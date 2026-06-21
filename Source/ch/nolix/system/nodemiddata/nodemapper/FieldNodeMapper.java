/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.system.midschemainfo.modelsearcher.TableInfoSearcher;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.midschemainfo.modelsearcher.ITableInfoSearcher;
import ch.nolix.systemapi.nodemiddata.nodemapper.IContentFieldNodeMapper;
import ch.nolix.systemapi.nodemiddata.nodemapper.IFieldNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class FieldNodeMapper implements IFieldNodeMapper {
  private static final ITableInfoSearcher TABLE_VIEW_SEARCHER = new TableInfoSearcher();

  private static final IContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<INode<?>> mapEntityCreationDtoToFieldNodes(
    final EntityCreationDto entityCreationDto,
    final long saveStamp,
    final TableInfoDto tableView) {
    final var size = //
    FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + tableView.columnViews().getCount();

    final var attributes = new INode[size];
    final var entityId = entityCreationDto.id();

    attributes[0] = ImmutableNode.withHeader(entityId);
    attributes[1] = ImmutableNode.withHeader(saveStamp);
    attributes[2] = ImmutableNode.EMPTY_NODE;
    attributes[3] = ImmutableNode.EMPTY_NODE;

    for (final var f : entityCreationDto.contentFields()) {
      final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var columnZeroBasedOrdinalIndex = columnView.oneBasedOrdinalIndex() - 1;

      attributes[columnZeroBasedOrdinalIndex] = CONTENT_FIELD_NODE_MAPPER.mapValueStringFieldDtoToContentFieldNode(f);
    }

    return ContainerView.forArray(attributes);
  }
}
