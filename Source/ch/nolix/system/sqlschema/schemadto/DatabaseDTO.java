//package declaration
package ch.nolix.system.sqlschema.schemadto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IDatabaseDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.ITableDTO;

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
