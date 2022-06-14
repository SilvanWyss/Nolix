//package declaration
package ch.nolix.system.gui.widget;

import ch.nolix.core.commontype.constant.StringCatalogue;
//own imports
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.core.functionuniversalapi.IElementTaker;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui.textformat.TextFormat;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * A {@link TextLineWidget} is a {@link Widget} that contains 1 text line.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <TLW> is the type of a {@link TextLineWidget}.
 * @param <TLWL> is the type of the {@link BorderWidgetLook} of a {@link TextLineWidget}.
 */
public abstract class TextLineWidget<TLW extends TextLineWidget<TLW, TLWL>, TLWL extends BorderWidgetLook<TLWL>>
extends BorderWidget<TLW, TLWL> {
	
	//constant
	private static final String TEXT_HEADER = PascalCaseCatalogue.TEXT;
	
	//attribute
	private MutableValue<String> text = MutableValue.forString(TEXT_HEADER, getDefaultText(), this::setText);
	
	//optional attribute
	private IElementTaker<String> noteTextUpdateAction;
	
	//method
	/**
	 * Empties the text of the current {@link TextLineWidget}.
	 */
	public final void emptyText() {
		setText(StringCatalogue.EMPTY_STRING);
	}
	
	//method declaration
	/**
	 * @return the shown text of the current {@link TextLineWidget}.
	 */
	public abstract String getShownText();
	
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
		
		GlobalValidator.assertThat(noteTextUpdateAction).thatIsNamed("note text update action").isNotNull();
		
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
	protected final boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {}
	
	//method declaration
	/**
	 * @return the default text of the current {@link TextLineWidget}.
	 */
	protected abstract String getDefaultText();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getNaturalContentAreaHeight() {
		return new TextFormat(getRefActiveLook().getTextSize()).getTextHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getNaturalContentAreaWidth() {
		return (getTextFormat().getSwingTextWidth(getShownText()) + getTextWidthAddition());
	}
	
	//method
	/**
	 * @return a new {@link TextFormat} for the current {@link TextLineWidget}.
	 */
	protected final TextFormat getTextFormat() {
		
		//Extracts the of the current TextLineWidget.
		final var look = getRefActiveLook();
		
		return
		new TextFormat(
			look.getFont(),
			look.getTextSize(),
			look.getTextColor()
		);
	}
	
	//method
	/**
	 * @return the value that have to be added to the width of the text of the current {@link TextLineWidget}.
	 */
	protected abstract int getTextWidthAddition();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingOnSelfWhenFocused(final Key key) {}
	
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
	protected final void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(
		final RotationDirection rotationDirection
	) {}
	
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
	protected final void paintContentArea(final IPainter painter, final TLWL textLineWidgetLook) {
		
		painter.paintText(getShownText(), getTextFormat());
		
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetBorderWidget() {
		
		setText(getDefaultText());
		
		resetTextLineWidget();
	}
	
	//method declaration
	/**
	 * Resets the current {@link TextLineWidget}.
	 */
	protected abstract void resetTextLineWidget();
	
	//method
	protected void setTextWhenHasOtherText(final String text) {
		
		this.text.setValue(text);
		
		if (hasNoteTextUpdateAction()) {
			noteTextUpdateAction.run(text);
		}
	}
}
