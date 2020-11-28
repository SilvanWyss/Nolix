//package declaration
package ch.nolix.element.elementFactory;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperexception.WrapperException;
import ch.nolix.element.elementAPI.IMutableElement;

//class
final class OneTypeElementFactory<E> {
	
	//attributes
	private final Class<E> elementClass;
	private final IElementTakerElementGetter<BaseNode, E> creator;
	
	//method
	@SuppressWarnings("unchecked")
	public <ME extends IMutableElement<ME>> OneTypeElementFactory(final Class<ME> elementClass) {
		this(
			(Class<E>)elementClass,
			s -> {
				try {
					return (E)elementClass.getConstructor().newInstance().reset(s);
				}
				catch (
					final
					InstantiationException
					| IllegalAccessException
					|  InvocationTargetException
					| NoSuchMethodException
					exception
				) {
					throw new WrapperException(exception);
				}
			}
		);
	}
	
	//constructor
	public OneTypeElementFactory(final Class<E> elementClass, final IElementTakerElementGetter<BaseNode, E> creator) {
		
		Validator.assertThat(elementClass).thatIsNamed("element class").isNotNull();
		Validator.assertThat(creator).thatIsNamed(VariableNameCatalogue.CREATOR).isNotNull();
		
		this.elementClass = elementClass;
		this.creator = creator;
	}
	
	//method
	public boolean canCreateElementFrom(final BaseNode specification) {
		return canCreateElementOf(specification.getHeader());
	}
	
	//method
	public boolean canCreateElementOf(final Class<?> type) {
		return (elementClass == type);
	}
	
	//method
	public boolean canCreateElementOf(final String type) {
		return (elementClass.getSimpleName().equals(type) || elementClass.getName().equals(type));
	}
	
	//method
	public E createElementFrom(final BaseNode specification) {
		return creator.getOutput(specification);
	}
	
	//method
	Class<E> getRefElementClass() {
		return elementClass;
	}
}
