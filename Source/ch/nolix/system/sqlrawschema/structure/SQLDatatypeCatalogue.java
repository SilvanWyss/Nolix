//package declaration
package ch.nolix.system.sqlrawschema.structure;

import ch.nolix.system.sqlbasicschema.schemadto.DataTypeDTO;

//class
public final class SQLDatatypeCatalogue {
	
	//constant
	public static final DataTypeDTO INTEGER = new DataTypeDTO("INT");
	
	//constant
	public static final DataTypeDTO TEXT =  new DataTypeDTO("NVARCHAR", "MAX");
	
	//constructor
	private SQLDatatypeCatalogue() {}
}
