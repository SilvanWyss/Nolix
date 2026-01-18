/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectschema.model;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectschema.modelvalidator.DatabaseValidator;
import ch.nolix.systemapi.midschema.adapter.ISchemaAdapter;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelvalidator.IDatabaseValidator;

/**
 * @author Silvan Wyss
 */
public final class Database extends AbstractSchemaObject implements IDatabase {
  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IDatabaseValidator DATABASE_VALIDATOR = new DatabaseValidator();

  private final String memberName;

  private boolean loadedTablesFromDatabase;

  private final ISchemaAdapter midSchemaAdapter;

  private LinkedList<ITable> tables = LinkedList.createEmpty();

  public Database(final String name, final ISchemaAdapter midSchemaAdapter) {
    DATABASE_VALIDATOR.assertCanSetGivenNameToDatabase(name);

    Validator.assertThat(midSchemaAdapter).thatIsNamed("mid schema adapter").isNotNull();

    memberName = name;
    this.midSchemaAdapter = midSchemaAdapter;

    setLoaded();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Database addTable(final ITable table) {
    DATABASE_VALIDATOR.assertCanAddGivenTable(this, table);
    DatabaseMutationExecutor.addTableToDatabase(this, (Table) table);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Database createTableWithName(final String name) {
    final var table = Table.withName(name);

    return addTable(table);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return memberName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<ITable> getStoredTables() {
    loadTablesFromDatabaseIfNeeded();

    return tables;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getTableCount() {
    if (!isConnectedWithRealDatabase() || hasLoadedTablesFromDatabase()) {
      return tables.getCount();
    }

    return midSchemaAdapter.getTableCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isConnectedWithRealDatabase() {
    return (midSchemaAdapter != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteClose() {
    //Does not call getStoredTables method to avoid that the tables need to be
    //loaded from the database.
    for (final var t : tables) {
      ((Table) t).close();
    }
  }

  void addTableAttribute(final ITable table) {
    tables.addAtEnd(table);
  }

  ISchemaAdapter getStoredMidSchemaAdapter() {
    DATABASE_OBJECT_VALIDATOR.assertIsConnectedWithRealDatabase(this);

    return midSchemaAdapter;
  }

  void removeTableAttribute(final Table table) {
    tables.removeStrictlyFirstOccurrenceOf(table);
  }

  private boolean hasLoadedTablesFromDatabase() {
    return loadedTablesFromDatabase;
  }

  private void loadTablesFromDatabase() {
    final var loadedTables = TableLoader.loadTables(getStoredMidSchemaAdapter());

    for (final var t : loadedTables) {
      t.setParentDatabase(this);
      tables.addAtEnd(t);
    }

    loadedTablesFromDatabase = true;
  }

  private void loadTablesFromDatabaseIfNeeded() {
    if (needsToLoadTablesFromDatabase()) {
      loadTablesFromDatabase();
    }
  }

  private boolean needsToLoadTablesFromDatabase() {
    return (isLoaded() && !hasLoadedTablesFromDatabase());
  }
}
