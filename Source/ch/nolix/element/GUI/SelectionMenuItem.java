//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.entity.Property;
import ch.nolix.element.bases.OptionalIdentifiedElement;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.NonEmptyText;

//class
public final class SelectionMenuItem
extends OptionalIdentifiedElement<SelectionMenuItem> {
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static SelectionMenuItem createFromSpecification(
		final DocumentNodeoid specification
	) {
		return new SelectionMenuItem(specification);
	}
	
	//attribute
	private final Property<NonEmptyText> text =
	new Property<NonEmptyText>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t.getValue()),
		s -> NonEmptyText.createFromSpecification(s),
		t -> t.getSpecification()
	);
	
	//attribute
	private final Property<Boolean> selectionFlagProperty =
	new Property<Boolean>(
		SELECTION_FLAG_HEADER,
		sf -> setSelectionFlag(sf.getValue()),
		s -> Boolean.createFromSpecification(s),
		b -> b.getSpecification()
	);
	
	//attribute
	private final Label label = new Label();
	
	//constructor
	public SelectionMenuItem(final String text) {
		label.setKeepsFocus();
		unselect();
		setText(text);
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
	private SelectionMenuItem(final DocumentNodeoid specification) {
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
		return text.getValue().getValue();
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
			unselect();
		}
		else {
			select();
		}
	}
	
	//method
	private void setText(final String text) {
		this.text.setValue(new NonEmptyText(text));
		label.setText(text);
	}
}
