package com.ecab.driver;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ecab.driver");

        noClasses()
            .that()
            .resideInAnyPackage("com.ecab.driver.service..")
            .or()
            .resideInAnyPackage("com.ecab.driver.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ecab.driver.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
