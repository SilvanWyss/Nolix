//package declaration
package ch.nolix.element.gui.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.attributeapi.optionalattributeapi.OptionalIdentifiedByString;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.element.base.MutableElement;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.base.OptionalValue;
import ch.nolix.element.base.Value;
import ch.nolix.element.gui.base.IWidgetGUI;

//class
public final class ItemMenuItem extends MutableElement<ItemMenuItem> implements OptionalIdentifiedByString {
	
	//constants
	public static final boolean DEFAULT_SELECTION_FLAG = false;
	public static final String EMPTY_ITEM_TEXT = "";
	
	//constants
	private static final String ROLE_HEADER = PascalCaseCatalogue.ROLE;
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static ItemMenuItem fromSpecification(final BaseNode specification) {
		
		final var item = new ItemMenuItem();
		item.resetFrom(specification);
		
		return item;
	}
	
	//attribute
	private final Value<ItemMenuItemRole> role =
	new Value<>(
		ROLE_HEADER,
		this::setRole,
		ItemMenuItemRole::fromSpecification,
		ItemMenuItemRole::getSpecification
	);
	
	//attribute
	private final Value<String> text =
	new Value<>(
		PascalCaseCatalogue.TEXT,
		this::setText,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
	);
	
	//attribute
	private final OptionalValue<String> id =
	new OptionalValue<>(
		PascalCaseCatalogue.ID,
		this::setId,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
	);
			
	//attribute
	private final MutableValue<Boolean> selectionFlag =
	new MutableValue<>(
		SELECTION_FLAG_HEADER,
		DEFAULT_SELECTION_FLAG,
		this::setSelectionFlag,
		BaseNode::getOneAttributeAsBoolean,
		Node::withAttribute
	);
	
	//attribute
	private final Label label = new Label();
	
	//optional attributes
	private ItemMenu<?> parentMenu;
	private IElementTaker<ItemMenuItem> selectCommand;
	
	//constructor
	public ItemMenuItem() {
		setRole(ItemMenuItemRole.EMPTY_ITEM);
		setText(EMPTY_ITEM_TEXT);
	}
	
	//constructor
	public ItemMenuItem(final String text) {
		setRole(ItemMenuItemRole.NORMAL_ITEM);
		setText(text);
	}
	
	//constructor
	public ItemMenuItem(final String text, final IElementTaker<ItemMenuItem> selectCommand) {
		
		this(text);
		
		Validator.assertThat(selectCommand).thatIsNamed("select command").isNotNull();
		
		this.selectCommand = selectCommand;
	}
	
	//constructor
	public ItemMenuItem(final String id, final String text) {
		
		this(text);	
		
		setId(id);
	}
	
	//constructor
	public ItemMenuItem(final String id, final String text, final IElementTaker<ItemMenuItem> selectCommand) {
		
		this(text, selectCommand);
		
		setId(id);
	}
	
	//method
	public boolean belongsToMenu() {
		return (parentMenu != null);
	}
	
	//method
	@Override
	public String getId() {
		return id.getValue();
	}
	
	//mehtod
	public IWidgetGUI<?> getParentGUI() {
		return getParentMenu().getParentGUI();
	}
	
	//method
	public ItemMenuItemRole getRole() {
		return role.getValue();
	}
	
	//method
	public String getText() {
		return text.getValue();
	}
	
	//method
	@Override
	public boolean hasId() {
		return id.hasValue();
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
	public boolean isEmptyItem() {
		return (getRole() == ItemMenuItemRole.EMPTY_ITEM);
	}
	
	//method
	public boolean isNormalItem() {
		return (getRole() == ItemMenuItemRole.NORMAL_ITEM);
	}
	
	//method
	public boolean isSelected() {
		return selectionFlag.getValue();
	}
	
	//method
	@Override
	public void reset() {
		unselect();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public void select() {
		
		selectionFlag.setValue(true);
		
		if (parentMenu != null) {
			parentMenu.noteSelectItem(this);
		}
		
		runOptionalSelectCommand();
	}
	
	//method
	public void unselect() {
		selectionFlag.setValue(false);
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
	void recalculate() {
		label.getRefLook().setFrom(getRefItemLook());
		label.recalculate();
	}

	//method
	void setParentMenu(final ItemMenu<?> parentMenu) {
		
		Validator.assertThat(parentMenu).thatIsNamed("parent menu").isNotNull();
		assertDoesNotBelonToMenu();
		
		this.parentMenu = parentMenu;
	}
	
	//method
	private void assertBelongsToMenu() {
		if (!belongsToMenu()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "parent item menu");
		}
	}

	//method
	private void assertDoesNotBelonToMenu() {
		if (belongsToMenu()) {
			throw new ArgumentBelongsToParentException(this, ItemMenu.class);
		}
	}

	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private ItemMenu<?> getParentMenu() {
		
		assertBelongsToMenu();
		
		return parentMenu;
	}
	
	//method
	private LabelLook getRefItemLook() {
		
		if (!isSelected()) {
			return getParentMenu().getRefItemLook();
		}
		
		return getParentMenu().getRefSelectedItemLook();
	}
	
	//method
	private void runOptionalSelectCommand() {
		if (selectCommand != null) {
			selectCommand.run(this);
		}
	}
	
	//method
	private void setId(final String id) {
		
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
	}
	
	//method
	private void setRole(final ItemMenuItemRole role) {
		this.role.setValue(role);
	}
	
	//method
	private void setSelectionFlag(final boolean selected) {
		if (selected) {
			select();
		} else {
			unselect();
		}
	}
	
	//method
	private void setText(final String text) {
		
		this.text.setValue(text);
		
		label.setText(text);
	}
}
