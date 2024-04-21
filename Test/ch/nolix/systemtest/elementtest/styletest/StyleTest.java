//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.element.stylebuilder.SelectingStyleBuilder;
import ch.nolix.system.element.stylebuilder.StyleBuilder;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.WebGui;

//class
final class StyleTest extends StandardTest {

  //method
  @Test
  void testCase_styleElement() {

    //setup part 1: create WebGui
    final var webGui = new WebGui();

    //setup part 2: create testUnit
    final var testUnit = new Style(
      ImmutableList.withElement(
        Node.fromString("Title(my_title)"),
        Node.fromString("Background(Color(Blue))")),
      new ImmutableList<>());

    //execution
    testUnit.applyToElement(webGui);

    //verification
    expect(webGui.getTitle()).isEqualTo("my_title");
    expect(webGui.getBackgroundColor()).isEqualTo(Color.BLUE);
  }

  //method
  @Test
  void testCase_withAttachingAttributesAndSubStyles_whenIsEmpty() {

    //setup
    final var subStyle1 = new SelectingStyleBuilder().build();
    final var subStyle2 = new SelectingStyleBuilder().build();
    final var testUnit = new StyleBuilder().build();

    //execution
    final var result = testUnit.withAttachingAttributesAndSubStyles(
      ImmutableList.withElement("p1(v1)", "p2(v2)"),
      ImmutableList.withElement(subStyle1, subStyle2));

    //verification
    expect(result.getAttachingAttributes().toStrings()).containsExactlyEqualing("p1(v1)", "p2(v2)");
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(2);
    expect(subStyles.getStoredAt1BasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAt1BasedIndex(2)).is(subStyle2);
  }

  //method
  @Test
  void testCase_withAttachingAttributesAndSubStyles_whenContainsAny() {

    //setup
    final var subStyle1 = new SelectingStyleBuilder().build();
    final var subStyle2 = new SelectingStyleBuilder().build();
    final var subStyle3 = new SelectingStyleBuilder().build();
    final var subStyle4 = new SelectingStyleBuilder().build();
    final var testUnit = new StyleBuilder()
      .addAttachingAttribute("p1(v1)", "p2(v2)")
      .addSubStyle(subStyle1, subStyle2)
      .build();

    //execution
    final var result = testUnit.withAttachingAttributesAndSubStyles(
      ImmutableList.withElement("p3(v3)", "p4(v4)"),
      ImmutableList.withElement(subStyle3, subStyle4));

    //verification
    expect(result.getAttachingAttributes().toStrings())
      .containsExactlyEqualing("p1(v1)", "p2(v2)", "p3(v3)", "p4(v4)");
    final var subStyles = result.getSubStyles();
    expect(subStyles).hasElementCount(4);
    expect(subStyles.getStoredAt1BasedIndex(1)).is(subStyle1);
    expect(subStyles.getStoredAt1BasedIndex(2)).is(subStyle2);
    expect(subStyles.getStoredAt1BasedIndex(3)).is(subStyle3);
    expect(subStyles.getStoredAt1BasedIndex(4)).is(subStyle4);
  }
}
