//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.base.OptionalProperty;
import ch.nolix.element.base.Property;

//class
public final class TextItemMenuItem extends Element<TextItemMenuItem> {
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static TextItemMenuItem fromSpecification(final BaseNode specification) {
		return new TextItemMenuItem(specification);
	}
	
	//attribute
	private TextItemMenu<?> parentItemMenu;
	
	//attribute
	private final Property<String> text =
	new Property<>(
		PascalCaseNameCatalogue.TEXT,
		this::setText,
		BaseNode::getOneAttributeAsString,
		Node::withOneAttribute
	);
	
	//attribute
	private final OptionalProperty<String> id =
	new OptionalProperty<>(
		PascalCaseNameCatalogue.ID,
		this::setId,
		BaseNode::getOneAttributeAsString,
		Node::withOneAttribute
	);
	
	//optional attribute
	private IElementTaker<TextItemMenu<?>> selectCommand;
	
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
	public TextItemMenuItem(final String text) {
		setText(text);
		unselect();
	}
	
	//constructor
	public TextItemMenuItem(final String text, final IElementTaker<TextItemMenu<?>> selectCommand) {
		
		this(text);
		
		this.selectCommand = Validator.assertThat(selectCommand).thatIsNamed("select command").isNotNull().andReturn();
	}
	
	//constructor
	public TextItemMenuItem(final String id, final String text) {
		
		this(text);	
		
		setId(id);
	}
	
	//constructor
	public TextItemMenuItem(final String id, final String text, final IElementTaker<TextItemMenu<?>> selectCommand) {
		
		this(text, selectCommand);
		
		setId(id);
	}
	
	//constructor
	private TextItemMenuItem(final BaseNode specification) {
		unselect();
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
	}
	
	//method
	public String getId() {
		return id.getValue();
	}
	
	//method
	public String getText() {
		return text.getValue();
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
	
	//method
	int getHeight() {
		return label.getHeight();
	}

	//method
	Label getRefLabel() {
		return label;
	}

	//method
	int getWidth() {
		return label.getWidth();
	}
	
	//method
	public void setParentMenu(final TextItemMenu<?> parentItemMenu) {
		
		Validator.assertThat(parentItemMenu).thatIsNamed("parent item menu").isNotNull().andReturn();
		
		this.parentItemMenu = parentItemMenu;
	}

	//method
	private TextItemMenu<?> getParentItemMenu() {
		
		if (parentItemMenu == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "parent item menu");
		}
		
		return parentItemMenu;
	}
	
	//method
	private void setId(final String id) {
		
		Validator.assertThat(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
	}

	//method
	private void setSelectionFlag(final boolean selected) {
		if (selected) select(); else unselect();
	}
	
	//method
	private void setText(final String text) {
		
		Validator.assertThat(text).thatIsNamed(VariableNameCatalogue.TEXT).isNotBlank();
		
		this.text.setValue(text);
		
		label.setText(text);
	}
}
