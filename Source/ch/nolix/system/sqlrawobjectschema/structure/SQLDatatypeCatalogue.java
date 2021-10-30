//package declaration
package ch.nolix.system.sqlrawobjectschema.structure;

//own imports
import ch.nolix.system.sqlschema.schemadto.DataTypeDTO;

//class
public final class SQLDatatypeCatalogue {
	
	//constant
	public static final DataTypeDTO TEXT_DATA_TYPE =  new DataTypeDTO("NVARCHAR", "MAX");
	
	//constructor
	private SQLDatatypeCatalogue() {}
}
