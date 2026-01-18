package ch.nolix.core.sql.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.sql.model.SqlRecord;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.sql.model.ISqlRecord;

/**
 * @author Silvan Wyss
 */
public final class SqlConnectionHelper {
  private SqlConnectionHelper() {
  }

  public static IContainer<ISqlRecord> getRecordsFromStatement(
    final String query,
    final Statement statement)
  throws SQLException {
    try (final var resultSet = statement.executeQuery(query)) {
      return getRecordsFromResultSet(resultSet);
    }
  }

  private static IContainer<ISqlRecord> getRecordsFromResultSet(final ResultSet resultSet)
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
