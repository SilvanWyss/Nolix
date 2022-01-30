//package declaration
package ch.nolix.system.objectschema.databaseschemaadapter;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.container.LinkedList;

//interface
public interface IEntitySetAdapter extends Named {
	
	//method declaration
	IColumnAdapter getColumnAdapter(Column column);
	
	//method declaration
	LinkedList<IColumnAdapter> getColumnAdapters();
	
	//method
	default LinkedList<Column> getColumns() {
		return getColumnAdapters().to(IColumnAdapter::toColumn);
	}
	
	//method declaration
	default EntitySet toEntitySet() {
		return new EntitySet(getName(), getColumns());
	}
}
