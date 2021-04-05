//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.formatelement.CascadingProperty;
import ch.nolix.element.formatelement.FormatElement;
import ch.nolix.element.gui.textformat.Font;

//class
public abstract class WidgetLook<WL extends WidgetLook<WL>> extends FormatElement<WL, WidgetLookState> {
	
	//constants
	public static final Font DEFAULT_FONT = Font.ARIAL;
	public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;
	public static final int DEAULT_TEXT_SIZE = 10;
	
	//constants
	private static final String FONT_HEADER = PascalCaseCatalogue.FONT;
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextag";
	private static final String TEXT_SIZE_HEADER = PascalCaseCatalogue.TEXT_SIZE;
	
	//attribute
	private final CascadingProperty<WidgetLookState, Font> font =
	new CascadingProperty<>(
		FONT_HEADER,
		WidgetLookState.class,
		Font::fromSpecification,
		Font::getSpecification,
		DEFAULT_FONT
	);
	
	//attribute
	private final CascadingProperty<WidgetLookState, Boolean> boldTextFlag =
	new CascadingProperty<>(
		BOLD_TEXT_FLAG_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsBoolean,
		Node::withAttribute,
		DEFAULT_BOLD_TEXT_FLAG
	);
	
	//attribute
	private final CascadingProperty<WidgetLookState, Integer> textSize =
	new CascadingProperty<>(
		TEXT_SIZE_HEADER,
		WidgetLookState.class,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute,
		DEAULT_TEXT_SIZE
	);
	
	//constructor
	public WidgetLook() {
		super(WidgetLookState.NORMAL);
	}
	
	//method
	public final boolean getBoldTextFlag() {
		return boldTextFlag.getValue();
	}
	
	//method
	public final Font getFont() {
		return font.getValue();
	}
	
	//method
	public final int getTextSize() {
		return textSize.getValue();
	}
	
	//method
	public final void removeBoldTextFlags() {
		boldTextFlag.setUndefined();
	}
	
	//method
	public final void removeFonts() {
		font.setUndefined();
	}
	
	//method
	public final void removeTextSizes() {
		textSize.setUndefined();
	}
	
	//method
	public final WL setBoldTextFlagForState(final WidgetLookState state, final boolean boldTextFlag) {
		
		this.boldTextFlag.setValueForState(state, boldTextFlag);
		
		return asConcrete();
	}
	
	//method
	public final WL setFontForState(final WidgetLookState state, final Font font) {
		
		this.font.setValueForState(state, font);
		
		return asConcrete();
	}
	
	//method
	public final WL setTextSizeForState(final WidgetLookState state, final int textSize) {
		
		this.textSize.setValueForState(state, textSize);
		
		return asConcrete();
	}
}
