//package declaration
package ch.nolix.systemapi.noderawschemaapi.databaseinitializingapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IDatabaseInitializer {

  //method declaration
  void initializeDatabase(IMutableNode<?> databaseNode);
}
