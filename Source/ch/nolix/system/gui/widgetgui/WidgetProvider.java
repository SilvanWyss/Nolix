//package declaration
package ch.nolix.system.gui.widgetgui;

//Java imports
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.gui.widget.Widget;

//class
/**
 * @author Silvan Wyss
 * @date 2018-03-31
 */
final class WidgetProvider {

	//multi-attribute
	private LinkedList<Class<?>> widgetClasses = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link WidgetProvider}.
	 */
	public WidgetProvider() {}
	
	//constructor
	/**
	 * Creates a new {@link WidgetProvider} with the given widget classes.
	 * 
	 * @param widgetClasses
	 * @throws ArgumentIsNullException if one of the given widget classes is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetProvider}
	 * can already create a {@link Widget} of the same type as one of the given widget classes.
	 */
	public WidgetProvider(final Class<?>... widgetClasses) {
		registerWidgetClass(widgetClasses);
	}
	
	//method
	/**
	 * Adds the given widget class to the current {@link WidgetProvider}.
	 * 
	 * @param widgetClass
	 * @throws ArgumentIsNullException if the given widget class is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetProvider}
	 * can already create a {@link Widget} of the same type as the given widget class.
	 */
	public void registerWidgetClass(final Class<?> widgetClass) {
		
		//Asserts that the given widget class is not null.
		GlobalValidator.assertThat(widgetClass).thatIsNamed("widget class").isNotNull();

		//Asserts that the current widget creator cannot already create a widget of the same type as the given widget class.
		if (canCreateWidgetOf(widgetClass.getSimpleName())) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				widgetClass,
				"is invalid because the current "
				+ getClass().getSimpleName()
				+ "can already create a widget of the type '"
				+ widgetClass.getSimpleName()
				+ "'"
			);
		}
		
		//Adds the given widget class to the current widget creator.
		widgetClasses.addAtEnd(widgetClass);
	}
	
	//method
	/**
	 * Adds the given widget clasess to the current {@link WidgetProvider}.
	 * 
	 * @param widgetClasses
	 * @throws ArgumentIsNullException if one of the given widget classes is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetProvider}
	 * can already create a {@link Widget} of the same type as one of the given widget classes.
	 */
	public void registerWidgetClass(final Class<?>... widgetClasses) {
		
		//Iterates the given widget classes.
		for (final var wc : widgetClasses) {
			registerWidgetClass(wc);
		}
	}
	
	//method
	/**
	 * @param specification
	 * @return true if the current {@link WidgetProvider} can create a {@link Widget} from the given specification.
	 */
	public boolean canCreateWidgetFrom(final INode<?> specification) {
		return canCreateWidgetOf(specification.getHeader());
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link WidgetProvider}
	 * can create a {@link Widget} of the given type.
	 */
	public boolean canCreateWidgetOf(final String type) {
		return widgetClasses.containsAny(wc -> wc.getSimpleName().equals(type));
	}
	
	//method
	/**
	 * @param specification
	 * @return a new {@link Widget} from the given specification from the current {@link WidgetProvider}.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public Widget<?, ?> createWidgetFrom(final INode<?> specification) {
		
		final var widget = createWidget(specification.getHeader());
		widget.addOrChangeAttributes(specification.getRefChildNodes());
		
		return widget;
	}
	
	//method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with default values
	 * from the current {@link WidgetProvider}.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetProvider} cannot create a {@link Widget} of the given type.
	 */
	public Widget<?, ?> createWidget(final String type) {
		
		//Extracts the corresponding widget class.
			final Class<?> widgetClass =
			widgetClasses.getRefFirstOrNull(wc -> wc.getSimpleName().equals(type));
			
			if (widgetClass == null) {
				throw
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					LowerCaseCatalogue.TYPE,
					type,
					"is invalid because the current "
					+ getClass().getSimpleName()
					+ " cannot create a widget of the type '"
					+ type
					+ "'"
				);
			}
		
		//Creates the widget from widget class.
			try {
				final var defaultContructor = widgetClass.getDeclaredConstructor();
				return (Widget<?, ?>)defaultContructor.newInstance();
			} catch (
				InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException exception
			) {
				throw WrapperException.forError(exception);
			}
	}
}
