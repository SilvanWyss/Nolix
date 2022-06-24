//package declaration
package ch.nolix.system.sqlbasicschema.schemadto;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.IDatabaseDTO;
import ch.nolix.systemapi.sqlbasicschemaapi.schemadtoapi.ITableDTO;

//class
public final class DatabaseDTO implements IDatabaseDTO {
	
	//attribute
	private final String name;
	
	//mutli-attribute
	private final IContainer<ITableDTO> tables;
	
	//constructor
	public DatabaseDTO(final String name, final IContainer<ITableDTO> tables) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		
		//TODO: tables.getCopy()
		this.tables = tables;
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
