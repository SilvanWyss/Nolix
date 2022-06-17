//package declaration
package ch.nolix.system.gui.color;

//Java imports
import java.lang.reflect.Field;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.reflection.GlobalFieldHelper;

//class
public final class ColorNameConstantExtractor {
	
	//constant
	private static final String STRING_CONSTANT_POSTFIX = "_STRING";
	
	//multi-attribute
	private final LinkedList<Pair<String, Color>> colorNames = extractAndGetColorNames();
	
	//TODO: Do not return a mutable container.
	//method
	public LinkedList<Pair<String, Color>> getColorNames() {
		return colorNames;
	}
	
	//method
	private boolean declaresColor(final Field field) {
		return GlobalFieldHelper.isStaticAndStoresValueOfGivenType(field, Color.class);
	}
	
	//method
	private boolean declaresColorName(final Field field) {
		return (GlobalFieldHelper.isStatic(field) && field.getName().endsWith(STRING_CONSTANT_POSTFIX));
	}
	
	//method
	private LinkedList<Pair<String, Color>> extractAndGetColorNames() {
		
		final LinkedList<Pair<String, Color>> lColorNames = new LinkedList<>();
		
		final var colorStringFields = getColorNameConnstantFields();
		final var colorFields = getColorFields();
		
		for (final var csf : colorStringFields) {
			
			final var colorStringFieldName = csf.getName();
			
			final var colorField =
			colorFields.removeAndGetRefFirst(cf -> colorStringFieldName.startsWith(cf.getName()));
			
			lColorNames.addAtEnd(
				new Pair<>(GlobalFieldHelper.getValueFromStaticField(csf), GlobalFieldHelper.getValueFromStaticField(colorField))
			);
		}
				
		return lColorNames;
	}

	//method
	private LinkedList<Field> getColorFields() {
		
		final var colorFields = new LinkedList<Field>();
		
		for (final var f : Color.class.getDeclaredFields()) {
			if (declaresColor(f)) {
				colorFields.addAtEnd(f);
			}
		}
		
		return colorFields;
	}

	//method
	private LinkedList<Field> getColorNameConnstantFields() {
		
		final var colorNameConstantFields = new LinkedList<Field>();
		
		for (final var f : Color.class.getDeclaredFields()) {
			if (declaresColorName(f)) {
				colorNameConstantFields.addAtEnd(f);
			}
		}
		
		return colorNameConstantFields;
	}
}
