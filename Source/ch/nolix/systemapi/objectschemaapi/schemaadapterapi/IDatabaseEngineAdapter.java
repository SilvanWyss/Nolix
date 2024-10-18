package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

public interface IDatabaseEngineAdapter extends IChangeSaver {

  IDatabaseEngine getStoredDatabaseEngine();
}
