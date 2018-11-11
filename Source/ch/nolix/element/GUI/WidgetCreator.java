//package declaration
package ch.nolix.element.GUI;

//Java import
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.argument.Argument;
import ch.nolix.core.argument.ArgumentName;
import ch.nolix.core.argument.ErrorPredicate;
//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 160
 */
final class WidgetCreator {

	//multiple attribute
	private List<Class<?>> widgetClasses = new List<Class<?>>();
	
	//constructor
	/**
	 * Creates a new {@link WidgetCreator}.
	 */
	public WidgetCreator() {}
	
	//constructor
	/**
	 * Creates a new {@link WidgetCreator} with the given widget classes.
	 * 
	 * @param widgetClasses
	 * @throws NullArgumentException if one of the given widget classes is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetCreator}
	 * can already create a {@link Widget} of the same type as one of the given widget classes.
	 */
	public WidgetCreator(final Class<?>... widgetClasses) {
		addWidgetClass(widgetClasses);
	}
	
	//method
	/**
	 * Adds the given widget class to the current {@link WidgetCreator}.
	 * 
	 * @param widgetClass
	 * @throws NullArgumentException if the given widget class is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetCreator}
	 * can already create a {@link Widget} of the same type as the given widget class.
	 */
	public void addWidgetClass(final Class<?> widgetClass) {
		
		//Checks if the given widget class is not null.
		Validator.suppose(widgetClass).thatIsNamed("widget class").isInstance();

		//Checks if the current widget creator cannot already create a widget of the same type as the given widget class.
		if (canCreateWidget(widgetClass.getSimpleName())) {
			throw new InvalidArgumentException(
				new Argument(widgetClass),
				new ErrorPredicate(
					"is invalid because the current "
					+ getClass().getSimpleName()
					+ "can already create a widget of the same type"
				)
			);
		}
		
		//Adds the given widget class to the current widget creator.
		widgetClasses.addAtEnd(widgetClass);
	}
	
	//method
	/**
	 * Adds the given widget clasess to the current {@link WidgetCreator}.
	 * 
	 * @param widgetClasses
	 * @throws NullArgumentException if one of the given widget classes is null.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetCreator}
	 * can already create a {@link Widget} of the same type as one of the given widget classes.
	 */
	public void addWidgetClass(final Class<?>... widgetClasses) {
		
		//Iterates the given widget classes.
		for (final var wc : widgetClasses) {
			addWidgetClass(wc);
		}
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link WidgetCreator}
	 * can create a {@link Widget} of the given type.
	 */
	public boolean canCreateWidget(final String type) {
		return widgetClasses.contains(wc -> wc.getSimpleName().equals(type));
	}
	
	//method
	/**
	 * @param specification
	 * @return a new {@link Widget} from the given specification from the current {@link WidgetCreator}.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public Widget<?, ?> createWidget(final DocumentNodeoid specification) {
		
		final var widget = createWidget(specification.getHeader());
		widget.addOrChangeAttributes(specification.getRefAttributes());
		
		return widget;
	}
	
	//method
	/**
	 * @param type
	 * @return a new {@link Widget} of the given type with default values
	 * from the current {@link WidgetCreator}.
	 * @throws InvalidArgumentException
	 * if the current {@link WidgetCreator} cannot create a {@link Widget} of the given type.
	 */
	public Widget<?, ?> createWidget(final String type) {
		
		//Extracts the corresponding widget class.
			final Class<?> widgetClass =
			widgetClasses.getRefFirstOrNull(wc -> wc.getSimpleName().equals(type));
			
			if (widgetClass == null) {
				throw new InvalidArgumentException(
					new ArgumentName(VariableNameCatalogue.TYPE),
					new Argument(type),
					new ErrorPredicate(
						"is invalid because the current "
						+ getClass().getSimpleName()
						+ " cannot create a widget of the type "
						+ type
					)
				);
			}
		
		//Creates the widget from widget class.
			try {
				final var defaultContructor = widgetClass.getDeclaredConstructor();
				return (Widget<?, ?>)defaultContructor.newInstance();
			}
			catch (
				InstantiationException
				| IllegalAccessException
				| IllegalArgumentException
				| InvocationTargetException
				| NoSuchMethodException
				| SecurityException exception
			) {
				throw new RuntimeException(exception);
			}
	}
}
