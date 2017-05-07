/*
 * file:	TabContainerTab.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	130
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.invalidStateException.UnexistingAttributeException;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.basic.NamableElement;

//class
public final class TabContainerTab extends NamableElement<TabContainerTab> {

	//attribute
	private TabContainer tabContainer;
	private Label menuItem;
	
	//optional attribute
	private Widget<?, ?> rectangle;
	
	//method
	/**
	 * @return true if this tab container tab belongs to a tab container
	 */
	public final boolean belongsToTabContainer() {
		return (tabContainer != null);
	}
	
	//method
	/**
	 * @return the attributes of this tab container tab
	 */
	public final List<Specification> getAttributes() {
		
		List<Specification> attributes = super.getAttributes();
		
		if (hasRectangle()) {
			attributes.addAtEnd(getRefRectangle().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the rectangle of this tab container
	 * @throws UnexistingAttributeException if this tab container has no rectangle
	 */
	public final Widget<?, ?> getRefRectangle() {
		
		if (!hasRectangle()) {
			throw new UnexistingAttributeException(this, "rectangle");
		}
		
		return rectangle;
	}
	
	//method
	/**
	 * @return true if this tab container tab has a rectangle
	 */
	public final boolean hasRectangle() {
		return (rectangle != null);
	}
		
	//method
	/**
	 * Sets the rectangle of this tab container tab.
	 * 
	 * @param rectangle
	 * @return this tab container tab
	 * @throws Exception if:
	 * -the given rectangle is null
	 * -the given rectangle already belongs to an other dialog than the tab container of this tab container tab belongs to
	 */
	public final TabContainerTab setRectangle(Widget<?, ?> rectangle) {
		
		Validator.throwExceptionIfValueIsNull("rectangle", rectangle);
		
		this.rectangle = rectangle;
		
		if (belongsToTabContainer() && tabContainer.belongsToDialog()) {
			rectangle.setDialog(tabContainer.getRefDialog());
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the given attribute to this tab container
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public final void addOrChangeAttribute(Specification attribute) {
		
		//Handles the case when the given attribute specifies a rectangle.
		if (tabContainer.getRefDialog().canCreateWidget(attribute.getHeader())) {
			rectangle = tabContainer.getRefDialog().createAndAddWidget(attribute);
			rectangle.setDialog(tabContainer.getRefDialog());
			return;
		}
		
		//Calls the method of the base class.
		super.addOrChangeAttribute(attribute);
	}
	
	public TabContainerTab setName(String name) {
		
		super.setName(name);
		
		if (belongsToTabContainer()) {
		menuItem.setText(name);
		}
		
		return this;
	}
	
	//method
	/**
	 * Sets the tab container this tab container tab belongs to.
	 * 
	 * @param tabContainer
	 * @throws Exception if:
	 * -the given tab container is null
	 * -this tab container tab already belongs to an other tab container tab than the given tab container
	 */
	protected final void setTabContainer(TabContainer tabContainer, Label menuItem) {
		
		//Checks the given parameters.
		Validator.throwExceptionIfValueIsNull("tab container", tabContainer);
		Validator.throwExceptionIfValueIsNull("menuItem", menuItem);
		
		if (belongsToTabContainer() && tabContainer != this.tabContainer) {
			throw new RuntimeException("Tab container tab already belongs to an other tab container.");
		}
		
		this.tabContainer = tabContainer;
		this.menuItem = menuItem;
	}
}
