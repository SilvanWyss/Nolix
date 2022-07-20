//package declaration
package ch.nolix.system.multistateelement;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.mainapi.IMutableList;
import ch.nolix.coreapi.documentapi.nodeapi.INode;

//class
public abstract class Property<S extends Enum<S>> implements Named {
	
	//attributes
	private final String name;
	protected MultiStateElement<?, S> parent;
	
	//constructor
	protected Property(final String name) {
		
		GlobalValidator.assertThat(name).thatIsNamed(LowerCaseCatalogue.NAME).isNotBlank();
				
		this.name = name;
	}
	
	//method
	@Override
	public final String getName() {
		return name;
	}
	
	//method declaration
	protected abstract void fillUpValuesSpecificationInto(IMutableList<INode<?>> list);
	
	//method declaration
	protected abstract void setFrom(Property<S> property);
	
	//method declaration
	protected abstract void setUndefined();
	
	//method declaration
	protected abstract void setValueFromSpecification(INode<?> specification);
	
	//method
	final void setParent(final MultiStateElement<?, S> parent) {
		
		GlobalValidator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
		
		this.parent = parent;
	}
}
