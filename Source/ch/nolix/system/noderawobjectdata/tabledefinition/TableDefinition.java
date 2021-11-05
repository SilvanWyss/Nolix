//package declaration
package ch.nolix.system.noderawobjectdata.tabledefinition;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlrawobjectdata.sqlapi.IColumnDefinition;

//class
public final class TableDefinition implements Named {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<IColumnDefinition> contentColumnDefinitions;
	
	//constructor
	public TableDefinition(final String name, IContainer<IColumnDefinition> contentColumnDefinitions) {
		
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
	public int getContentColumnCount() {
		return contentColumnDefinitions.getElementCount();
	}
	
	//method
	public IContainer<IColumnDefinition> getContentColumnDefinitions() {
		return contentColumnDefinitions;
	}
	
	//method
	public int getIndexOfContentColumnWithHeader(final String header) {
		return contentColumnDefinitions.getIndexOfFirst(ccd -> ccd.getColumnHeader().equals(header));
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
