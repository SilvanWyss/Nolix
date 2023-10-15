//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineAdapter extends IChangeSaver {

  // method declaration
  IDatabaseEngine getStoredDatabaseEngine();
}
