//package declaration
package ch.nolix.system.sqlrawschema.columntable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.objectschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.objectschema.schemadto.ColumnDTO;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ColumnDTOMapper {
	
	//method
	public ColumnDTO createColumnDTO(final List<String> columnSystemTableSQLRecord) {
		switch (PropertyType.valueOf(columnSystemTableSQLRecord.get(3)).getBaseType()) {
			case BASE_VALUE:
				return createColumnDTOForBaseValue(columnSystemTableSQLRecord);
			case BASE_BACK_REFERENCE:
				return createColumnDTOForBaseBackReference(columnSystemTableSQLRecord);
			case BASE_REFERENCE:
				return createColumnDTOForBaseReference(columnSystemTableSQLRecord);
			default:
				throw
				InvalidArgumentException.forArgumentNameAndArgument(
					"column system table record",
					columnSystemTableSQLRecord
				);
		}
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseBackReference(final List<String> columnSystemTableSQLRecord) {
		return
		new ColumnDTO(
			columnSystemTableSQLRecord.get(0),
			columnSystemTableSQLRecord.get(1),
			new BaseParametrizedBackReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableSQLRecord.get(4)),
				DataType.valueOf(columnSystemTableSQLRecord.get(5)),
				columnSystemTableSQLRecord.get(6)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseReference(final List<String> columnSystemTableSQLRecord) {
		return
		new ColumnDTO(
			columnSystemTableSQLRecord.get(0),
			columnSystemTableSQLRecord.get(1),
			new BaseParametrizedReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableSQLRecord.get(3)),
				DataType.valueOf(columnSystemTableSQLRecord.get(4)),
				columnSystemTableSQLRecord.get(5)
			)
		);
	}
	
	//method
	private ColumnDTO createColumnDTOForBaseValue(final List<String> columnSystemTableSQLRecord) {
		return
		new ColumnDTO(
			columnSystemTableSQLRecord.get(0),
			columnSystemTableSQLRecord.get(1),
			new BaseParametrizedValueTypeDTO(
				PropertyType.valueOf(columnSystemTableSQLRecord.get(3)),
				DataType.valueOf(columnSystemTableSQLRecord.get(4))
			)
		);
	}
}
