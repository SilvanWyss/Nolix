//package declaration
package ch.nolix.system.sqldatabaserawschema.structure;

import ch.nolix.system.sqldatabasebasicschema.schemadto.DataTypeDTO;

//class
public final class SqlDatatypeCatalogue {
	
	//constant
	public static final DataTypeDTO INTEGER = new DataTypeDTO("INT");
	
	//constant
	public static final DataTypeDTO TEXT =  new DataTypeDTO("NVARCHAR", "MAX");
	
	//constructor
	private SqlDatatypeCatalogue() {}
}
