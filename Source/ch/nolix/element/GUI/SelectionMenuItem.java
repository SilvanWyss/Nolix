//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.Property;
import ch.nolix.core.specification.Specification;
import ch.nolix.element.bases.OptionalIdentifiedElement;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.NonEmptyText;

//class
public final class SelectionMenuItem extends OptionalIdentifiedElement {
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static SelectionMenuItem createFromSpecification(
		final Specification specification
	) {
		return new SelectionMenuItem(specification);		
	}
	
	//attribute
	private final Property<NonEmptyText> textProperty =
	new Property<NonEmptyText>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t.getValue()),
		s -> NonEmptyText.createFromSpecification(s)
	);
	
	//attribute
	private final Property<Boolean> selectionFlagProperty =
	new Property<>(
		SELECTION_FLAG_HEADER,
		sf -> setSelectionFlag(sf.getValue()),
		s -> Boolean.createFromSpecification(s)
	);
	
	//attribute
	private final Label label = new Label();
	
	//constructor
	public SelectionMenuItem(final String text) {
		setText(text);
		unselect();
		approveProperties();
		
	}
	
	//constructor
	public SelectionMenuItem(final int id, final String text) {
		
		super(id);
		
		unselect();	
		setText(text);		
		approveProperties();
		
	}
	
	//constructor
	private SelectionMenuItem(final Specification specification) {
		unselect();
		initializeProperties(specification);		
		approveProperties();
	}
	
	//method
	public int getHeight() {
		return label.getHeight();
	}
	
	//method
	public Label getRefLabel() {
		return label;
	}
	
	//method
	public String getText() {
		return textProperty.getValue().getValue();
	}
	
	//method
	public int getWidth() {
		return label.getWidth();
	}

	//method
	public boolean hasText(final String text) {
		return getText().equals(text);
	}
	
	//method
	public boolean isSelected() {
		return selectionFlagProperty.getValue().getValue();
	}
	
	//method
	public void select() {
		selectionFlagProperty.setValue(new Boolean(true));
		label.setFocused();
	}
	
	//method
	public void unselect() {
		selectionFlagProperty.setValue(new Boolean(false));
		label.setNormal();
	}
	
	//method
	private void setSelectionFlag(final boolean selected) {		
		if (!selected) {
			select();
		}
		else {
			unselect();
		}
	}
	
	//method
	private void setText(final String text) {	
		textProperty.setValue(new NonEmptyText(text));
		label.setText(text);
	}
}
