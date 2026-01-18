/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemiddata.nodemapper;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.system.midschemaview.modelsearcher.TableViewSearcher;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.midschema.databasestructure.FixDatabasePropertyCatalogue;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;
import ch.nolix.systemapi.midschemaview.modelsearcher.ITableViewSearcher;
import ch.nolix.systemapi.nodemiddata.nodemapper.IContentFieldNodeMapper;
import ch.nolix.systemapi.nodemiddata.nodemapper.IFieldNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class FieldNodeMapper implements IFieldNodeMapper {
  private static final ITableViewSearcher TABLE_VIEW_SEARCHER = new TableViewSearcher();

  private static final IContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> mapEntityCreationDtoToFieldNodes(
    final EntityCreationDto entityCreationDto,
    final long saveStamp,
    final TableViewDto tableView) {
    final var size = //
    FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + tableView.columnViews().getCount();

    final var attributes = new INode[size];
    final var entityId = entityCreationDto.id();

    attributes[0] = Node.withHeader(entityId);
    attributes[1] = Node.withHeader(saveStamp);
    attributes[2] = Node.EMPTY_NODE;
    attributes[3] = Node.EMPTY_NODE;

    for (final var f : entityCreationDto.contentFields()) {
      final var columnView = TABLE_VIEW_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var columnZeroBasedOrdinalIndex = columnView.oneBasedOrdinalIndex() - 1;

      attributes[columnZeroBasedOrdinalIndex] = CONTENT_FIELD_NODE_MAPPER.mapValueStringFieldDtoToContentFieldNode(f);
    }

    return ContainerView.forArray(attributes);
  }
}
