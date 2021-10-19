//package declaration
package ch.nolix.system.nodeintermediatedata.tabledefinition;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;

//class
public final class TableDefinition implements Named {
	
	//attribute
	private final String name;
	
	//multi-attribute
	private final IContainer<String> contentColumnHeadersInOrder;
	
	//constructor
	public TableDefinition(final String name, final IContainer<String> contentColumnHeadersInOrder) {
		
		if (name == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.NAME);
		}
		
		if (contentColumnHeadersInOrder == null) {
			throw new ArgumentIsNullException("content column headers in order");
		}
		
		this.name = name;
		this.contentColumnHeadersInOrder = contentColumnHeadersInOrder;
	}
	
	//method
	public int getContentColumnCount() {
		return contentColumnHeadersInOrder.getElementCount();
	}
	
	//method
	public int getIndexOfContentColumnWithHeader(final String header) {
		return contentColumnHeadersInOrder.getIndexOfFirstEqualElement(header);
	}
	
	//method
	@Override
	public String getName() {
		return name;
	}
}
