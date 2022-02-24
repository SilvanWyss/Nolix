//package declaration
package ch.nolix.system.objectschema.schema;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDTO;

//class
final class RawSchemaReader {
	
	//attribute
	private final ISchemaReader internalRawSchemaReader;
	
	//constructor
	public RawSchemaReader(final ISchemaReader internalRawSchemaReader) {
		
		Validator.assertThat(internalRawSchemaReader).thatIsNamed("internal RawSchemaReader").isNotNull();
		
		this.internalRawSchemaReader = internalRawSchemaReader;
	}
	
	//method
	public boolean columnIsEmpty(final IColumn<SchemaImplementation> column) {
		return internalRawSchemaReader.columnIsEmpty(column.getParentTable().getName(), column.getName());
	}
	
	//method
	public LinkedList<IColumnDTO> loadColumnsOfTable(final ITable<SchemaImplementation> table) {
		return internalRawSchemaReader.loadColumnsByTableId(table.getId());
	}
	
	//method
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return internalRawSchemaReader.loadFlatTables();
	}
	
	//method
	public Time loadSchemaTimestamp() {
		return internalRawSchemaReader.loadSchemaTimestamp();
	}
}
