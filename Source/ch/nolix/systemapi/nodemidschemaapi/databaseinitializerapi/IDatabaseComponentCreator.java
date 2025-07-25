package ch.nolix.systemapi.nodemidschemaapi.databaseinitializerapi;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IDatabaseComponentCreator {

  /**
   * @param databaseName
   * @param initialSchemaTimeStamp
   * @return a new database properties node with the given name and
   *         initialSchemaTimeStamp.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  IMutableNode<?> createDatabasePropertiesNodeWithDatabaseNameAndInitialSchemaTimeStamp(
    String databaseName,
    ITime initialSchemaTimeStamp);

  /**
   * @return a new entity indexes node.
   */
  IMutableNode<?> createEntityIndexesNode();

  /**
   * @param name
   * @return a new name node with the given name.
   * @throws RuntimeException if the given name is null or blank.
   */
  IMutableNode<?> createNameNodeWithName(String name);

  /**
   * @param initialSchemaTimeStamp
   * @return a new schema timestamp node with the given initialSchemaTimeStamp.
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  IMutableNode<?> createSchemaTimestampNodeWithInitialSchemaTimeStamp(ITime initialSchemaTimeStamp);
}
