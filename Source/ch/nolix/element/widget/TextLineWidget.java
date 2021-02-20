//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.painterapi.IPainter;
import ch.nolix.element.textformat.TextFormat;

//class
/**
 * A {@link TextLineWidget} is a {@link Widget} that contains 1 text line.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 290
 * @param <TLW> is the type of a {@link TextLineWidget}.
 * @param <TLWL> is the type of the {@link TextLineWidgetLook} of a {@link TextLineWidget}.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW, TLWL>, TLWL extends TextLineWidgetLook<TLWL>>
extends BorderWidget<TLW, TLWL> {
	
	//attribute
	private MutableValue<String> text =
	new MutableValue<>(
		PascalCaseCatalogue.TEXT,
		StringCatalogue.EMPTY_STRING,
		this::setText,
		s -> {
			
			if (!s.containsAttributes()) {
				return  StringCatalogue.EMPTY_STRING;
			}
			
			return s.getOneAttributeHeader();
		},
		t -> {
			
			final var specification = new Node();
			if (!t.isEmpty()) {
				specification.addAttribute(t);
			}
			
			return specification;
		}
	);
	
	//optional attribute
	private IElementTaker<String> noteTextUpdateAction;
	
	//method
	/**
	 * Empties the text of the current {@link TextLineWidget}.
	 */
	public final void emptyText() {
		setText(StringCatalogue.EMPTY_STRING);
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
	 * @return true if the current {@link TextLineWidget} has a note text update action.
	 */
	public final boolean hasNoteTextUpdateAction() {
		return (noteTextUpdateAction != null);
	}
	
	//method
	/**
	 * @param text
	 * @return true if the current {@link TextLineWidget} has the given text.
	 */
	public final boolean hasText(final String text) {
		return getText().equals(text);
	}
	
	//method
	/**
	 * Removes the note text update action of the current {@link TextLineWidget}.
	 */
	public final void removeNoteTextUpdateAction() {
		noteTextUpdateAction = null;
	}
	
	//method
	/**
	 * Sets the given noteTextUpdateAction to the current {@link TextLineWidget}.
	 * 
	 * @param noteTextUpdateAction
	 * @return the current {@link TextLineWidget}.
	 * @throws ArgumentIsNullException if the given noteTextUpdateAction is null.
	 */
	public final TLW setNoteTextUpdateAction(final IAction noteTextUpdateAction) {
		return setNoteTextUpdateAction(t -> noteTextUpdateAction.run());
	}
	
	//method
	/**
	 * Sets the given noteTextUpdateAction to the current {@link TextLineWidget}.
	 * 
	 * @param noteTextUpdateAction
	 * @return the current {@link TextLineWidget}.
	 * @throws ArgumentIsNullException if the given noteTextUpdateAction is null.
	 */
	public final TLW setNoteTextUpdateAction(final IElementTaker<String> noteTextUpdateAction) {
		
		Validator.assertThat(noteTextUpdateAction).thatIsNamed("note text update action").isNotNull();
		
		this.noteTextUpdateAction = noteTextUpdateAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the text of the current {@link TextLineWidget}.
	 * 
	 * @param text
	 * @return the current {@link TextLineWidget}.
	 * @throws ArgumentIsNullException if the given text is null.
	 */
	public final TLW setText(final String text) {
		
		if (!hasText(text)) {
			setTextWhenHasOtherText(text);
		}
		
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
	protected final int getNaturalContentAreaWidth() {
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
		
		paintTextLineWidgetContentArea(painter, textLineWidgetLook);
	}
	
	//method declaration
	/**
	 * Paints the content area of the current {@link TextLineWidget} using the given painter and textLineWidgetLook.
	 * 
	 * @param painter
	 * @param textLineWidgetLook
	 */
	protected abstract void paintTextLineWidgetContentArea(final IPainter painter, final TLWL textLineWidgetLook);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void recalculateBorderWidget() {}
	
	//method
	private void setTextWhenHasOtherText(final String text) {
		
		this.text.setValue(text);
		
		if (hasNoteTextUpdateAction()) {
			noteTextUpdateAction.run(text);
		}
	}
}
