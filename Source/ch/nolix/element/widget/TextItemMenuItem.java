//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.base.OptionalValue;
import ch.nolix.element.base.Value;

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
	private final Value<String> text =
	new Value<>(
		PascalCaseNameCatalogue.TEXT,
		this::setText,
		BaseNode::getOneAttributeHeader,
		Node::withOneAttribute
	);
	
	//attribute
	private final OptionalValue<String> id =
	new OptionalValue<>(
		PascalCaseNameCatalogue.ID,
		this::setId,
		BaseNode::getOneAttributeHeader,
		Node::withOneAttribute
	);
			
	//attribute
	private final MutableValue<Boolean> selectionFlag =
	new MutableValue<>(
		SELECTION_FLAG_HEADER,
		this::setSelectionFlag,
		BaseNode::getOneAttributeAsBoolean,
		Node::withOneAttribute
	);
	
	//attribute
	private final Label label = new Label();
	
	//optional attribute
	private IElementTaker<TextItemMenu<?>> selectCommand;
	
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
		specification.getRefAttributes().forEach(this::addOrChangeAttribute);
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
	void select() {
				
		selectionFlag.setValue(true);
		label.setFocused();
		
		if (hasSelectCommand()) {
			selectCommand.run(getParentItemMenu());
		}
	}
	
	//method
	void unselect() {
		
		selectionFlag.setValue(false);
		
		label
		.setUnfocused()
		.setUnhovered();
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
		if (selected) {
			select();
		}
		else {
			unselect();
		}
	}
	
	//method
	private void setText(final String text) {
		
		Validator.assertThat(text).thatIsNamed(VariableNameCatalogue.TEXT).isNotBlank();
		
		this.text.setValue(text);
		
		label.setText(text);
	}
}
