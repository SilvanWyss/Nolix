/*
 * file:	TextLineRectangle.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	160
 */

//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.core.container.AccessorContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Text;
import ch.nolix.element.font.Font;

//class
/**
 * A text line rectangle is a rectangle that has a text in 1 line.
 * 
 * @author Silvan Wyss
 * @param <TLW> The type of a text line widget.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW>>
extends BorderWidget<TLW, TextLineWidgetStructure> {

	//attribute
	private Text text = new Text();
	
	//method
	/**
	 * Adds or change the given attribute to this text line rectangle.
	 * 
	 * @param attribute		The attribute to add or change to this text line rectangle.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the given attribute.
		switch (attribute.getHeader()) {
			case Text.TYPE_NAME:
				setText(attribute.getOneAttributeToString());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
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
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		getRefNormalStructure().setTextSize(AttributeCatalogue.MEDIUM_TEXT_SIZE);
		getRefNormalStructure().setTextColor(Color.BLACK);
		
		getRefHoverStructure().removeTextSize();
		getRefHoverStructure().removeTextColor();
		
		getRefFocusStructure().removeTextSize();
		getRefFocusStructure().removeTextColor();
	}
	
	//method
	/**
	 * Sets the text of this text line rectangle.
	 * 
	 * @param text
	 * @return this text line rectangle
	 * @throws Exception if the given text is null
	 */
	@SuppressWarnings("unchecked")
	public TLW setText(String text) {
		
		this.text = new Text(text);
		
		return (TLW)this;
	}
	
	//method
	/**
	 * @return the current height of the content of this text line rectangle
	 */
	protected final int getContentHeight() {
		return
		new Font(getRefCurrentStructure().getActiveTextSize())
		.getTextHeight();
	}
	
	//method
	/**
	 * @return the current width of the content of this text line rectangle
	 */
	protected int getContentWidth() {	
		return
		new Font(getRefCurrentStructure()
		.getActiveTextSize())
		.getTextWidth(getText());
	}
	
	//method
	/**
	 * Paints the content of this text line rectangle using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected void paintContent(
		final TextLineWidgetStructure rectangleStructure,
		final Graphics graphics
	) {
		new Font(
		rectangleStructure.getActiveTextSize(),
		rectangleStructure.getActiveTextColor())
		.paintText(getText(), graphics);	
	}
	
	protected TextLineWidgetStructure createWidgetStructure() {
		return new TextLineWidgetStructure();
	}
	
	@Override
	public AccessorContainer<Widget<?, ?>> getRefWidgets() {
		return new AccessorContainer<>();
	}
}
