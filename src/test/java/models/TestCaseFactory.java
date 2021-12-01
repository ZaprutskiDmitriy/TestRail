package models;

import com.github.javafaker.Faker;

public class TestCaseFactory {

    static Faker faker;

    public static TestCase get() {
        faker = new Faker();
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
}