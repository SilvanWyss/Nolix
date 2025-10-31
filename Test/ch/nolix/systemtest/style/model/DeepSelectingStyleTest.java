package ch.nolix.systemtest.style.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.system.webatomiccontrol.button.Button;
import ch.nolix.systemapi.webatomiccontrol.button.ButtonRole;
import ch.nolix.systemapi.webatomiccontrol.label.LabelRole;

final class DeepSelectingStyleTest extends StandardTest {
  @Test
  void testCase_getSpecification_whenHasSelectorIdAndContainsAttachingeAttributes() {
    //setup
    final var testUnit = //
    new DeepSelectingStyle()
      .withSelectorId("selector_id")
      .withAttachingAttribute("BaseBorderThickness(1)", "BaseBackgroundColor(Anthrazit)");

    //execution
    final var specification = testUnit.getSpecification();

    //verification
    final var expectedStringRepresentation = //
    "DeepSelectingStyle(SelectorId(selector_id),AttachingAttribute(BaseBorderThickness(1)),AttachingAttribute(BaseBackgroundColor(Anthrazit)))";
    expect(specification).hasStringRepresentation(expectedStringRepresentation);
  }

  @Test
  void testCase_getSpecification_whenHasSelectorRoleAndContainsAttachingeAttributes() {
    //setup
    final var testUnit = //
    new DeepSelectingStyle()
      .withSelectorRole(LabelRole.TITLE)
      .withAttachingAttribute("BaseTextSize(50)", "BaseTextColor(Black)");

    //execution
    final var specification = testUnit.getSpecification();

    //verification
    final var expectedStringRepresentation = //
    "DeepSelectingStyle(SelectorRole(TITLE),AttachingAttribute(BaseTextSize(50)),AttachingAttribute(BaseTextColor(Black)))";
    expect(specification).hasStringRepresentation(expectedStringRepresentation);
  }

  @Test
  void testCase_selectsChildElements() {
    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_skipsChildElements() {
    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_withSelectorTokens() {
    //setup
    final var testUnit = //
    new DeepSelectingStyle()
      .withSelectorId("id")
      .withSelectorType(Button.class)
      .withSelectorRole(ButtonRole.CREATE_BUTTON, ButtonRole.DELETE_BUTTON)
      .withSelectorToken("token1", "token2")
      .withAttachingAttribute("BaseTextSize(20)", "BaseTextColor(Black)");

    //execution
    final var result = testUnit.withSelectorToken("token3", "token4");

    //verification
    expect(result.getSelectorId()).isEqualTo("id");
    expect(result.getSelectorType()).isEqualTo("Button");
    expect(result.getSelectorRoles()).containsExactly("CREATE_BUTTON", "DELETE_BUTTON");
    expect(result.getSelectorTokens()).containsExactlyEqualing("token1", "token2", "token3", "token4");
  }
}
