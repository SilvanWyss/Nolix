//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.gui.ValueCatalogue;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.textformat.TextFormat;

//class
/**
 * A {@link TextLineWidget} is a {@link Widget} that contains 1 text line.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 240
 * @param <TLW> The type of a {@link TextLineWidget}.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW, TLWL>, TLWL extends TextLineWidgetLook<TLWL>>
extends BorderWidget<TLW, TLWL> {
	
	//constant
	public static final String DEFAULT_TEXT = StringCatalogue.EMPTY_STRING;
	
	//attribute
	private MutableValue<String> text =
	new MutableValue<>(
		PascalCaseNameCatalogue.TEXT,
		DEFAULT_TEXT,
		this::setText,
		s -> s.containsOneAttribute() ? s.getOneAttributeHeader() : StringCatalogue.EMPTY_STRING,
		t -> t.isEmpty() ? new Node() : Node.withAttribute(t)
	);
	
	//constructor
	public TextLineWidget() {
		
		//TODO: Fix initializing of values.
		text.setValue(DEFAULT_TEXT);
	}
	
	//method
	/**
	 * @return the shown text of the current {@link TextLineWidget}.
	 */
	public final String getShownText() {
		
		return getText();
		
		//TODO: Handle the case that the current TextLineWidget shortens its text when it has a limited width.
		/*
		if (shortensShownTextWhenHasLimitedWidth()) {
			return getTextFormat().getFirstPart(getText(), getContentAreaWidth(), true);
		}
		*/
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
	public void reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setText(DEFAULT_TEXT);
	}
	
	//method
	/**
	 * Sets the text of the current {@link TextLineWidget}.
	 * 
	 * @param text
	 * @return the current {@link TextLineWidget}.
	 * @throws ArgumentIsNullException if the given text is null.
	 */
	public TLW setText(final String text) {
		
		this.text.setValue(text);
		
		return asConcrete();
	}
	
	//method declaration
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
	protected final void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {}

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
		
		//TODO: Handle the case that the current TextLineWidget shortens its text when it has a limited width.
		/*
		if (shortensShownTextWhenHasLimitedWidth()) {
			
			final var look = getRefLook();
			
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
		*/
		
		//Handles the case that the current TextLineWidget does not shorten its text when it has a limited width.
		return getTextFormat().getSwingTextWidth(getShownText());
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
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final TLWL textLineWidgetLook, final IPainter painter) {
		painter.paintText(getShownText(), getTextFormat());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateSelfStage2() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelf() {
		
		super.resetConfigurationOnSelf();
		
		getRefBaseLook()
		.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE)
		.setTextColor(Color.BLACK);
	}
}
