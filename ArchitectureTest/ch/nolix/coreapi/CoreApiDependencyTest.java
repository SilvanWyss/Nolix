package ch.nolix.coreapi;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

final class CoreApiDependencyTest {

  @Test
  void testCase_chnolixcoreapi_package() {

    //setup
    final var rule = //
    ArchRuleDefinition
      .classes()
      .that()
      .resideInAPackage("ch.nolix.coreapi..")
      .and()
      .haveNameNotMatching(".*Test$")
      .should()
      .onlyDependOnClassesThat()
      .resideInAnyPackage("ch.nolix.coreapi..", "java..");
    final var testUnit = new ClassFileImporter().importPackages("ch.nolix.coreapi..");

    //execution & verification
    rule.check(testUnit);
  }
}
