package ch.nolix.systemapi;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class SystemApiDependencyTest {

  @Test
  void testCase_chnolixsystemapi_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.systemapi..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage("ch.nolix.coreapi..", "ch.nolix.systemapi..", "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.systemapi..");

    //execution & verification
    rule.check(testUnit);
  }
}
