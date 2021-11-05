//package declaration
package ch.nolix.system.sqlintermediatedata.schema;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlintermediatedata.sqlapi.IColumnDefinition;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinition;

//class
public final class TableDefinition implements ITableDefinition {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<IColumnDefinition> contentColumnDefinitions;
	
	//constructor
	public TableDefinition(final String name, final IContainer<IColumnDefinition> contentColumnDefinitions) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (contentColumnDefinitions == null) {
			throw new ArgumentIsNullException("content column definitions");
		}
		
		this.name = name;
		this.contentColumnDefinitions = contentColumnDefinitions;
	}
	
	//method
	@Override
	public IContainer<IColumnDefinition> getContentColumnDefinitions() {
		return contentColumnDefinitions;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
