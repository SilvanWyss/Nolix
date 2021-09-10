//package declaration
package ch.nolix.system.sqlintermediateschema.columnsystemtable;

//Java imports
import java.util.List;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.databaseschema.schemadto.BaseParametrizedBackReferenceTypeDTO;
import ch.nolix.system.databaseschema.schemadto.BaseParametrizedControlTypeDTO;
import ch.nolix.system.databaseschema.schemadto.BaseParametrizedReferenceTypeDTO;
import ch.nolix.system.databaseschema.schemadto.BaseParametrizedValueTypeDTO;
import ch.nolix.system.databaseschema.schemadto.ColumnDTO;
import ch.nolix.techapi.databasecommonapi.propertytypeapi.PropertyType;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;

//class
public final class ColumnDTOMapper {
	
	//method
	public IColumnDTO createColumnDTO(final List<String> columnSystemTableRecord) {
		switch (PropertyType.valueOf(columnSystemTableRecord.get(2)).getBaseType()) {
			case BASE_VALUE:
				return createColumnDTOForBaseValue(columnSystemTableRecord);
			case BASE_BACK_REFERENCE:
				return createColumnDTOForBaseBackReference(columnSystemTableRecord);
			case BASE_CONTROL_TYPE:
				return createColumnDTOForBaseControlType(columnSystemTableRecord);
			case BASE_REFERENCE:
				return createColumnDTOForBaseReference(columnSystemTableRecord);
			default:
				throw
				new InvalidArgumentException("column system table record", columnSystemTableRecord, "is not valid");
		}
	}
	
	//method
	private IColumnDTO createColumnDTOForBaseBackReference(final List<String> columnSystemTableRecord) {
		return
		new ColumnDTO(
			columnSystemTableRecord.get(0),
			new BaseParametrizedBackReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3),
				columnSystemTableRecord.get(5),
				columnSystemTableRecord.get(6)
			)
		);
	}
	
	//method
	private IColumnDTO createColumnDTOForBaseControlType(final List<String> columnSystemTableRecord) {
		return
		new ColumnDTO(
			columnSystemTableRecord.get(0),
			new BaseParametrizedControlTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3)
			)
		);
	}
	
	//method
	private IColumnDTO createColumnDTOForBaseReference(final List<String> columnSystemTableRecord) {
		return
		new ColumnDTO(
			columnSystemTableRecord.get(0),
			new BaseParametrizedReferenceTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3),
				columnSystemTableRecord.get(4)
			)
		);
	}
	
	//method
	private IColumnDTO createColumnDTOForBaseValue(final List<String> columnSystemTableRecord) {
		return
		new ColumnDTO(
			columnSystemTableRecord.get(0),
			new BaseParametrizedValueTypeDTO(
				PropertyType.valueOf(columnSystemTableRecord.get(2)),
				columnSystemTableRecord.get(3)
			)
		);
	}
}
