/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.archunit;

import com.tngtech.archunit.core.domain.JavaClass;
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
  public static final ArchRule NON_ANONYMOUS_CLASSES_ARE_ABSTRACT_OR_FINAL = //
  ArchRuleDefinition
    .classes()
    .that()
    .areNotAnonymousClasses()
    .should()
    .haveModifier(JavaModifier.ABSTRACT)
    .orShould()
    .haveModifier(JavaModifier.FINAL);

  public static final ArchRule PUBLIC_AND_PACKAGE_VISIBLE_CONSTRUCTORS_DO_NOT_CONTAIN_PARAMETERS = //
  ArchRuleDefinition
    .constructors()
    .that()
    .arePublic()
    .or()
    .arePackagePrivate()
    .and()
    .areDeclaredInClassesThat()
    .areNotRecords()
    .and()
    .areDeclaredInClassesThat()
    .haveModifier(JavaModifier.FINAL)
    .should(
      new ArchCondition<JavaConstructor>("should not contain parameters.") {
        @Override
        public void check(final JavaConstructor item, final ConditionEvents events) {
          if (!item.getParameters().isEmpty()) {
            final var message = //
            "The public or package-visible constructor '" + item.getFullName() + "' contains parameters.";

            events.add(new SimpleConditionEvent(item, false, message));
          }
        }
      });

  public static final ArchRule PUBLIC_CLASSES_DO_NOT_CONTAIN_NESTED_CLASSES = //
  ArchRuleDefinition
    .classes()
    .that()
    .areNotAnonymousClasses()
    .and()
    .areNestedClasses()
    .should(new ArchCondition<JavaClass>("should not belong to public classes.") {
      @Override
      public void check(JavaClass item, ConditionEvents events) {
        final var optionalEnclosingClass = item.getEnclosingClass();

        if (optionalEnclosingClass.isPresent()) {
          final var enclosingClass = optionalEnclosingClass.get();

          if (enclosingClass.getModifiers().contains(JavaModifier.PUBLIC)) {
            final var message = "The public class '" + enclosingClass.getName() + "' contains nested classes.";

            events.add(new SimpleConditionEvent(enclosingClass, false, message));
          }
        }
      }
    });

  public static final ArchRule PUBLIC_MEMBER_METHODS_RETURN_A_PRIMITIVE_BOOLEAN = //
  ArchRuleDefinition
    .methods()
    .that()
    .areNotStatic()
    .and()
    .arePublic()
    .should()
    .haveRawReturnType("boolean");

  /**
   * Prevents that an instance of the {@link ArchUnitRuleCatalog} can be created.
   */
  private ArchUnitRuleCatalog() {
  }
}
