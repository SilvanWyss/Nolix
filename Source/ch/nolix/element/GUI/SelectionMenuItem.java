//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.entity.Property;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.Element;
import ch.nolix.element.core.NonEmptyText;

//class
public final class SelectionMenuItem extends Element<SelectionMenuItem> {
	
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
	private final MutableProperty<Boolean> selectionFlagProperty =
	new MutableProperty<Boolean>(
		SELECTION_FLAG_HEADER,
		sf -> setSelectionFlag(sf.getValue()),
		s -> Boolean.createFromSpecification(s),
		b -> b.getSpecification()
	);
	
	//optional attribute
	private final int id;
	
	//attribute
	private final Label label = new Label();
	
	//constructor
	public SelectionMenuItem(final String text) {
		id = -1;
		label.setKeepsFocus();
		unselect();
		setText(text);
	}
	
	//constructor
	public SelectionMenuItem(final int id, final String text) {
		
		Validator.suppose(id).thatIsNamed(VariableNameCatalogue.ID).isPositive();
		
		this.id = id;
		
		unselect();
		setText(text);
	}
	
	//constructor
	private SelectionMenuItem(final DocumentNodeoid specification) {
		id = -1;
		unselect();
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
	}
	
	//method
	public int getHeight() {
		return label.getHeight();
	}
	
	//method
	public int getId() {
		
		if (!hasId()) {
			throw new ArgumentMissesAttributeException(this, VariableNameCatalogue.ID);
		}
		
		return id;
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
	public boolean hasId() {
		return (id > 0);
	}
	
	//method
	public boolean hasId(final long id) {
		return hasId() && (this.id == id);
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
