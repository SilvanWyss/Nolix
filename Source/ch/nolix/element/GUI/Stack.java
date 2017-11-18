//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.intData.Margin;

//abstract class
/**
 * A stack is a container that orders its widgets in a line.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 220
 * @param <S> The type of a stack.
 */
public abstract class Stack<S extends Stack<S>> 
extends Container<S, StackStructure>
implements Clearable<S> {
	
	//attribute header
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//default value
	public static final int DEFAULT_ELEMENT_MARGIN = 10;
	
	//optional attribute
	private Margin elementMargin;
	
	//multiple attribute
	private final List<Widget<?, ?>> widgets = new List<Widget<?, ?>>();
	
	//method
	/**
	 * Adds or changes the given attribute to this stack.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		if (getRefGUI().canCreateWidget(attribute.getHeader())) {
			addWidget(getRefGUI().createWidget(attribute));
			return;
		}
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ELEMENT_MARGIN_HEADER:
				setElementMargin(attribute.getOneAttributeToInteger());
				break;		
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given widget to this stack.
	 * 
	 * @param widget
	 * @return this stack.
	 * @throws NullArgumentException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another GUI than this stack.
	 */
	@SuppressWarnings("unchecked")
	public final S addWidget(final Widget<?, ?> widget) {
		
		//Handles the case that this stack belongs to a GUI.
		if (belongsToGUI()) {
			widget.setGUI(getRefGUI());
		}
		
		widgets.addAtEnd(widget);
		
		return (S)this;
	}
	
	//method
	/**
	 * Adds the given widgets to this stack.
	 * 
	 * @param widgets
	 * @return this stack.
	 * @throws NullArgumentException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than this stack.
	 */
	@SuppressWarnings("unchecked")
	public final S addWidget(final Widget<?, ?>... widgets) {
		
		//Iterates the given widgets.
		for (Widget<?, ?> r: widgets) {
			addWidget(r);
		}
		
		return (S)this;
	}
	
	//method
	/**
	 * Removes the widgets of this stack.
	 * 
	 * @return this server.
	 */
	@SuppressWarnings("unchecked")
	public final S clear() {
		
		widgets.clear();
		
		return (S)this;
	}
	
	//method
	/**
	 * @return the active element margin of this stack.
	 */
	public final int getActiveElementMargin() {
		
		//Handles the case that this stack has no element margin.
		if (!hasElementMargin()) {
			return 0;
		}
		
		//Handles the case that this stack has an element margin.
		return elementMargin.getValue();
	}
	
	//method
	/**
	 * @return the attributes of this stack.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this stack has an element margin.
		if (hasElementMargin()) {
			attributes.addAtEnd(new StandardSpecification(ELEMENT_MARGIN_HEADER, elementMargin.getAttributes()));
		}
		
		getRefWidgets().forEach(r -> attributes.addAtEnd(r.getSpecification()));	
		
		return attributes;
	}
	
	//method
	/**
	 * @return the widgets of this stack that are shown.
	 */
	public final AccessorContainer<Widget<?, ?>> getRefShownWidgets() {
		return new AccessorContainer<Widget<?, ?>>(
			getRefWidgets().getRefSelected(w -> !w.isCollapsed())
		);
	}
	
	//method
	/**
	 * @return the widgets of this stack.
	 */
	public AccessorContainer<Widget<?, ?>> getRefWidgets() {
		return new AccessorContainer<Widget<?, ?>>(widgets);
	}
	
	//method
	/**
	 * @return true if this stack container has an element margin.
	 */
	public final boolean hasElementMargin() {
		return (elementMargin != null);
	}
	
	//method
	/**
	 * @return true if this stack contains no widget.
	 */
	public final boolean isEmpty() {
		return widgets.isEmpty();
	}
	
	//method
	/**
	 * Removes the element margin of this stack.
	 */
	public final void removeElementMargin() {
		elementMargin = null;
	}
	
	//method
	/**
	 * Resets the configuration of this stack.
	 */
	public final void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		removeElementMargin();
	}
	
	//method
	/**
	 * Sets the element margin of this stack.
	 * 
	 * @param elementMargin
	 * @return this stack.
	 * @throws NonPositiveArgumentException if the given element margin is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final S setElementMargin(final int elementMargin) {
		
		this.elementMargin = new Margin(elementMargin);
		
		return (S)this;
	}
	
	//method
	/**
	 * Lets this stack create a new widget structure.
	 */
	protected final StackStructure createWidgetStructure() {
		return new StackStructure();
	}
}
