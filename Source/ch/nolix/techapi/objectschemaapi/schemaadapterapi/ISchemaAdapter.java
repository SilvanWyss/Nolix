//package declaration
package ch.nolix.techapi.objectschemaapi.schemaadapterapi;

import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ISchemaAdapter<
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends IChangeSaver, GroupCloseable {
	
	//method declaration
	D getRefDatabase();
}
