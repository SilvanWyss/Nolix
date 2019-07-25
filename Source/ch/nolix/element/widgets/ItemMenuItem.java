//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.base.OptionalProperty;
import ch.nolix.element.base.Property;
import ch.nolix.element.core.Boolean;
import ch.nolix.element.core.NonEmptyText;

//class
public final class ItemMenuItem extends Element<ItemMenuItem> {
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static ItemMenuItem createFromSpecification(final DocumentNodeoid specification) {
		return new ItemMenuItem(specification);
	}
	
	//attribute
	private ItemMenu<?> parentItemMenu;
	
	//attribute
	private final Property<NonEmptyText> text =
	new Property<>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t.getValue()),
		s -> NonEmptyText.createFromSpecification(s),
		t -> t.getSpecification()
	);
	
	//attribute
	private final OptionalProperty<String> id =
	new OptionalProperty<>(
		PascalCaseNameCatalogue.ID,
		id -> setId(id),
		s -> s.getOneAttributeAsString(),
		id -> DocumentNode.createWithHeader(id)
	);
	
	//optional attribute
	private IElementTaker<ItemMenu<?>> selectCommand;
	
	//attribute
	private final MutableProperty<Boolean> selectionFlag =
	new MutableProperty<>(
		SELECTION_FLAG_HEADER,
		sf -> setSelectionFlag(sf.getValue()),
		s -> Boolean.createFromSpecification(s),
		b -> b.getSpecification()
	);
	
	//attribute
	private final Label label = new Label();
	
	//constructor
	public ItemMenuItem(final String text) {
		setText(text);
		unselect();
		label.setKeepsFocus();
	}
	
	//constructor
	public ItemMenuItem(final String text, final IElementTaker<ItemMenu<?>> selectCommand) {
		
		this(text);
		
		this.selectCommand = Validator.suppose(selectCommand).thatIsNamed("select command").isNotNull().andReturn();
	}
	
	//constructor
	public ItemMenuItem(final String id, final String text) {
		
		this(text);	
		
		setId(id);
	}
	
	//constructor
	public ItemMenuItem(final String id, final String text, final IElementTaker<ItemMenu<?>> selectCommand) {
		
		this(text, selectCommand);
		
		setId(id);
	}
	
	//constructor
	private ItemMenuItem(final DocumentNodeoid specification) {
		unselect();
		label.setKeepsFocus();
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
	}
	
	//method
	public String getId() {
		return id.getValue();
	}
	
	//method
	public String getText() {
		return text.getValue().getValue();
	}
	
	//method
	public boolean hasId() {
		return id.containsAny();
	}
	
	//method
	public boolean hasId(final String id) {
		return (this.id.hasValue() && this.id.getValue().equals(id));
	}
	
	//method
	public boolean hasSelectCommand() {
		return (selectCommand != null);
	}
	
	//method
	public boolean hasText(final String text) {
		return getText().equals(text);
	}
	
	//method
	public boolean isSelected() {
		return selectionFlag.getValue().getValue();
	}
	
	//method
	public void select() {
		
		selectionFlag.setValue(new Boolean(true));
		label.setFocused();
		
		if (hasSelectCommand()) {
			selectCommand.run(getParentItemMenu());
		}
	}
	
	//method
	public void unselect() {
		selectionFlag.setValue(new Boolean(false));
		label.setNormal();
	}
	
	//package-visible method
	int getHeight() {
		return label.getHeight();
	}

	//package-visible method
	Label getRefLabel() {
		return label;
	}

	//package-visible method
	int getWidth() {
		return label.getWidth();
	}
	
	//package-visible method
	public void setParentMenu(final ItemMenu<?> parentItemMenu) {
		
		Validator.suppose(parentItemMenu).thatIsNamed("parent item menu").isNotNull().andReturn();
		
		this.parentItemMenu = parentItemMenu;
	}

	//method
	private ItemMenu<?> getParentItemMenu() {
		
		if (parentItemMenu == null) {
			throw new ArgumentMissesAttributeException(this, "parent item menu");
		}
		
		return parentItemMenu;
	}
	
	//method
	private void setId(final String id) {
		
		Validator.suppose(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
	}

	//method
	private void setSelectionFlag(final boolean selected) {
		if (selected) select(); else unselect();
	}
	
	//method
	private void setText(final String text) {
		this.text.setValue(new NonEmptyText(text));
		label.setText(text);
	}
}
