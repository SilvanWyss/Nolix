//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.base.OptionalProperty;
import ch.nolix.element.base.Property;
import ch.nolix.element.core.NonEmptyText;

//class
public final class ItemMenuItem extends Element<ItemMenuItem> {
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static ItemMenuItem fromSpecification(final BaseNode specification) {
		return new ItemMenuItem(specification);
	}
	
	//attribute
	private ItemMenu<?> parentItemMenu;
	
	//attribute
	private final Property<NonEmptyText> text =
	new Property<>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t.getValue()),
		s -> NonEmptyText.fromSpecification(s),
		t -> t.getSpecification()
	);
	
	//attribute
	private final OptionalProperty<String> id =
	new OptionalProperty<>(
		PascalCaseNameCatalogue.ID,
		id -> setId(id),
		s -> s.getOneAttributeAsString(),
		id -> new Node(id)
	);
	
	//optional attribute
	private IElementTaker<ItemMenu<?>> selectCommand;
	
	//attribute
	private final MutableProperty<Boolean> selectionFlag =
	new MutableProperty<>(
		SELECTION_FLAG_HEADER,
		sf -> setSelectionFlag(sf),
		s -> s.toBoolean(),
		sf -> Node.withOneAttribute(sf)
	);
	
	//attribute
	private final Label label = new Label();
	
	//constructor
	public ItemMenuItem(final String text) {
		setText(text);
		unselect();
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
	private ItemMenuItem(final BaseNode specification) {
		unselect();
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
		return selectionFlag.getValue();
	}
	
	//method
	public void select() {
		
		selectionFlag.setValue(true);
		label.setFocused();
		
		if (hasSelectCommand()) {
			selectCommand.run(getParentItemMenu());
		}
	}
	
	//method
	public void unselect() {
		selectionFlag.setValue(false);
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
			throw new ArgumentDoesNotHaveAttributeException(this, "parent item menu");
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
