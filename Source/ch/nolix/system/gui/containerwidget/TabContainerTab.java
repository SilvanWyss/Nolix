//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.attributeapi.mutablemandatoryattributeuniversalapi.Headerable;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.MutableValue;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.MutableOptionalWidgetProperty;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.structureproperty.ContentPosition;

//class
/**
 * @author Silvan Wyss
 * @date 2016-05-01
 */
public final class TabContainerTab extends MutableElement<TabContainerTab>
implements Clearable, Headerable<TabContainerTab> {
	
	//constants
	public static final String DEFAULT_HEADER = PascalCaseCatalogue.DEFAULT;
	public static final boolean DEFAULT_SELECTION_FLAG = false;
	
	//constants
	private static final String HEADER_HEADER = PascalCaseCatalogue.HEADER;
	private static final String SELECTION_FLAG_HEADER = "Selection";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link TabContainerTab} from the given specification.
	 */
	public static TabContainerTab fromSpecification(final INode<?> specification) {
		
		final var tab = new TabContainerTab();
		tab.resetFromSpecification(specification);
		
		return tab;
	}
	
	//attribute
	private final MutableValue<String> header =
	new MutableValue<>(
		HEADER_HEADER,
		DEFAULT_HEADER,
		this::setHeader,
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
	
	//attributes
	private final Label menuItemLabel = new Label();
	private final MutableOptionalWidgetProperty widget = new MutableOptionalWidgetProperty(this::setWidget);
	
	//constructor
	/**
	 * Creates a new {@link TabContainerTab}.
	 */
	public TabContainerTab() {
		reset();
	}
		
	//method
	/**
	 * Removes the {@link Widget} of the current {@link TabContainerTab}.
	 */
	@Override
	public void clear() {
		widget.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getHeader() {
		return header.getValue();
	}
	
	//method
	/**
	 * @return the height of the current {@link TabContainerTab}.
	 */
	public int getHeight() {
		
		//Handles the case that the current TabContainerTab is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current TabContainerTab contains a Widget.
		return getRefWidget().getHeight();
	}
	
	//method
	/**
	 * @return the {@link Widget} of the current {@link TabContainerTab}
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link TabContainerTab} is empty.
	 */
	public Widget<?, ?> getRefWidget() {
		return widget.getRefWidget();
	}
	
	//method
	/**
	 * @return the width of the current tab container tab.
	 */
	public int getWidth() {
		
		//Handles the case that the current TabContainerTab is empty
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current TabContainerTab contains a Widget.
		return getRefWidget().getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return widget.isEmpty();
	}
	
	//method
	/**
	 * @return true if the current {@link TabContainerTab} is selected.
	 */
	public boolean isSelected() {
		return selectionFlag.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		
		menuItemLabel.reset();
		
		menuItemLabel
		.setContentPosition(ContentPosition.CENTER)
		.setCustomCursorIcon(CursorIcon.HAND);
		
		setHeader(DEFAULT_HEADER);
		clear();
	}
	
	//method
	/**
	 * @param header
	 * @return the current {@link TabContainerTab}.
	 * @throws ArgumentIsNullException if the given header is null.
	 * @throws InvalidArgumentException if the given header is blank.
	 */
	@Override
	public TabContainerTab setHeader(final String header) {
		
		//Asserts that the given header is not null or blank.
		GlobalValidator.assertThat(header).thatIsNamed(LowerCaseCatalogue.HEADER).isNotBlank();
		
		//Sets the header of the current TabContainerTab.
		this.header.setValue(header);
		menuItemLabel.setText(header);
		
		return this;
	}
	
	//method
	/**
	 * Sets the {@link Widget} of the current {@link TabContainerTab}.
	 * 
	 * @param widget
	 * @return the current {@link TabContainerTab}.
	 * @throws ArgumentIsNullException if the given widget is null.
	 */
	public TabContainerTab setWidget(final Widget<?, ?> widget) {
		
		//Asserts that the given widget is not null.
		GlobalValidator.assertThat(widget).isOfType(Widget.class);
		
		//Sets the widget of the current tab container tab.
		this.widget.setWidget(widget);
		
		return this;
	}
	
	//method
	/**
	 * Selects the current {@link TabContainerTab}.
	 */
	void select() {
		selectionFlag.setValue(Boolean.TRUE);
	}

	//method
	/**
	 * Unselects the current {@link TabContainerTab}.
	 */
	void unselect() {
		selectionFlag.setValue(Boolean.FALSE);
	}
	
	//method
	/**
	 * @return the menu item of the current {@link TabContainerTab}.
	 */
	Label getRefMenuItem() {
		return menuItemLabel;
	}
	
	//method
	private void setSelectionFlag(final boolean selected) {
		if (!selected) {
			unselect();
		} else {
			select();
		}
	}
}
