package ch.nolix.system.noderawdata.nodemapper;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.rawdata.schemaviewdtosearcher.TableViewDtoSearcher;
import ch.nolix.systemapi.noderawdataapi.nodemapperapi.IEntityNodeMapper;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.rawdataapi.model.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdtosearcherapi.ITableViewDtoSearcher;
import ch.nolix.systemapi.rawschemaapi.databasestructureapi.FixDatabasePropertyCatalogue;

/**
 * @author Silvan Wyss
 * @version 2021-10-19
 */
public final class EntityNodeMapper implements IEntityNodeMapper {

  private static final ITableViewDtoSearcher TABLE_VIEW_DTO_SEARCHER = new TableViewDtoSearcher();

  @Override
  public INode<?> mapEntityCreationDtoToEntityNode(
    final EntityCreationDto newEntity,
    final TableViewDto tableView,
    final long saveStamp) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.ENTITY,
      createAttributesFromNewEntityWithSaveStamp(newEntity, saveStamp, tableView));
  }

  private IContainer<Node> createAttributesFromNewEntityWithSaveStamp(
    final EntityCreationDto newEntity,
    final long saveStamp,
    final TableViewDto tableView) {

    final var size = FixDatabasePropertyCatalogue.NUMBER_OF_ENTITY_META_FIELDS + tableView.columnViews().getCount();
    final var attributes = new Node[size];

    attributes[0] = createIdAttributeFrom(newEntity);
    attributes[1] = createSaveStampAttribute(saveStamp);
    attributes[2] = Node.EMPTY_NODE;
    attributes[3] = Node.EMPTY_NODE;

    for (final var f : newEntity.contentFields()) {

      final var columnInfo = TABLE_VIEW_DTO_SEARCHER.getColumnViewByColumnName(tableView, f.columnName());
      final var index = columnInfo.oneBasedOrdinalIndex() - 1;

      final var valueAsString = f.optionalContent();
      if (valueAsString.isEmpty()) {
        attributes[index] = Node.EMPTY_NODE;
      } else {
        attributes[index] = Node.withHeader(valueAsString.get());
      }
    }

    return ContainerView.forArray(attributes);
  }

  private Node createIdAttributeFrom(final EntityCreationDto newEntity) {
    return Node.withHeader(newEntity.id());
  }

  private Node createSaveStampAttribute(final long saveStamp) {
    return Node.withHeader(saveStamp);
  }
}
