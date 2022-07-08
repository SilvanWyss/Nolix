//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.container.main.LinkedList;
//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
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
	public DatabaseDTO(final String name, final IContainer<ITableDTO> tables) {
		
		if (name == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.NAME);
		}
		
		if (tables == null) {
			throw ArgumentIsNullException.forArgumentName(PluralLowerCaseCatalogue.TABLES);
		}
		
		this.name = name;
		this.tables = LinkedList.fromIterable(tables);
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
