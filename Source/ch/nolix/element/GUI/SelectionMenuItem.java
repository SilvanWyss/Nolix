//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.entity.Property;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.bases.OptionalIdentifiedElement;
import ch.nolix.element.core.NonEmptyText;

//class
public final class SelectionMenuItem extends OptionalIdentifiedElement {

	//constant
	private static final String SELECTED_FLAG_HEADER = "Selected";
	
	//static method
	public static SelectionMenuItem createFromSpecification(final Specification specification) {
		return new SelectionMenuItem(specification);		
	}
	
	//attribute
	private final Property<NonEmptyText> text =
	new Property<NonEmptyText>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t.getValue()),
		s -> NonEmptyText.createFromSpecification(s)
	);
	
	//attribute
	private final Label label;
	
	//constructor
	public SelectionMenuItem(final String text) {

		label = new Label();	
		setText(text);		
		approveProperties();
	}
	
	//constructor
	public SelectionMenuItem(final int id, final String text) {
		
		super(id);
		
		label = new Label();		
		setText(text);		
		approveProperties();
	}
	
	//constructor
	private SelectionMenuItem(final Specification specification) {
		
		label = new Label();
		
		//TODO: Add init method to Entity.
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
		
		approveProperties();
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (isSelected()) {
			attributes.addAtEnd(StandardSpecification.createSpecificationWithHeaderOnly(SELECTED_FLAG_HEADER));
		}
		
		return attributes;
	}
	
	//method
	public int getHeight() {
		return label.getHeight();
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
		return (getRefLabel().isFocused() || getRefLabel().isHoverFocused());
	}
	
	//method
	public void select() {
		label.setFocused();
	}
	
	//method
	public void unselect() {
		label.setNormal();
	}
	
	//method
	protected void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case SELECTED_FLAG_HEADER:
				select();
				break;
			default:				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//package-visible method
	Label getRefLabel() {
		return label;
	}
	
	//method
	private void setText(final String text) {	
		this.text.setValue(new NonEmptyText(text));
		label.setText(text);
	}
}
