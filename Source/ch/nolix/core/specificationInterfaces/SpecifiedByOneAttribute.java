//package declaration
package ch.nolix.core.specificationInterfaces;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;

//interface
/**
 * A specified by one attribute object is a specified object
 * that has exactly 1 attribute.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
public interface SpecifiedByOneAttribute extends Specified {

	//abstract method
	/**
	 * @return the attribute of this specified by one attribute object.
	 */
	public abstract StandardSpecification getAttribute();
	
	//default method
	/**
	 * @return the attributes of this specified by one attribute object.
	 */
	public default List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>(getAttribute());
	}
}
