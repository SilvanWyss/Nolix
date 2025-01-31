package ch.nolix.systemapi.noderawdataapi.schemaviewloaderapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2025-01-24
 */
public interface ITableViewLoader {

  /**
   * @param nodeDatabase
   * @return the table views from the given nodeDatabase.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  IContainer<TableSchemaViewDto> loadTableViewsFromNodeDatabase(IMutableNode<?> nodeDatabase);
}
