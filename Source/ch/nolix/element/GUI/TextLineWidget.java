/*
 * file:	TextLineRectangle.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	160
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.enums.TextStyle;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Text;
import ch.nolix.element.font.Font;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A text line rectangle is a rectangle that has a text in 1 line.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 180
 * @param <TLW> The type of a text line widget.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW>>
extends BorderWidget<TLW, TextLineWidgetLook> {

	//attribute
	private Text text = new Text();
	
	//method
	/**
	 * Adds or change the given attribute to this text line rectangle.
	 * 
	 * @param attribute		The attribute to add or change to this text line rectangle.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the given attribute.
		switch (attribute.getHeader()) {
			case Text.TYPE_NAME:
				setText(attribute.getOneAttributeAsString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the active cursor icon of the current {@link TextLineWidget}.
	 */
	public final CursorIcon getActiveCursorIcon() {
		return getCursorIcon();
	}
	
	//method
	/**
	 * @return the attributes of this text line rectangle
	 */
	public List<StandardSpecification> getAttributes() {
		return super.getAttributes().addAtEnd(text.getSpecification());
	}
	
	//method
	/**
	 * @return the text of this text line rectangle
	 */
	public final String getText() {
		return text.getValue();
	}
	
	//method
	/**
	 * Resets the configuration of this text line rectangle.
	 * 
	 * @return this text line widget.
	 */
	public TLW resetConfiguration() {
				
		getRefBaseLook().setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE);
		getRefBaseLook().setTextColor(Color.BLACK);
		
		getRefHoverLook().removeTextSize();
		getRefHoverLook().removeTextColor();
		
		getRefFocusLook().removeTextSize();
		getRefFocusLook().removeTextColor();
		
		//Calls method of the base class.
		return super.resetConfiguration();
	}
	
	//method
	/**
	 * Sets the text of this text line rectangle.
	 * 
	 * @param text
	 * @return this text line rectangle
	 * @throws Exception if the given text is null
	 */
	public TLW setText(String text) {
		
		this.text = new Text(text);
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the current height of the content of this text line rectangle
	 */
	protected final int getContentAreaHeight() {
		return
		new Font(getRefCurrentLook().getRecursiveOrDefaultTextSize())
		.getTextHeight();
	}
	
	//method
	/**
	 * @return the current width of the content of this text line rectangle
	 */
	protected int getContentAreaWidth() {	
		return
		new Font(getRefCurrentLook()
		.getRecursiveOrDefaultTextSize())
		.getSwingTextWidth(getText());
	}
	
	//method
	/**
	 * Paints the content of this text line rectangle using the given rectangle structure and painter.
	 * 
	 * @param rectangleStructure
	 * @param painter
	 */
	protected void paintContentArea(
		final TextLineWidgetLook rectangleStructure,
		final IPainter painter
	) {
		painter.paintText(
			getText(),
			createFont()
		);	
	}
	
	//method
	protected final Font createFont() {
		
		final var textLineWidgetLook = getRefCurrentLook();
		
		return
		new Font(
			textLineWidgetLook.getRecursiveOrDefaultTextFont(),
			TextStyle.Default,
			textLineWidgetLook.getRecursiveOrDefaultTextSize(),
			textLineWidgetLook.getRecursiveOrDefaultTextColor()
		);
	}
	
	//method
	protected TextLineWidgetLook createWidgetLook() {
		return new TextLineWidgetLook();
	}
	
	//method
	/**
	 * @return the text specification of this text line widget.
	 */
	protected StandardSpecification getTextSpecification() {
		return text.getSpecification();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {}
}
