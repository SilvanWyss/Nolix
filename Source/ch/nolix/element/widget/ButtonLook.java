//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.layerelement.LayerProperty;

//class
public final class ButtonLook extends TextLineWidgetLook<ButtonLook> {
	
	//constant
	public static final boolean DEFAULT_SHORTENS_TEXT_WHEN_LIMITED_FLAG = false;
	
	//constant
	private static final String SHORTENS_TEXT_WHEN_LIMIITED_HEADER = "ShortensTextWhenLimited";
	
	//attribute
	private final LayerProperty<Boolean> shortensTextWhenLimited =
	new LayerProperty<>(
		SHORTENS_TEXT_WHEN_LIMIITED_HEADER,
		DEFAULT_SHORTENS_TEXT_WHEN_LIMITED_FLAG,
		BaseNode::getOneAttributeAsBoolean,
		Node::withOneAttribute
	);
	
	//method
	public boolean getRecursiveOrDefaultShortensTextWhenLimitedFlag() {
		return shortensTextWhenLimited.getRecursiveOrDefaultValue();
	}
	
	//method
	public ButtonLook remainTextWhenLimited() {
		
		shortensTextWhenLimited.setValue(false);
		
		return this;
	}
	
	//method
	public ButtonLook shortenTextWhenLimited() {
		
		shortensTextWhenLimited.setValue(true);
		
		return this;
	}
}
