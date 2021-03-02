//package declaration
package ch.nolix.element.elementfactory;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.exception.WrapperException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.element.elementapi.IMutableElement;

//class
final class OneTypeElementFactory<E> {
	
	//static method
	private static <ME extends IMutableElement<ME>> ME createElementOf(final Class<ME> elementClass) {
		try {
			return elementClass.getConstructor().newInstance();
		} catch (
			final
			InstantiationException
			| IllegalAccessException
			| InvocationTargetException
			| NoSuchMethodException
			exception
		) {
			throw new WrapperException(exception);
		}
	}
	
	//attributes
	private final Class<E> elementClass;
	private final IElementTakerElementGetter<BaseNode, E> creator;
	
	//constructor
	@SuppressWarnings("unchecked")
	public <ME extends IMutableElement<ME>> OneTypeElementFactory(final Class<ME> elementClass) {
		this(
			(Class<E>)elementClass,
			s -> {
				
				final var element = createElementOf(elementClass);
				element.resetFrom(s);
				
				return (E)element;
			}
		);
	}
	
	//constructor
	public OneTypeElementFactory(final Class<E> elementClass, final IElementTakerElementGetter<BaseNode, E> creator) {
		
		Validator.assertThat(elementClass).thatIsNamed("element class").isNotNull();
		Validator.assertThat(creator).thatIsNamed(LowerCaseCatalogue.CREATOR).isNotNull();
		
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
