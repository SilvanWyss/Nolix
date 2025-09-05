package ch.nolix.system.webgui.main;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

//record
public record HtmlElementEvent(String htmlElementId, String htmlEvent) implements IHtmlElementEvent {
  public static HtmlElementEvent withHtmlElementIdAndHtmlEvent(final String htmlElementId, final String htmlEvent) {
    return new HtmlElementEvent(htmlElementId, htmlEvent);
  }

  public HtmlElementEvent( //NOSONAR: This constructor does more than the default one.
    final String htmlElementId,
    final String htmlEvent) {
    Validator.assertThat(htmlElementId).thatIsNamed("HTML element id").isNotBlank();
    Validator.assertThat(htmlEvent).thatIsNamed("HTML event").isNotBlank();

    this.htmlElementId = htmlElementId;
    this.htmlEvent = htmlEvent;
  }

  @Override
  public String getHtmlElementId() {
    return htmlElementId;
  }

  @Override
  public String getHtmlEvent() {
    return htmlEvent;
  }
}
