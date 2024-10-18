package ch.nolix.system.webgui.main;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//record
public record HtmlElementEvent(String htmlElementId, String htmlEvent) implements IHtmlElementEvent {

  public static HtmlElementEvent withHtmlElementIdAndHtmlEvent(final String htmlElementId, final String htmlEvent) {
    return new HtmlElementEvent(htmlElementId, htmlEvent);
  }

  public HtmlElementEvent( //NOSONAR: This constructor does more than the default one.
    final String htmlElementId,
    final String htmlEvent) {

    GlobalValidator.assertThat(htmlElementId).thatIsNamed("HTML element id").isNotBlank();
    GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isNotBlank();

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
