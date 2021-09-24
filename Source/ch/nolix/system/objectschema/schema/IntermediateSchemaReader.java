//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//class
final class IntermediateSchemaReader {
	
	//attribute
	private final ISchemaReader internalIntermediateSchemaReader;
	
	//constructor
	public IntermediateSchemaReader(final ISchemaReader internalIntermediateSchemaReader) {
		
		Validator
		.assertThat(internalIntermediateSchemaReader)
		.thatIsNamed("internal intermediate schema reader")
		.isNotNull();
		
		this.internalIntermediateSchemaReader = internalIntermediateSchemaReader;
	}
	
	//method
	public boolean columnIsEmpty(final IColumn<?, ?> column) {
		return internalIntermediateSchemaReader.columnIsEmpty(column.getParentTable().getName(), column.getHeader());
	}
	
	//method
	public LinkedList<IColumnDTO> loadColumnsOfTable(final ITable<?, ?, ?> table) {
		return internalIntermediateSchemaReader.loadColumns(table.getName());
	}
	
	//method
	public LinkedList<IFlatTableDTO> loadFlatTables() {
		return internalIntermediateSchemaReader.loadFlatTables();
	}
	
	//method
	public Time loadSchemaTimestamp() {
		return internalIntermediateSchemaReader.loadSchemaTimestamp();
	}
}
