//package declaration
package ch.nolix.techapi.objectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseEngineAdapter<
	DE extends IDatabaseEngine<DE, D, T, C, PPT>,
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<PPT, ?>
>
extends IChangeSaver {
	
	//method declaration
	DE getRefDatabaseEngine();
}
