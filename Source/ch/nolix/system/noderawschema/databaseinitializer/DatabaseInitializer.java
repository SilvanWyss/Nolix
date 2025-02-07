package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.rawschema.databaseinitializer.AbstractDatabaseInitializer;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseComponentCreator;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseStateAnalyser;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
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

    GlobalValidator.assertThat(databaseName).thatIsNamed("database name").isNotBlank();
    GlobalValidator.assertThat(nodeDatabase).thatIsNamed("node database").isNotNull();

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
    DATABASE_COMPONENT_CREATOR.createDatabasePropertiesNodeWithInitialSchemaTimeStamp(
      databaseName,
      initialSchemaTimeStamp);

    final var entityIndexesNodes = DATABASE_COMPONENT_CREATOR.createEntityIndexesNode();

    nodeDatabase
      .setHeader(NodeHeaderCatalog.DATABASE)
      .addChildNode(databasePropertiesNode, entityIndexesNodes);
  }
}
