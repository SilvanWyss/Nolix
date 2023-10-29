//package declaration
package ch.nolix.system.webgui.main;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//record
public record HtmlElementEvent(String htmlElementId, String htmlEvent) implements IHtmlElementEvent {

  //static method
  public static HtmlElementEvent withHtmlElementIdAndHtmlEvent(final String htmlElementId, final String htmlEvent) {
    return new HtmlElementEvent(htmlElementId, htmlEvent);
  }

  //constructor
  public HtmlElementEvent( //NOSONAR: This constructor does more than the default one.
    final String htmlElementId,
    final String htmlEvent) {

    GlobalValidator.assertThat(htmlElementId).thatIsNamed("HTML element id").isNotBlank();
    GlobalValidator.assertThat(htmlEvent).thatIsNamed("HTML event").isNotBlank();

    this.htmlElementId = htmlElementId;
    this.htmlEvent = htmlEvent;
  }

  //method
  @Override
  public String getHtmlElementId() {
    return htmlElementId;
  }

  //method
  @Override
  public String getHtmlEvent() {
    return htmlEvent;
  }
}
