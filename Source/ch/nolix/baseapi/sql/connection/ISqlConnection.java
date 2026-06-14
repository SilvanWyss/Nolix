/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.sql.connection;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.sql.model.ISqlRecord;
import ch.nolix.baseapi.sql.sqlproperty.SqlDatabaseEngine;

/**
 * @author Silvan Wyss
 */
public interface ISqlConnection extends GroupCloseable {
  void executeStatement(String statement);

  void executeStatements(IWellOrderContainer<String> statements);

  void executeStatements(String... statements);

  SqlDatabaseEngine getDatabaseEngine();

  IWellOrderContainer<ISqlRecord> getRecordsFromQuery(String query);

  ISqlRecord getSingleRecordFromQuery(String query);
}
