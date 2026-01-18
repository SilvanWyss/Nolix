/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.system.midschema.databaseinitializer.AbstractDatabaseInitializer;
import ch.nolix.systemapi.midschema.databaseinitializer.DatabaseState;
import ch.nolix.systemapi.nodemidschema.databaseinitializer.IDatabaseComponentCreator;
import ch.nolix.systemapi.nodemidschema.databaseinitializer.IDatabaseStateAnalyser;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.time.moment.ITime;

/**
 * @author Silvan Wyss
 */
public final class DatabaseInitializer extends AbstractDatabaseInitializer {
  private static final IDatabaseStateAnalyser DATABASE_STATE_ANALYSER = new DatabaseStateAnalyser();

  private static final IDatabaseComponentCreator DATABASE_COMPONENT_CREATOR = new DatabaseComponentCreator();

  private final String databaseName;

  private final IMutableNode<?> nodeDatabase;

  /**
   * Creates a new {@link DatabaseInitializer} for the given databaseName and
   * nodeDatabase.
   * 
   * @param databaseName
   * @param nodeDatabase
   * @throws RuntimeException if the given databaseName is null or blank.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  private DatabaseInitializer(final String databaseName, final IMutableNode<?> nodeDatabase) {
    Validator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    Validator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

    this.databaseName = databaseName;
    this.nodeDatabase = nodeDatabase;
  }

  /**
   * @param databaseName
   * @param nodeDatabase
   * @return a new {@link DatabaseInitializer} for the given nodeDatabase.
   * @throws RuntimeException if the given databaseName is null or blank.
   * @throws RuntimeException if the given nodeDatabase is null.
   */
  public static DatabaseInitializer forDatabaseNameAndNodeDatabase(
    final String databaseName,
    final IMutableNode<?> nodeDatabase) {
    return new DatabaseInitializer(databaseName, nodeDatabase);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DatabaseState getDatabaseState() {
    return DATABASE_STATE_ANALYSER.getStateOfNodeDatabase(nodeDatabase);
  }

  /**
   * 
   * Initializes the database with the given initialSchemaTimeStamp.
   * 
   * @param initialSchemaTimeStamp
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  @Override
  protected void initializeDatabaseWithInitialSchemaTimestamp(final ITime initialSchemaTimeStamp) {
    final var databasePropertiesNode = //
    DATABASE_COMPONENT_CREATOR.createDatabasePropertiesNodeWithDatabaseNameAndInitialSchemaTimeStamp(
      databaseName,
      initialSchemaTimeStamp);

    final var entityIndexesNodes = DATABASE_COMPONENT_CREATOR.createEntityIndexesNode();

    nodeDatabase
      .setHeader(NodeHeaderCatalog.DATABASE)
      .addChildNode(databasePropertiesNode, entityIndexesNodes);
  }
}
