//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemadto;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IDatabaseDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDTO;

//class
public record DatabaseDto(String name, ImmutableList<ITableDTO> tables) implements IDatabaseDto {
	
	//constructor
	public DatabaseDto(final String name, final IContainer<ITableDTO> tables) {
		this(name, ImmutableList.forIterable(tables));
	}
	
	//constructor
	public DatabaseDto(final String name, final ImmutableList<ITableDTO> tables) { //NOSONAR: This implementations checks the given arguments.
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotNull();
		
		this.name = name;
		
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
