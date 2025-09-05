package ch.nolix.architecturetest;

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
  @Test
  @Disabled
  void testCase_publicConstructorsAreDefaultConstructors() {
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
        new ArchCondition<JavaConstructor>("have public constructors only without parameters") {
          @Override
          public void check(final JavaConstructor item, final ConditionEvents events) {
            final var isDefaultConstructor = item.getParameters().isEmpty();
            final var message = item.getFullName() + " is not a default constructor";

            events.add(new SimpleConditionEvent(item, isDefaultConstructor, message));
          }
        });
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix...");

    //execution & verification
    rule.check(testUnit);
  }
}
