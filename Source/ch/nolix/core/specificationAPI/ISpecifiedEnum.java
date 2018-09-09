//package declaration
package ch.nolix.core.specificationAPI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;

//interface
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public interface ISpecifiedEnum extends Specified {

	//default method
	/**
	 * @return the type of this specified enum.
	 */
	public default String getType() {
		return getClass().getSimpleName();
	}
	
	//default method
	/**
	 * @return the attributes of this specified enum.
	 */
	public default List<StandardSpecification> getAttributes() {
		return
		new List<StandardSpecification>(
			StandardSpecification.createSpecificationWithHeader(toString())
		);
	}
}
