//package declaration
package ch.nolix.system.objectschema.schema;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.time.base.Time;
import ch.nolix.system.objectschema.parametrizedpropertytype.SchemaImplementation;
import ch.nolix.techapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

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
		return internalRawSchemaReader.loadColumns(table.getName());
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
