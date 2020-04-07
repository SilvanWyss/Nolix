//package declaration
package ch.nolix.element.color;

//Java import
import java.lang.reflect.Field;

//own imports
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.reflectionHelpers.FieldHelper;

//class
public final class ColorPairsExtractor {
	
	//constant
	private static final String STRING_CONSTANT_POSTFIX = "_STRING";
	
	//multi-attribute
	private final IContainer<Pair<String, Color>> colorPairs = extractAndGetColorPairs();
	
	//method
	public IContainer<Pair<String, Color>> getColorPairs() {
		return colorPairs;
	}
	
	//method
	private boolean declaresColor(final Field field) {
		return FieldHelper.isStaticAndStoresValueOfGivenType(field, Color.class);
	}
	
	//method
	private boolean declaresColorString(final Field field) {
		return (FieldHelper.isStatic(field) && field.getName().endsWith(STRING_CONSTANT_POSTFIX));
	}
	
	//method
	private IContainer<Pair<String, Color>> extractAndGetColorPairs() {
		
		final LinkedList<Pair<String, Color>> lColorPairs = new LinkedList<>();
		
		final var colorStringFields = getColorStringFields();
		final var colorFields = getColorFields();
		
		for (final var csf : colorStringFields) {
			
			final var colorStringFieldName = csf.getName();
			
			final var colorField =
			colorFields.removeAndGetRefFirst(cf -> colorStringFieldName.startsWith(cf.getName()));
			
			lColorPairs.addAtEnd(
				new Pair<>(FieldHelper.getValueFromStaticField(csf), FieldHelper.getValueFromStaticField(colorField))
			);
		}
				
		return lColorPairs;
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
	private LinkedList<Field> getColorStringFields() {
		
		final var colorStringFields = new LinkedList<Field>();
		
		for (final var f : Color.class.getDeclaredFields()) {
			if (declaresColorString(f)) {
				colorStringFields.addAtEnd(f);
			}
		}
		
		return colorStringFields;
	}
}
