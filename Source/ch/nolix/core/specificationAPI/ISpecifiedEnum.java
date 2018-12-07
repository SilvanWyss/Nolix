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
public interface ISpecifiedEnum extends Specified {
	
	//default method
	/**
	 * @return the type of the current {@link ISpecifiedEnum}.
	 */
	@Override
	public default String getType() {
		return getClass().getSimpleName();
	}
	
	//default method
	/**
	 * @return the attributes of the current {@link ISpecifiedEnum}.
	 */
	@Override
	public default List<DocumentNode> getAttributes() {
		return DocumentNode.createSpecificationWithHeader(toString()).intoList();
	}
}
