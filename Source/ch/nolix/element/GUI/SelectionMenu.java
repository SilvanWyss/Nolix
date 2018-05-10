//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;

//class
public final class SelectionMenu
extends BorderWidget<SelectionMenu, SelectionMenuLook> 
implements Clearable<SelectionMenu> {
	
	//multi-attribute
	private final List<SelectionMenuItem> items = new List<SelectionMenuItem>();
	
	//constructor
	public SelectionMenu() {
		reset();
		approveProperties();
	}
	
	//method
	public SelectionMenu addItem(final SelectionMenuItem selectionMenuItem) {
		
		supposeDoesNotContainItem(selectionMenuItem.getText());
		
		items.addAtEnd(selectionMenuItem);
		
		return this;
	}
	
	//method
	public SelectionMenu addItem(final String text) {
		return addItem(new SelectionMenuItem(text));
	}
	
	//method
	public SelectionMenu addItem(final String... texts) {
		return addItems(new ReadContainer<String>(texts));
	}
	
	//method
	public SelectionMenu addItem(final int id, final String text) {
		
		supposeDoesNotContainItem(text);
		
		items.addAtEnd(new SelectionMenuItem(id, text));
		
		return this;
	}
	
	//method
	public SelectionMenu addItems(final Iterable<String> texts) {
		
		texts.forEach(t -> addItem(t));
		
		return this;
	}
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.ITEM:
				addItem(SelectionMenuItem.createFromSpecification(attribute));
				break;
			default:				
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	public SelectionMenu clear() {
		
		items.clear();
		unselect();
		
		return this;
	}
	
	//method
	public boolean containsItem(final String text) {
		return items.contains(i -> i.hasText(text));
	}
	
	//method
	public boolean containsSelectedItem() {
		return items.contains(i -> i.isSelected());
	}
	
	//method
	/**
	 * @return the active cursor icon of the current {@link Area}.
	 */
	public CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		getItems().forEach(
			i -> attributes.addAtEnd(i.getSpecificationAs(PascalCaseNameCatalogue.ITEM))
		);
		
		return attributes;
	}
	
	//method
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<Widget<?, ?>>();
	}
	
	//method
	public int getSelectedItemId() {
		return getSelectedItem().getId();
	}
	
	//method
	public String getSelectedItemText() {
		return getSelectedItem().getText();
	}
	
	//method
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
	//method
	public void noteLeftMouseButtonPress() {
		
		super.noteLeftMouseButtonPress();
		
		if (getItems().contains(i -> i.getRefLabel().isUnderCursor())) {
			getItems().forEach(i -> i.getRefLabel().noteAnyLeftMouseButtonPress());
		}
	}
	
	//method
	public void noteMouseMove() {
		
		super.noteMouseMove();
		
		getItems().forEach(i -> i.getRefLabel().noteAnyMouseMove());
	}
	
	//method
	public SelectionMenu select(final int id) {
		
		items.getRefFirst(i -> i.hasId(id)).select();
		
		return this;
	}
	
	//method
	public SelectionMenu select(final String text) {
		
		items.getRefFirst(i -> i.hasText(text)).select();
		
		return this;
	}
	
	public void setCursorPositionFromParentContainer(
			final int mouseXPositionOnParentContainer,
			final int mouseYPositionOnParentContainer
	) {
		super.setCursorPositionFromParentContainer(
			mouseXPositionOnParentContainer,
			mouseYPositionOnParentContainer
		);
		
		items.forEach(
			i -> i.getRefLabel()
			.setCursorPositionFromParentContainer(
				getCursorXPosition() + getViewAreaXPositionOnScrollArea(),
				getCursorYPosition() +getViewAreaYPositionOnScrollArea()
			)
		);
	}
	
	//method
	public SelectionMenu unselect() {
		
		if (containsSelectedItem()) {
			getSelectedItem().unselect();
		}
		
		return this;
	}
	
	//method
	protected SelectionMenuLook createWidgetLook() {
		return new SelectionMenuLook();
	}
	
	//method
	protected int getContentHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getItems().getSumByInt(i -> i.getHeight());
	}
	
	//method
	protected int getContentWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return items.getMaxInt(i -> i.getRefLabel().getOriginWidth());
	}
	
	//method
	protected void paintContent(
		final SelectionMenuLook selectionMenuStructure,
		IPainter painter
	) {
		final var x = getContentXPosition();
		var y = getContentYPosition();
		final var width = getContentWidth();
		
		for (final SelectionMenuItem i : items) {
			
			i.getRefLabel().setPositionOnParent(x, y);
			i.getRefLabel().setMinWidth(width);
			i.getRefLabel().paintUsingPositionOnContainer(painter);
			
			y += i.getRefLabel().getHeight();
			
			//TODO: Move that.
				i
				.getRefLabel()
				.getRefBaseLook()
				.reset(
					selectionMenuStructure
					.getRefRecursiveOrDefaultNormalItemLook()
					.getAttributes()
				);
								
				i
				.getRefLabel()
				.getRefHoverLook()
				.reset(
					selectionMenuStructure
					.getRefRecursiveOrDefaultHoverItemLook()
					.getAttributes()
				);
				
				i
				.getRefLabel()
				.getRefFocusLook()
				.reset(
					selectionMenuStructure
					.getRefRecursiveOrDefaultSelectionItemLook()
					.getAttributes()
				);
				
				i
				.getRefLabel()
				.getRefBaseLook()
				.setPaddings(selectionMenuStructure.getRecursiveOrDefaultItemPadding());
		}
	}
	
	//method
	private IContainer<SelectionMenuItem> getItems() {
		return items;
	}
	
	//method
	private SelectionMenuItem getSelectedItem() {
		
		supposeContainsSelectedItem();
		
		return items.getRefFirst(i -> i.isSelected());
	}
	
	//method
	private void supposeContainsSelectedItem() {
		if (!containsSelectedItem()) {
			throw new InvalidStateException(
				this,
				"contains no selected item"
			);
		}
	}

	//method
	private void supposeDoesNotContainItem(final String text) {
		if (containsItem(text)) {
			throw new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate(
					"contains an item with the text '" + text + "'."
				)
			);
		}
	}
}
