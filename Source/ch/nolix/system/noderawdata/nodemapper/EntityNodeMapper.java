package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IContentFieldNodeMapper;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;

/**
 * @author Silvan Wyss
 * @version 2021-10-19
 */
public final class EntityNodeMapper implements IEntityNodeMapper {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  private static final IContentFieldNodeMapper CONTENT_FIELD_NODE_MAPPER = new ContentFieldNodeMapper();

  @Override
  public INode<?> mapEntityCreationDtoToEntityNode(
    final EntityCreationDto newEntity,
    final TableSchemaViewDto tableView,
    final long saveStamp) {

    final var fieldNodes = mapEntityCreationDtoToFieldNodes(newEntity, saveStamp, tableView);

    return Node.withHeaderAndChildNodes(NodeHeaderCatalog.ENTITY, fieldNodes);
  }

  private IContainer<INode<?>> mapEntityCreationDtoToFieldNodes(
    final EntityCreationDto newEntity,
    final long saveStamp,
    final TableSchemaViewDto tableView) {

    final var size = //
    FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + tableView.columnSchemaViews().getCount();

    final var attributes = new INode[size];
    final var entityId = newEntity.id();

    attributes[0] = Node.withHeader(entityId);
    attributes[1] = Node.withHeader(saveStamp);
    attributes[2] = Node.EMPTY_NODE;
    attributes[3] = Node.EMPTY_NODE;

    for (final var f : newEntity.contentFields()) {

      final var columnView = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var index = columnView.oneBasedOrdinalIndex() - 1;

      attributes[index] = CONTENT_FIELD_NODE_MAPPER.mapStringContentFieldDtoToContentFieldNode(f);
    }

    return ContainerView.forArray(attributes);
  }
}
