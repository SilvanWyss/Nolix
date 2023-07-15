//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.coreapi.functionapi.mutationapi.IChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineAdapter extends IChangeSaver {
	
	//method declaration
	IDatabaseEngine getStoredDatabaseEngine();
}
