/*
 * file:	TabContainer.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	800
 */

//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.data.Margin;
import ch.nolix.element.data.TextColor;

//class
public class TabContainer
extends Container<TabContainer, SimpleBorderableRectangleStructure> {

	//constant
	public final static String SIMPLE_CLASS_NAME = "TabContainer";
	
	//attribute headers
	private final static String MENU_ITEM_MARGIN = "MenuItemMargin";
	private final static String MENU_ITEM_PADDING = "MenuItemPadding";
	private final static String MENU_ITEM_LEFT_PADDING = "MenuItemLeftPadding";
	private final static String MENU_ITEM_RIGHT_PADDING = "MenuItemRightPadding";
	private final static String MENU_ITEM_BOTTOM_PADDING = "MenuItemBottomPadding";
	private final static String MENU_ITEM_TOP_PADDING = "MenuItemTopPadding";
	private final static String MENU_MARGIN = "MenuMargin";
	private final static String TAB = "Tab";
	private final static String CURRENT_TAB = "CurrentTab";

	//attributes
	private final TabContainerMenuItemStructure normalMenuItemStructure = new TabContainerMenuItemStructure();
	private final TabContainerMenuItemStructure hoverMenuItemStructure = new TabContainerMenuItemStructure();
	private final TabContainerMenuItemStructure focusMenuItemStructure = new TabContainerMenuItemStructure();
	private final HorizontalStack menu = new HorizontalStack();
	
	//optional attributes
	private PositiveInteger menuItemLeftPadding;
	private PositiveInteger menuItemRightPadding;
	private PositiveInteger menuItemBottomPadding;
	private PositiveInteger menuItemTopPadding;
	private Margin menuMargin;
	private TabContainerTab currentTab;
	private String nextCurrentTab;
	
	//multiple attribute
	private final List<TabContainerTab> tabs = new List<TabContainerTab>();
	
	//constructor
	/**
	 * Creates new empty tab container with default values.
	 */
	public TabContainer() {
		
		//Calls constructor of the base class.
		super(
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure(),
			new SimpleBorderableRectangleStructure()
		);
		
		hoverMenuItemStructure.setNormalStructure(normalMenuItemStructure);
		focusMenuItemStructure.setNormalStructure(normalMenuItemStructure);
	}
	
	//method
	/**
	 * Sets the given attribute
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case MENU_ITEM_PADDING:
				setMenuItemPadding(attribute.getOneAttributeToInteger());
				break;
			case MENU_ITEM_LEFT_PADDING:
				setMenuItemLeftPadding(attribute.getOneAttributeToInteger());
				break;
			case MENU_ITEM_RIGHT_PADDING:
				setMenuItemRightPadding(attribute.getOneAttributeToInteger());
				break;
			case MENU_ITEM_BOTTOM_PADDING:
				setMenuItemBottomPadding(attribute.getOneAttributeToInteger());			
				break;
			case MENU_ITEM_TOP_PADDING:
				setMenuItemTopPadding(attribute.getOneAttributeToInteger());
				break;
			case MENU_MARGIN:
				setMenuMargin(attribute.getOneAttributeToInteger());
				break;
			case MENU_ITEM_MARGIN:
				setMenuItemMargin(attribute.getOneAttributeToInteger());
				break;
			case CURRENT_TAB:
				selectTab(attribute.getOneAttributeToString());
				break;
			case TAB:
				TabContainerTab tab = new TabContainerTab();
				//tab.setTabContainer(this);
				
				addTab(tab);
				tab.addOrChangeAttributes(attribute.getRefAttributes());
				break;
			default:	
				if (attribute.getHeader().startsWith("NormalMenuItem")) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring("NormalMenuItem".length()));
					getRefNormalMenuItemStructure().setAttribute(temp);
				}
				else if (attribute.getHeader().startsWith("HoverMenuItem")) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring("HoverMenuItem".length()));
					getRefHoverMenuItemStructure().setAttribute(temp);
				}
				else if (attribute.getHeader().startsWith("FocusMenuItem")) {
					Specification temp = attribute.getClone();
					temp.setHeader(attribute.getHeader().substring("FocusMenuItem".length()));
					getRefFocusMenuItemStructure().setAttribute(temp);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
		}	
	}
	
	//method
	/**
	 * Adds the given tab to this tab container.
	 * 
	 * @param tab
	 */
	public TabContainer addTab(TabContainerTab tab) {
		
		Label menuItemLabel = new Label();
		menuItemLabel.setText(tab.getName());
		menuItemLabel.setCursorIcon(CursorIcon.Hand);
		menu.addRectangle(menuItemLabel);
		
		tab.setTabContainer(this, menuItemLabel);
		tabs.addAtEnd(tab);
		
		if (!hasCurrentTab()) {
			selectTab(tab.getName());
		}
		
		if (tab.hasName(nextCurrentTab)) {
			selectTab(tab.getName());
		}
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tabs to this tab container.
	 * 
	 * @param tabs
	 * @return this tab container
	 */
	public TabContainer addTab(TabContainerTab... tabs) {
		
		//Iterates the given tabs.
		for (TabContainerTab t: tabs) {
			addTab(t);
		}
		
		return this;
	}
		
	//method
	/**
	 * @return the attributes of this tab container
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class.
		final List<Specification> attributes = super.getAttributes();
				
		if (hasMenuItemMargin()) {
			attributes.addAtEnd(new Specification(MENU_ITEM_MARGIN + "(" + getMenuItemMargin() + ")"));
		}
		
		if (hasMenuItemLeftPadding()) {
			attributes.addAtEnd(menuItemLeftPadding.getSpecificationAs(MENU_ITEM_LEFT_PADDING));
		}		
		if (hasMenuItemRightPadding()) {
			attributes.addAtEnd(menuItemRightPadding.getSpecificationAs(MENU_ITEM_RIGHT_PADDING));
		}		
		if (hasMenuItemBottomPadding()) {
			attributes.addAtEnd(menuItemBottomPadding.getSpecificationAs(MENU_ITEM_BOTTOM_PADDING));
		}		
		if (hasTopPadding()) {
			attributes.addAtEnd(menuItemTopPadding.getSpecificationAs(MENU_ITEM_TOP_PADDING));
		}
		
		if (hasMenuMargin()) {
			attributes.addAtEnd(new Specification(MENU_MARGIN, menuMargin.getAttributes()));
		}
				
		attributes.addAtEnd(
			getRefHoverMenuItemStructure().getAttributes().forEachAndGetList(a -> a.addPrefixToHeader("NormalMenuItem"))
		);
		attributes.addAtEnd(
			getRefHoverMenuItemStructure().getAttributes().forEachAndGetList(a -> a.addPrefixToHeader("HoverMenuItem"))
		);
		attributes.addAtEnd(
			getRefFocusMenuItemStructure().getAttributes().forEachAndGetList(a -> a.addPrefixToHeader("FocusMenuItem"))
		);
			
		if (hasCurrentTab()) {
			attributes.addAtEnd(new Specification("CurrentTab(" + currentTab.getName() + ")"));
		}
		attributes.addAtEnd(tabs.toContainer(t -> t.getSpecificationAs(TAB)));
				
		return attributes;
	}
	
	//method
	/**
	 * @return the margin of the items of the menu of this tab container
	 */
	public int getMenuItemMargin() {
		return menu.getElementMargin();
	}
	
	//method
	/**
	 * @return the menu item bottom padding of this tab container
	 */
	public int getMenuItemBottomPadding() {
		
		//Handles the case if this tab container has a menu item bottom padding.
		if (hasMenuItemBottomPadding()) {
			return menuItemBottomPadding.getValue();
		}
		
		//Handles the case if this tab container has no menu item bottom padding.
		return 0;
	}
	
	//method
	/**
	 * @return the menu item left padding of this tab container
	 */
	public int getMenuItemLeftPadding() {
		
		//Handles the case if this tab container has a menu item left padding.
		if (hasMenuItemLeftPadding()) {
			return menuItemLeftPadding.getValue();
		}
		
		//Handles the case if this tab container has no menu item left padding.
		return 0;
	}
	
	//method
	/**
	 * @return the menu item right padding of this tab container
	 */
	public int getMenuItemRightPadding() {
		
		//Handles the case if this tab container has a menu item right padding.
		if (hasMenuItemRightPadding()) {
			return menuItemRightPadding.getValue();
		}
		
		//Handles the case if this tab container has no menu item right padding.
		return 0;
	}
	
	//method
	/**
	 * @return the menu item top padding of this tab container
	 */
	public int getMenuItemTopPadding() {
		
		//Handles the case if this tab container has a menu item top padding.
		if (hasMenuItemTopPadding()) {
			return menuItemTopPadding.getValue();
		}
		
		//Handles the case if this tab container has no menu item top padding.
		return 0;
	}
	
	//method
	/**
	 * @return true if this tab container has a menu item bottom padding
	 */
	public boolean hasMenuItemBottomPadding() {
		return (menuItemBottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this tab container has a menu item left padding
	 */	
	public boolean hasMenuItemLeftPadding() {
		return (menuItemLeftPadding != null);
	}
	
	//method
	/**
	 * @return true if this tab container has a menu item right padding
	 */
	public boolean hasMenuItemRightPadding() {
		return (menuItemRightPadding != null);
	}
	
	//method
	/**
	 * @return true if this tab container has a menu item top padding
	 */
	public boolean hasMenuItemTopPadding() {
		return (menuItemTopPadding != null);
	}

	//method
	/**
	 * @return the margin of the menu of this tab container
	 */
	public int getMenuMargin() {
		
		if (hasMenuMargin()) {
			return menuMargin.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the focus structure of the items of the menu of this tab container
	 */
	public TabContainerMenuItemStructure getRefFocusMenuItemStructure() {
		return focusMenuItemStructure;
	}
	
	//method
	/**
	 * @return the hover structure of the items of the menu of this tab container
	 */
	public TabContainerMenuItemStructure getRefHoverMenuItemStructure() {
		return hoverMenuItemStructure;
	}
	
	//method
	/**
	 * @return the normal structure of the items of the menu of this tab container
	 */
	public TabContainerMenuItemStructure getRefNormalMenuItemStructure() {
		return normalMenuItemStructure;
	}
	
	//method
	/**
	 * @return the rectangles of this tab container
	 */
	public List<Rectangle<?, ?>> getRefRectangles() {
		
		List<Rectangle<?, ?>> rectangles = new List<Rectangle<?, ?>>();
		
		for (TabContainerTab t: tabs) {
			if (t.hasRectangle()) {
				rectangles.addAtEnd(t.getRefRectangle());
			}
		}
		
		return rectangles;
	}
	
	//method
	/**
	 * @return the rectangleso of this tab container that are shown
	 */
	public List<Rectangle<?, ?>> getRefShownRectangles() {
		
		if (currentTab.hasRectangle()) {
			return new List<Rectangle<?, ?>>().addAtEnd(currentTab.getRefRectangle());
		}
		
		return new List<Rectangle<?, ?>>();
	}
	
	//method
	/**
	 * @return true if the items of the menu of this tab container have a margin
	 */
	public boolean hasMenuItemMargin() {
		return menu.hasElementMargin();
	}
	
	//method
	/**
	 * @return true if the menu of this tab container has a margin
	 */
	public boolean hasMenuMargin() {
		return (menuMargin != null);
	}
	
	//method
	/**
	 * Removes the menu item bottom padding of this tab container.
	 * 
	 * @return this tab container
	 */
	public TabContainer removeMenuItemBottomPadding() {
		
		menuItemBottomPadding = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the menu item left padding of this tab container.
	 * 
	 * @return this tab container
	 */
	public TabContainer removeMenuItemLeftPadding() {
		
		menuItemLeftPadding = null;
		
		return this;
	}
	
	public TabContainer removeMenuItemPadding() {
		
		removeMenuItemLeftPadding();
		removeMenuItemRightPadding();
		removeMenuItemBottomPadding();
		removeMenuItemTopPadding();
		
		return this;
	}
	
	//method
	/**
	 * Removes the menu item right padding of this tab container.
	 * 
	 * @return this tab container
	 */
	public TabContainer removeMenuItemRightPadding() {
		
		menuItemRightPadding = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the menu item top padding of this tab container.
	 * 
	 * @return this tab container
	 */
	public TabContainer removeMenuItemTopPadding() {
		
		menuItemTopPadding = null;
		
		return this;
	}
	
	//method
	/**
	 * Removes the menu margin of this tab container.
	 * 
	 * @return this tab container
	 */
	public TabContainer removeMenuMargin() {
		
		menuMargin = null;
		
		return this;
	}
	
	//method
	/**
	 * Sets the default configuration of this tab container.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		removeMenuMargin();
		removeMenuItemPadding();
		
		getRefNormalMenuItemStructure().removeBackgroundColor();
		getRefNormalMenuItemStructure().setTextSize(ValueCatalog.MEDIUM_TEXT_SIZE);
		getRefNormalMenuItemStructure().setTextColor(new TextColor(Color.BLACK_STRING));
		
		getRefHoverMenuItemStructure().removeBackgroundColor();
		getRefHoverMenuItemStructure().removeTextColor();
		getRefHoverMenuItemStructure().removeTextSize();
		
		getRefFocusMenuItemStructure().removeBackgroundColor();
		getRefFocusMenuItemStructure().removeTextColor();
		getRefFocusMenuItemStructure().removeTextSize();
	}
	
	public void selectTab(String name) {
		
		if (tabs.contains(t -> t.hasName(name))) {
			currentTab = tabs.getRefFirst(t -> t.hasName(name));
			
			for (Rectangle<?, ?> mi: menu.getRefRectangles()) {
				Label menuItem = (Label)mi;
				if (menuItem.getText().equals(name)) {
					menuItem.setFocused();
				}
				else {
					menuItem.setNormal();
				}
			}
		}
		else {
			nextCurrentTab = name;
		}
	}
		
	//method
	/**
	 * Sets the dialog of this tab container.
	 */
	public void setDialog(Dialog<?> dialog) {
		
		//Calls method of the base class.
		super.setDialog(dialog);
		
		menu.setDialog(dialog);
	}
	
	//method
	/**
	 * Sets the margin of the items of the menu of this tab container.
	 * 
	 * @param menuItemsMargin
	 * @throws Exception if the given menu items margin is not positive
	 */
	public void setMenuItemMargin(int menuItemsMargin) {
		menu.setElementMargin(menuItemsMargin);
	}
	
	//method
	/**
	 * Sets the menu item bottom padding of this tab container.
	 * 
	 * @param menuItemBottomPadding
	 * @throws Exception if the given menu item bottom padding is not positive
	 * @return this tab container
	 */
	public TabContainer setMenuItemBottomPadding(final int menuItemBottomPadding) {
		
		this.menuItemBottomPadding = new PositiveInteger(menuItemBottomPadding);
		
		return this;
	}
	
	//method
	/**
	 * Sets the menu item left padding of this tab container.
	 * 
	 * @param menuItemLeftPadding
	 * @throws Exception if the given menu item left padding is not positive
	 * @return this tab container
	 */
	public TabContainer setMenuItemLeftPadding(final int menuItemLeftPadding) {
		
		this.menuItemLeftPadding = new PositiveInteger(menuItemLeftPadding);
		
		return this;
	}
	
	//method
	/**
	 * Sets the menu item padding of this tab container
	 * 
	 * @param menuItemPadding
	 * @return this tab container
	 * @throws Exception if the given menu itme padding is not positive
	 */
	public TabContainer setMenuItemPadding(final int menuItemPadding) {
		
		setMenuItemLeftPadding(menuItemPadding);
		setMenuItemRightPadding(menuItemPadding);
		setMenuItemBottomPadding(menuItemPadding);
		setMenuItemTopPadding(menuItemPadding);
		
		return this;
	}
	
	//method
	/**
	 * Sets the menu item right padding of this tab container.
	 * 
	 * @param menuItemRightPadding
	 * @throws Exception if the given menu item right padding is not positive
	 * @return this tab container
	 */
	public TabContainer setMenuItemRightPadding(final int menuItemRightPadding) {
		
		this.menuItemRightPadding = new PositiveInteger(menuItemRightPadding);
		
		return this;
	}
	
	//method
	/**
	 * Sets the menu item top padding of this tab container.
	 * 
	 * @param menuItemTopPadding
	 * @return this tab container
	 * @throws Exception if the given menu item top padding is not positive
	 */
	public TabContainer setMenuItemTopPadding(final int menuItemTopPadding) {
		
		this.menuItemTopPadding = new PositiveInteger(menuItemTopPadding);
		
		return this;
	}
	
	//method
	/**
	 * Sets the margin of the menu of this tab container.
	 * 
	 * @param menuMargin
	 * @throws Exception if the given menu margin is not positive
	 */
	public void setMenuMargin(int menuMargin) {
		this.menuMargin = new Margin(menuMargin);
	}

	//method
	/**
	 * @return the current height of the content of this tab container
	 */
	protected final int getContentHeight() {
		
		if (currentTab.hasRectangle()) {
			return menu.getHeight()
					+ getMenuMargin()
					+ currentTab.getRefRectangle().getHeight();
		}
		
		return menu.getHeight() + getMenuMargin();
	}

	//method
	/**
	 * @return the current width of the content of this tab container
	 */
	protected final int getContentWidth() {
		
		if (currentTab.hasRectangle()) {
			return Calculator.getMax(
					menu.getWidth(),
					currentTab.getRefRectangle().getWidth()
			);
		}
		
		return menu.getWidth();
	}
	
	//method
	/**
	 * @return true if this tab container has a current structure
	 */
	protected final boolean hasCurrentTab() {
		return currentTab != null;
	}
	
	//method
	/**
	 * Lets this tab container note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		if (menu.isPointed()) {
			
			menu.noteLeftMouseButtonPress();
			
			if (menu.getRefRectangles().contains(r -> r.isPointed())) {
				selectTab(((Label)menu.getRefRectangles().getRefFirst(r -> r.isPointed())).getText());
			}
		}
	}
	
	public void noteMouseMove() {
		
		super.noteMouseMove();
		menu.noteMouseMove();
	}

	/**
	 * Paints this tab container using the given rectangle structure and given graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected void paintContent(SimpleBorderableRectangleStructure rectangleStructure, Graphics graphics) {
		
		super.paintContent(rectangleStructure, graphics);
		
		//Update problem, when does the menu take the data from the structures?
		//Answer: not when it is painted, but on events: tab container must lead events though!
		//Paints the menu of this tab container.
		for (Rectangle<?, ?> mi: menu.getRefRectangles()) {
			
			Label label = (Label)mi;
			
			label.setCursorIcon(CursorIcon.Hand);
			
			label.removePadding();
			if (hasMenuItemLeftPadding()) {
				label.setLeftPadding(getMenuItemLeftPadding());
			}
			if (hasMenuItemRightPadding()) {
				label.setRightPadding(getMenuItemRightPadding());
			}
			if (hasMenuItemBottomPadding()) {
				label.setBottomPadding(getMenuItemBottomPadding());
			}
			if (hasMenuItemTopPadding()) {
				//label.setTopPadding(getMenuItemTopPadding());
			}
			
			if (getRefNormalMenuItemStructure().hasBackgroundColor()) {
				label.getRefNormalStructure().setBackgroundColor(getRefNormalMenuItemStructure().getRefRecBackgroundColor().getStringValue());
			}
			else {
				label.getRefNormalStructure().removeBackgroundColor();
			}
			label.getRefNormalStructure().setTextSize(getRefNormalMenuItemStructure().getRefRecTextSize());
			label.getRefNormalStructure().setTextColor(getRefNormalMenuItemStructure().getRefRecTextColor().getValue());
			
			if (getRefHoverMenuItemStructure().hasBackgroundColor()) {
				label.getRefHoverStructure().setBackgroundColor(getRefHoverMenuItemStructure().getRefRecBackgroundColor().getStringValue());
			}
			else {
				label.getRefHoverStructure().removeBackgroundColor();
			}
			label.getRefHoverStructure().setTextSize(getRefHoverMenuItemStructure().getRefRecTextSize());
			label.getRefHoverStructure().setTextColor(getRefHoverMenuItemStructure().getRefRecTextColor().getValue());
			
			if (getRefFocusMenuItemStructure().hasBackgroundColor()) {
				label.getRefFocusStructure().setBackgroundColor(getRefFocusMenuItemStructure().getRefRecBackgroundColor().getStringValue());
			}
			else {
				label.getRefFocusStructure().removeBackgroundColor();
			}
			label.getRefFocusStructure().setTextSize(getRefFocusMenuItemStructure().getRefRecTextSize());
			label.getRefFocusStructure().setTextColor(getRefFocusMenuItemStructure().getRefRecTextColor().getValue());
			
			label.paintUsingRelativePosition(graphics);
		}
		
		graphics.translate(-getContentXPosition(), -getContentYPosition());
		menu.paintUsingRelativePosition(graphics);
		graphics.translate(getContentXPosition(), getContentYPosition());
	}
	
	//method
	/**
	 * Sets the relative mouse position of this tab container.
	 * 
	 * @param relativeMouseXPosition
	 * @param realtiveMouseYPosition
	 */
	protected void setRelativeMousePosition(final int relativeMouseXPosition, final int relativeMouseYPosition) {
		
		//Calls method of the base class.
		super.setRelativeMousePosition(relativeMouseXPosition, relativeMouseYPosition);
		
		menu.setRelativeMousePosition(getMouseXPosition(), getMouseYPosition());
	}
	
	//method
	/**
	 * Sets the relative position of this tab container.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setRelativePosition(
		final int relaitveXPosition,
		final int relativeYPosition
	) {
		
		//Calls method of the base class.
		super.setRelativePosition(relaitveXPosition, relativeYPosition);
		
		menu.setRelativePosition(
			getContentXPosition(),
			getContentYPosition()
		);
		
		if (currentTab.hasRectangle()) {
			currentTab.getRefRectangle().setRelativePosition(
				getContentXPosition(),
				getContentYPosition() + menu.getHeight() + getMenuMargin()
			);
		}
	}
}
