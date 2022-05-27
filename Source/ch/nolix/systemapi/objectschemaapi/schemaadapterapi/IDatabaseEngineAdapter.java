//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

import ch.nolix.core.skilluniversalapi.IChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineAdapter<IMPL> extends IChangeSaver {
	
	//method declaration
	IDatabaseEngine<IMPL> getRefDatabaseEngine();
}
