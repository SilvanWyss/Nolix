//package declaration
package ch.nolix.system.sqlintermediatedata.recorddto;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.sqlintermediatedata.sqlapi.ITableDefinitionDTO;

//class
public final class TableDefinitionDTO implements ITableDefinitionDTO {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<String> contentColumnHeaders;
	
	//constructor
	public TableDefinitionDTO(final String name, final IContainer<String> contentColumnHeaders) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (contentColumnHeaders == null) {
			throw new ArgumentIsNullException("content column headers");
		}
		
		this.name = name;
		this.contentColumnHeaders = contentColumnHeaders;
	}
	
	//method
	@Override
	public IContainer<String> getContentColumnHeaders() {
		return contentColumnHeaders;
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
