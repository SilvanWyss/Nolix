package ch.nolix.commonarchitecturetest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class PublicConstructorTest {
  @Disabled
  @Test
  void testCase_publicConstructorsDoNotContainParameters() {
    //setup
    final var rule = //
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
              final var message = "The " + item.getFullName() + " constructor is public and contains parameters.";

              events.add(new SimpleConditionEvent(item, false, message));
            }
          }
        });
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    //execution & verification
    rule.check(testUnit);
  }
}
