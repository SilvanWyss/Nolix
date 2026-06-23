/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.style.model;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.control.button.Button;
import ch.nolix.system.style.model.DeepSelectingStyle;
import ch.nolix.systemapi.control.button.ButtonRole;
import ch.nolix.systemapi.control.label.LabelRole;

/**
 * @author Silvan Wyss
 */
final class DeepSelectingStyleTest extends StandardTest {
  @Test
  void testCase_getSpecification_whenHasSelectorIdAndContainsAttachingeAttributes() {
    //setup
    final var testUnit = //
    DeepSelectingStyle.EMPTY
      .withSelectorId("selector_id")
      .withAttachingAttributes("BaseBorderThickness(1)", "BaseBackgroundColor(Anthrazit)");

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
    DeepSelectingStyle.EMPTY
      .withAdditionalSelectorRoles(LabelRole.TITLE)
      .withAttachingAttributes("BaseTextSize(50)", "BaseTextColor(Black)");

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
    final var testUnit = DeepSelectingStyle.EMPTY;

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_skipsChildElements() {
    //setup
    final var testUnit = DeepSelectingStyle.EMPTY;

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_withSelectorTokens() {
    //setup
    final var testUnit = //
    DeepSelectingStyle.EMPTY
      .withSelectorId("id")
      .withSelectorType(Button.class)
      .withAdditionalSelectorRoles(ButtonRole.CREATE_BUTTON, ButtonRole.DELETE_BUTTON)
      .withAdditionalSelectorTokens("token1", "token2")
      .withAttachingAttributes("BaseTextSize(20)", "BaseTextColor(Black)");

    //execution
    final var result = testUnit.withAdditionalSelectorTokens("token3", "token4");

    //verification
    expect(result.getSelectorId()).isEqualTo("id");
    expect(result.getSelectorType()).isEqualTo("Button");
    expect(result.getSelectorRoles()).containsExactly("CREATE_BUTTON", "DELETE_BUTTON");
    expect(result.getSelectorTokens()).containsExactlyEqualing("token1", "token2", "token3", "token4");
  }
}
