//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseEngineAdapter<
	DE extends IDatabaseEngine<DE, D, T, C, PPT>,
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
>
extends IChangeSaver {
	
	//method declaration
	DE getRefDatabaseEngine();
}
