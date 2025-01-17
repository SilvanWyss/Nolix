package ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public interface IDatabaseComponentCreator {

  /**
   * @param initialSchemaTimeStamp
   * @return a new database properties node with the given initialSchemaTimeStamp.
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  IMutableNode<?> createDatabasePropertiesNodeWithInitialSchemaTimeStamp(ITime initialSchemaTimeStamp);

  /**
   * @return a new entity indexes node.
   */
  IMutableNode<?> createEntityIndexesNode();

  /**
   * @param initialSchemaTimeStamp
   * @return a new schema timestamp node with the given initialSchemaTimeStamp.
   * @throws RuntimeException if the given initialSchemaTimeStamp is null.
   */
  IMutableNode<?> createSchemaTimestampNodeWithInitialSchemaTimeStamp(ITime initialSchemaTimeStamp);
}
