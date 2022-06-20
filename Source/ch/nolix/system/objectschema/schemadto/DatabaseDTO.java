//package declaration
package ch.nolix.system.objectschema.schemadto;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IDatabaseDTO;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDTO;

//class
public final class DatabaseDTO implements IDatabaseDTO {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<ITableDTO> tables;
	
	//constructor
	//For a better performance, this implementation does not use all comfortable methods.
	public DatabaseDTO(final String name, final IContainer<TableDTO> tables) {
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.NAME);
		}
		
		if (tables == null) {
			throw ArgumentIsNullException.forArgumentName(PluralLowerCaseCatalogue.TABLES);
		}
		
		this.name = name;
		this.tables = tables.asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
	
	//method
	@Override
	public IContainer<ITableDTO> getTables() {
		return tables;
	}
}
