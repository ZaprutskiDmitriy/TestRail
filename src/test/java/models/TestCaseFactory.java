package models;

import com.github.javafaker.Faker;

public class TestCaseFactory {

    static Faker faker = new Faker();

    public static TestCase getCase() {
        return new TestCase(faker.company().industry(),
                "",
                "Test Case (Text)",
                "Functional",
                "High",
                faker.number().digit(),
                faker.internet().url(),
                "Ranorex",
                faker.howIMetYourMother().catchPhrase(),
                faker.howIMetYourMother().quote(),
                faker.howIMetYourMother().highFive());
    }

    public static TestCase getSecondCase() {
        return new TestCase(faker.company().name(),
                "",
                "Test Case (Text)",
                "Regression",
                "Medium",
                faker.number().digit(),
                faker.internet().url(),
                "Ranorex",
                faker.lordOfTheRings().character(),
                faker.yoda().quote(),
                faker.lordOfTheRings().location());
    }
}