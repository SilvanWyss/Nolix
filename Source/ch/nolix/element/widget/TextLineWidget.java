//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
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
 * @lines 200
 * @param <TLW> is the type of a {@link TextLineWidget}.
 * @param <TLWL> is the type of the {@link TextLineWidgetLook} of a {@link TextLineWidget}.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW, TLWL>, TLWL extends TextLineWidgetLook<TLWL>>
extends BorderWidget<TLW, TLWL> {
		
	//attribute
	private MutableValue<String> text =
	new MutableValue<>(
		PascalCaseNameCatalogue.TEXT,
		StringCatalogue.EMPTY_STRING,
		this::setText,
		s -> s.containsOneAttribute() ? s.getOneAttributeHeader() : StringCatalogue.EMPTY_STRING,
		t -> t.isEmpty() ? new Node() : Node.withAttribute(t)
	);
	
	//method
	/**
	 * @return the text of the current {@link TextLineWidget}.
	 */
	public final String getText() {
		return text.getValue();
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
	 * Paints the content area of the current {@link TextLineWidget} using the given painter and textLineWidgetLook.
	 * 
	 * @param painter
	 * @param textLineWidgetLook
	 */
	protected abstract void paintContentAreaStage2(final IPainter painter, final TLWL textLineWidgetLook);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateSelfStage2() {}
}
