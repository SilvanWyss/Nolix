/*
 * file:	Configuration.java
 * author:	Silvan Wyss
 * month:	23.01.2016
 * lines:	50
 */

//package declaration
package ch.nolix.element.configuration;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.specification.Configurable;
import ch.nolix.common.specification.Specification;

//class
public class StandardConfiguration extends Configuration<StandardConfiguration> {

	//constant
	public static final String SIMPLE_CLASS_NAME = "StandardConfiguration";
	
	//constructor
	/**
	 * Creates new configuration with default attributes.
	 */
	public StandardConfiguration() {}
	
	//constructor
	/**
	 * Creates new configuration with the given attributes.
	 * @param attributes
	 * @throws Exception if the given attributes are not valid
	 */
	public StandardConfiguration(List<Specification> attributes) {
		addOrChangeAttributes(attributes);
	}
	
	public final void configure(Configurable element) {	
		if (selects(element)) {
			
			setAttachingAttributesTo(element);
						
			final List<Configurable> elements = element.getRefConfigurables();
			elements.forEach(e -> configurations.forEach(c -> c.configure(e)));
		}
	}
}
