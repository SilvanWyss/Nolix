/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.system.midschemainfo.modelsearcher.TableInfoSearcher;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemainfo.model.TableInfoDto;
import ch.nolix.systemapi.nodemiddata.nodemapper.IFieldNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class FieldNodeMapper implements IFieldNodeMapper {
  private static final TableInfoSearcher TABLE_VIEW_SEARCHER = new TableInfoSearcher();

  private static final ContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> mapEntityCreationDtoToFieldNodes(
    final EntityCreationDto entityCreationDto,
    final long saveStamp,
    final TableInfoDto tableView) {
    final var size = //
    FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + tableView.columnViews().getCount();

    final var attributes = new Node[size];
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

    return ExtendedIterableView.forArray(attributes);
  }
}
