//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textFormat.TextFormat;

//abstract class
/**
 * A {@link TextLineWidget} is a {@link Widget} that contains 1 text line.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 210
 * @param <TLW> The type of a {@link TextLineWidget}.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW, TLWL>, TLWL extends TextLineWidgetLook<TLWL>>
extends BorderWidget<TLW, TLWL> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;
	
	//attribute
	private MutableProperty<String> text =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TEXT,
		t -> setText(t),
		s -> s.getOneAttributeAsString(),
		t -> new DocumentNode(PascalCaseNameCatalogue.TEXT, t)
	);
	
	//method
	/**
	 * @return the shown text of the current {@link TextLineWidget}.
	 */
	public final String getShownText() {
		return getTextFormat().getFirstPart(getText(), getContentAreaWidth(), true);
	}
	
	//method
	/**
	 * @return the text of the current {@link TextLineWidget}.
	 */
	public final String getText() {
		return text.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLW reset() {
				
		setText(DEFAULT_TEXT);
		
		//Calls method of the base class.
		super.reset();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public TLW resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
		
		getRefBaseLook()
		.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE)
		.setTextColor(Color.BLACK);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the text of the current {@link TextLineWidget}.
	 * 
	 * @param text
	 * @return the current {@link TextLineWidget}.
	 * @throws NullArgumentException if the given text is null.
	 */
	public TLW setText(final String text) {
		
		this.text.setValue(text);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return true if the current {@link TextLineWidget} scrolls its shown text
	 * when the current {@link TextLineWidget} has a limiting proposal width or max width.
	 */
	public final boolean scrollsShownTextWhenHasLimitedWidth() {
		return !shortensShownTextWhenHasLimitedWidth();
	}
	
	//abstract method
	/**
	 * @return true if the current {@link TextLineWidget} shortens its shown text
	 * when the current {@link TextLineWidget} has a limiting proposal width or max width.
	 */
	public abstract boolean shortensShownTextWhenHasLimitedWidth();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpChildWidgets(final List<Widget<?, ?>> list) {}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaHeight() {
		return new TextFormat(getRefLook().getRecursiveOrDefaultTextSize()).getTextHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		
		//Extracts the look of the current TextLineWidget.
		final var look = getRefLook();
		
		//Handles the case that the current TextLineWidget shortens its text when it has a limited width.
		if (shortensShownTextWhenHasLimitedWidth()) {
			
			if (hasMaxWidth()) {
				return
				getMaxWidth()
				- look.getRecursiveOrDefaultLeftBorderThickness()
				- look.getRecursiveOrDefaultLeftPadding()
				- look.getRecursiveOrDefaultRightPadding()
				- look.getRecursiveOrDefaultRightBorderThickness();
			}
			
			if (hasProposalWidth()) {
				return
				getProposalWidth()
				- look.getRecursiveOrDefaultLeftBorderThickness()
				- look.getRecursiveOrDefaultLeftPadding()
				- look.getRecursiveOrDefaultRightPadding()
				- look.getRecursiveOrDefaultRightBorderThickness();
			}
		}
		
		//Handles the case that the current TextLineWidget does not shorten its text when it has a limited width.
		return getTextFormat().getSwingTextWidth(getText());
	}
	
	//method
	/**
	 * @return a new {@link TextFormat} for the current {@link TextLineWidget}.
	 */
	protected final TextFormat getTextFormat() {
		
		//Extracts the of the current TextLineWidget.
		final var look = getRefLook();
		
		return
		new TextFormat(
			look.getRecursiveOrDefaultTextFont(),
			look.getRecursiveOrDefaultTextSize(),
			look.getRecursiveOrDefaultTextColor()
		);
	}
	
	//method
	/**
	 * @return a new specification of the text of the current {@link TextLineWidget}.
	 */
	protected final DocumentNode getTextSpecification() {
		return new DocumentNode(PascalCaseNameCatalogue.NAME, getText());
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final TLWL textLineWidgetLook, final IPainter painter) {
		painter.paintText(getShownText(), getTextFormat());
	}
}
