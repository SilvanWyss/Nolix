//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.painterapi.IPainter;
import ch.nolix.element.textformat.TextFormat;

//class
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
		text.setValue(DEFAULT_TEXT);
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
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
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
	protected final int getNaturalContentAreaHeight() {
		return new TextFormat(getRefLook().getRecursiveOrDefaultTextSize()).getTextHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
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
	protected final void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
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
	protected final void paintContentArea(final TLWL textLineWidgetLook, final IPainter painter) {
		
		painter.paintText(getText(), getTextFormat());
		
		paintContentAreaStage2(painter, textLineWidgetLook);
	}
	
	//method declaration
	/**
	 * {@inheritDoc}
	 */
	protected abstract void paintContentAreaStage2(final IPainter painter, final TLWL textLineWidgetLook);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateSelfStage2() {}
}
