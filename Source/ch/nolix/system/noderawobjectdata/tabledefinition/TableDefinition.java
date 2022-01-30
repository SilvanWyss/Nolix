//package declaration
package ch.nolix.system.noderawobjectdata.tabledefinition;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawobjectdataapi.schemainfoapi.IColumnInfo;

//class
public final class TableDefinition implements Named {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<IColumnInfo> contentColumnDefinitions;
	
	//constructor
	public TableDefinition(final String name, IContainer<IColumnInfo> contentColumnDefinitions) {
		
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
	public IContainer<IColumnInfo> getContentColumnDefinitions() {
		return contentColumnDefinitions;
	}
	
	//method
	public int getIndexOfContentColumnWithName(final String name) {
		return contentColumnDefinitions.getIndexOfFirst(ccd -> ccd.getColumnName().equals(name));
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
