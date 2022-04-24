//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.formatelement.CascadingProperty;
import ch.nolix.system.formatelement.FormatElement;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.textformat.Font;

//class
public abstract class WidgetLook<WL extends WidgetLook<WL>> extends FormatElement<WL, WidgetLookState> {
	
	//constants
	public static final Font DEFAULT_FONT = Font.ARIAL;
	public static final boolean DEFAULT_BOLD_TEXT_FLAG = false;
	public static final int DEAULT_TEXT_SIZE = 20;
	public static final Color DEFAULT_TEXT_COLOR = Color.BLACK;
	
	//constants
	private static final String FONT_HEADER = PascalCaseCatalogue.FONT;
	private static final String BOLD_TEXT_FLAG_HEADER = "BoldTextag";
	private static final String TEXT_SIZE_HEADER = PascalCaseCatalogue.TEXT_SIZE;
	private static final String TEXT_COLOR_HEADER = "TextColor";
	
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
		this::setTextSizeForState,
		DEAULT_TEXT_SIZE
	);
	
	//attribute
	private final CascadingProperty<WidgetLookState, Color> textColor =
	new CascadingProperty<>(
		TEXT_COLOR_HEADER,
		WidgetLookState.class,
		Color::fromSpecification,
		Color::getSpecification,
		DEFAULT_TEXT_COLOR
	);
	
	//constructor
	public WidgetLook() {
		super(WidgetLookState.BASE);
	}
	
	//method
	public final <WL2 extends WidgetLook<WL2>> void addChild(final WL2 widgetLook) {
		internalAddChild(widgetLook);
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
	public final Color getTextColor() {
		return textColor.getValue();
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
	public final void removeTextColors() {
		textColor.setUndefined();
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
	public final WL setTextColorForState(final WidgetLookState state, final Color textColor) {
		
		this.textColor.setValueForState(state, textColor);
		
		return asConcrete();
	}
	
	//method
	public final WL setTextSizeForState(final WidgetLookState state, final int textSize) {
		
		Validator.assertThat(textSize).thatIsNamed(LowerCaseCatalogue.TEXT_SIZE).isPositive();
		
		this.textSize.setValueForState(state, textSize);
		
		return asConcrete();
	}
	
	//method
	final void setState(final WidgetLookState state) {
		internalSwitchToState(state);
	}
}
