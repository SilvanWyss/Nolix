//package declaration
package ch.nolix.system.sqlschema.schemadto;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDatabaseDTO;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDTO;

//class
public final class DatabaseDTO implements IDatabaseDTO {
	
	//attribute
	private final String name;
	
	//mutli-attribute
	private final IContainer<ITableDTO> tables;
	
	//constructor
	public DatabaseDTO(final String name,  final IContainer<ITableDTO> tables) {
		
		Validator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		this.tables = tables.toList();
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
