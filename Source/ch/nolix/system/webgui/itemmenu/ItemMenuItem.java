//package declaration
package ch.nolix.system.webgui.itemmenu;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.element.mutableelement.OptionalValue;
import ch.nolix.system.element.mutableelement.Value;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenu;
import ch.nolix.systemapi.webguiapi.itemmenuapi.IItemMenuItem;

//class
public final class ItemMenuItem extends MutableElement<ItemMenuItem> implements IItemMenuItem<ItemMenuItem> {
	
	//constant
	public static final boolean DEFAULT_SELECTION_FLAG = false;
	
	//constant
	private static final String ID_HEADER = PascalCaseCatalogue.ID;
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//constant
	private static final String SELECTION_FLAG_HEADER = "Selected";
	
	//static method
	public static ItemMenuItem fromSpecification(final INode<?> specification) {
		
		final var item = new ItemMenuItem();
		item.resetFromSpecification(specification);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withIdAndText(
		final String id,
		final String text
	) {
		
		final var item = new ItemMenuItem();
		item.setId(id);
		item.setText(text);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withIdAndTextAndSelectAction(
		final String id,
		final String text,
		final IAction selectAction
	) {
		
		final var item = new ItemMenuItem(i -> selectAction.run());
		item.setId(id);
		item.setText(text);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withIdAndTextAndSelectAction(
		final String id,
		final String text,
		final IElementTaker<IItemMenuItem<?>> selectAction
	) {
		
		final var item = new ItemMenuItem(selectAction);
		item.setId(id);
		item.setText(text);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withText(
		final String text
	) {
		
		final var item = new ItemMenuItem();
		item.setText(text);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withTextAndSelectAction(
		final String text,
		final IAction selectAction
	) {
		
		final var item = new ItemMenuItem(i -> selectAction.run());
		item.setText(text);
		
		return item;
	}
	
	//static method
	public static ItemMenuItem withTextAndSelectAction(
		final String text,
		final IElementTaker<IItemMenuItem<?>> selectAction
	) {
		
		final var item = new ItemMenuItem(selectAction);
		item.setText(text);
		
		return item;
	}
	
	//attribute
	private final OptionalValue<String> id =
	new OptionalValue<>(
		ID_HEADER,
		this::setId,
		INode::getSingleChildNodeHeader,
		Node::withChildNode
	);
	
	//attribute
	private final Value<String> text =
	new Value<>(
		TEXT_HEADER,
		this::setText,
		INode::getSingleChildNodeHeader,
		Node::withChildNode
	);
	
	//attribute
	private final MutableValue<Boolean> selectionFlag =
	new MutableValue<>(
		SELECTION_FLAG_HEADER,
		DEFAULT_SELECTION_FLAG,
		this::setSelectionFlag,
		INode::getSingleChildNodeAsBoolean,
		Node::withChildNode
	);
		
	//optional attribute
	private IItemMenu<?, ?, ?> parentMenu;
	
	//optional attribute
	private final IElementTaker<IItemMenuItem<?>> selectAction;
	
	//constructor
	private ItemMenuItem() {
		selectAction = null;
	}
	
	//constructor
	private ItemMenuItem(final IElementTaker<IItemMenuItem<?>> selectAction) {
		
		GlobalValidator.assertThat(selectAction).thatIsNamed("select action").isNotNull();
		
		this.selectAction = selectAction;
	}
	
	//method
	@Override
	public boolean belongsToMenu() {
		return (parentMenu != null);
	}
	
	//method
	@Override
	public String getId() {
		return id.getValue();
	}
	
	//method
	@Override
	public String getText() {
		return text.getValue();
	}
	
	//method
	@Override
	public boolean isBlank() {
		
		//TODO: Implement.
		return false;
	}
	
	//method
	@Override
	public boolean isSelected() {
		return selectionFlag.getValue();
	}
	
	//method
	@Override
	public void reset() {
		unselect();
	}
	
	//method
	@Override
	public void select() {
		if (!isSelected()) {
			selectWhenNotSelected();
		}
	}
	
	//method
	@Override
	public void unselect() {
		selectionFlag.setValue(false);
	}
	
	//method
	private boolean hasSelectAction() {
		return (selectAction != null);
	}
	
	//method
	private void runOptionalSelectAction() {
		if (hasSelectAction()) {
			selectAction.run(this);
		}
	}
	
	//method
	private void selectWhenNotSelected() {
		
		selectionFlag.setValue(true);
		
		unselectItemsOfOptionalParentMenu();
		
		runOptionalSelectAction();
	}
	
	//method
	private void setId(final String id) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		
		this.id.setValue(id);
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
	}
	
	//method
	private void unselectItemsOfOptionalParentMenu() {
		if (belongsToMenu()) {
			parentMenu.getRefItems().forEach(IItemMenuItem::unselect);
		}
	}
}
