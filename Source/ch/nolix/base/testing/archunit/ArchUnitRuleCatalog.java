/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.archunit;

import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

/**
 * Of the {@link ArchUnitRuleCatalog} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class ArchUnitRuleCatalog {
  public static final ArchRule PUBLIC_CONSTRUCTORS_DO_NOT_CONTAIN_PARAMETERS = //
  ArchRuleDefinition
    .constructors()
    .that()
    .arePublic()
    .and()
    .areDeclaredInClassesThat()
    .areNotRecords()
    .and()
    .areDeclaredInClassesThat()
    .haveModifier(JavaModifier.FINAL)
    .should(
      new ArchCondition<JavaConstructor>("Public constructors do not contain parameters.") {
        @Override
        public void check(final JavaConstructor item, final ConditionEvents events) {
          if (!item.getParameters().isEmpty()) {
            final var message = "The constructor '" + item.getFullName() + "' is public and contains parameters.";

            events.add(new SimpleConditionEvent(item, false, message));
          }
        }
      });

  /**
   * Prevents that an instance of the {@link ArchUnitRuleCatalog} can be created.
   */
  private ArchUnitRuleCatalog() {
  }
}
