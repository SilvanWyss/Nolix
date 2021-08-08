//package declaration
package ch.nolix.system.databaseschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IDatabaseDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.ITableDTO;

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
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (tables == null) {
			throw new ArgumentIsNullException(PluralLowerCaseCatalogue.TABLES);
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
