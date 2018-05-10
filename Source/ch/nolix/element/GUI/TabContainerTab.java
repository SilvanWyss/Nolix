//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.bases.HeaderableElement;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 100
 */
public class TabContainerTab
extends HeaderableElement<TabContainerTab>
implements Clearable<TabContainerTab> {

	//default value
	public static final String DEFAULT_HEADER = PascalCaseNameCatalogue.DEFAULT;
	
	//method
	/**
	 * @param specification
	 * @return a new {@link TabContainerTab} from the given specification.
	 */
	public static TabContainerTab createFromSpecification(final Specification specification) {
		
		final var tab = new TabContainerTab();
		tab.reset(specification);
		
		return tab;
	}
	
	//optional attributes
	private TabContainer parentTabContainer;
	private Label menuItem;
	private boolean selected = false;
	private Widget<?, ?> widget;
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab}.
	 */
	public TabContainerTab() {
		reset();
		approveProperties();		
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab} with the given header.
	 * 
	 * @param header
	 * @param widget
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 */
	public TabContainerTab(final String header) {
		
		//Calls other constructor.
		this();
		
		setHeader(header);
	}
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab} with the given header and widget.
	 * 
	 * @param header
	 * @param widget
	 * @throws NullArgumentException if the given header is null.
	 * @throws EmptyArgumentException if the given header is empty.
	 * @throws NullArgumentException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another {@link GUI}
	 * than the current {@link TabContainerTab}.
	 */
	public TabContainerTab(final String header, final Widget<?,? > widget) {
		
		//Calls other constructor.
		this(header);
		
		setWidget(widget);
	}
	
	//method
	/**
	 * Sets the given attribute to the current {@link TabContainerTab}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Handles the case that the given attribute specifies a widget.
		if (GUI.canCreateWidget(attribute.getHeader())) {
			setWidget(GUI.createWidget(attribute));
		}
		
		//Handles the case that the given attribute specifies no widget.
			//Calls the method of the base class.
			super.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} belongs to a {@link TabContainer}.
	 */
	public boolean belongsToTabContainer() {
		return (parentTabContainer != null);
	}
	
	//method
	/**
	 * Removes the widget of the current {@link TabContainerTab}.
	 * 
	 * @return the current {@link TabContainerTab}.
	 */
	public TabContainerTab clear() {
		
		widget = null;
		
		return this;
	}
	
	//method
	/**
	 * @return the attributes of the current {@link TabContainerTab}.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//Handles the case that the current tab container tab contains a widget.
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
	}
	
	public int getHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefWidget().getHeight();
	}
	
	//method
	/**
	 * @return the widget of the current {@link TabContainerTab}
	 * @throws UnexistingAttributeException if the current {@link TabContainerTab} contains no widget.
	 */
	public Widget<?, ?> getRefWidget() {
		
		supposeContainsWidget();
		
		return widget;
	}
	
	public int getWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefWidget().getWidth();
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} contains no widget.
	 */
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} is selected.
	 */
	public boolean isSelected() {
		return selected;		
	}
	
	//method
	/**
	 * Resets the current {@link TabContainerTab}.
	 * 
	 * @return the current {@link TabContainerTab}.
	 */
	public TabContainerTab reset() {
		
		setHeader(DEFAULT_HEADER);
		clear();
		
		return this;
	}
	
	//method
	public TabContainerTab select() {
		
		selected = true;
		
		if (belongsToTabContainer()) {
			getRefMenuItem().setFocused();
		}
		
		if (containsAny()) {
			getRefWidget().setNormal();
		}
		
		return this;
	}
		
	//method
	/**
	 * Sets the widget of the current {@link TabContainerTab}.
	 * 
	 * @param widget
	 * @return the current {@link TabContainerTab}.
	 * @throws NullArgumentException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another {@link GUI}
	 * than the current {@link TabContainerTab}.
	 */
	public TabContainerTab setWidget(Widget<?, ?> widget) {
		
		Validator
		.suppose(widget)
		.thatIsOfType(Widget.class)
		.isNotNull();
		
		if (belongsToTabContainer() && getParentTabContainer().belongsToGUI()) {
			widget.setGUI(getParentTabContainer().getParentGUI());
		}
		
		this.widget = widget;
		
		if (!isSelected()) {
			widget.setDisabled();
		}
		
		return this;
	}
	
	public TabContainerTab unselect() {
		
		selected = false;
		
		if (belongsToTabContainer()) {
			getRefMenuItem().setNormal();
		}
		
		if (containsAny()) {
			getRefWidget().setDisabled();
		}
		
		return this;
	}
	
	//package-visible method
	/**
	 * Sets the tab container the current {@link TabContainerTab} belongs to.
	 * 
	 * @param tabContainer
	 * @throws Exception if:
	 * -the given tab container is null
	 * -the current {@link TabContainerTab} already belongs to an other tab container tab than the given tab container
	 */
	void setParentTabContainer(final TabContainer parentTabContainer, final Label menuItem) {
		
		Validator
		.suppose(parentTabContainer)
		.thatIsNamed("parent tab container")
		.isNotNull();
		
		Validator
		.suppose(menuItem)
		.thatIsNamed("menu item")
		.isNotNull();
		
		if (
			belongsToTabContainer()
			&& getParentTabContainer() != parentTabContainer
		) {
			throw new InvalidStateException(
				this,
				"belongs already to an other tab container"
			);
		}
		
		this.parentTabContainer = parentTabContainer;
		this.menuItem = menuItem;
	}
	
	//method
	private TabContainer getParentTabContainer() {
		
		supposeBelongsToTabContainer();
		
		return parentTabContainer;
	}
	
	//method
	private Label getRefMenuItem() {
		
		supposeBelongsToTabContainer();
		
		return menuItem;
	}
	
	//method
	private void supposeBelongsToTabContainer() {
		
		//Checks if the current tab container tab belongs to a tab container.
		if (!belongsToTabContainer()) {
			throw new InvalidStateException(
				this,
				"does not belong to a tab container"
			);
		}
	}
	
	//method
	private void supposeContainsWidget() {
		
		//Checks if the current tab container tab contains a widget.
		if (isEmpty()) {
			throw new InvalidStateException(
				this,
				"contains no widget"
			);
		}
	}
}
