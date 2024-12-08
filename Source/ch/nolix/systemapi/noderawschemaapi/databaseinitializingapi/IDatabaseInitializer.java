package ch.nolix.systemapi.noderawschemaapi.databaseinitializingapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IDatabaseInitializer {

  void initializeDatabase(IMutableNode<?> nodeDatabase);
}
