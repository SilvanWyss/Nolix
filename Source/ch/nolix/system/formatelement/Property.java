//package declaration
package ch.nolix.system.formatelement;

import ch.nolix.core.attributeuniversalapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public abstract class Property<S extends Enum<S>> implements Named {
	
	//attributes
	private final String name;
	protected FormatElement<?, S> parent;
	
	//constructor
	public Property(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
				
		this.name = name;
	}
	
	//method
	@Override
	public final String getName() {
		return name;
	}
	
	//method declaration
	protected abstract void fillUpValuesSpecificationInto(LinkedList<Node> list);
	
	//method declaration
	protected abstract void setFrom(Property<S> property);
	
	//method declaration
	protected abstract void setUndefined();
	
	//method declaration
	protected abstract void setValueFromSpecification(BaseNode specification);
	
	//method
	final void setParent(final FormatElement<?, S> parent) {
		
		GlobalValidator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
		
		this.parent = parent;
	}
}
