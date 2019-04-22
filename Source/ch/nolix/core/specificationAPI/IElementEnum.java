//package declaration
package ch.nolix.core.specificationAPI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public interface IElementEnum extends Specified {
	
	//default method
	/**
	 * @return the type of the current {@link IElementEnum}.
	 */
	@Override
	public default String getType() {
		return getClass().getSimpleName();
	}
	
	//default method
	/**
	 * @return the attributes of the current {@link IElementEnum}.
	 */
	@Override
	public default List<DocumentNode> getAttributes() {
		return DocumentNode.createWithHeader(toString()).intoList();
	}
}
