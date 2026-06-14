/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.sql.model.SqlRecord;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.sql.model.ISqlRecord;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionHelper {
  private SqlConnectionHelper() {
  }

  public static IWellOrderContainer<ISqlRecord> getRecordsFromStatement(
    final String query,
    final Statement statement)
  throws SQLException {
    try (final var resultSet = statement.executeQuery(query)) {
      return getRecordsFromResultSet(resultSet);
    }
  }

  private static IWellOrderContainer<ISqlRecord> getRecordsFromResultSet(final ResultSet resultSet)
  throws SQLException {
    final ILinkedList<ISqlRecord> sqlRecords = LinkedList.createEmpty();
    final var columnCount = resultSet.getMetaData().getColumnCount();
    var index = 1;

    while (resultSet.next()) {
      final List<String> entries = List.of();

      for (var i = 1; i <= columnCount; i++) {
        final var entry = resultSet.getString(i);

        entries.add(entry);
      }

      final var sqlRecord = SqlRecord.withOneBasedIndexAndValues(index, entries);

      sqlRecords.addAtEnd(sqlRecord);
      index++;
    }

    return sqlRecords;
  }
}
