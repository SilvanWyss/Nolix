//package declaration
package ch.nolix.techapi.objectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IDatabaseEngineAdapter<IMPL> extends IChangeSaver {
	
	//method declaration
	IDatabaseEngine<IMPL> getRefDatabaseEngine();
}
