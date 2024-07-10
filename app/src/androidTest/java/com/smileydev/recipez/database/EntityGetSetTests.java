package com.smileydev.recipez.database;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.time.Instant;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class EntityGetSetTests {
    private Instant i = Instant.now();
    private Date now = Date.from(i);
    private User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
    private Recipe testRecipe = new Recipe("Test Recipe", now, now, 4, 30, testUser, 1, "Test Instructions");
    private Ingredient testIngredient = new Ingredient("Test Ingredient", now, now, "1/4", "Weight", "Cups", 1);

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void userGets() {
        try {
            assertThat(testUser.getName(), equalTo("John Doe"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testUser.getTitle(), equalTo("Admin"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testUser.getEmail(), equalTo("email@email.com"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testUser.getUsername(), equalTo("admin"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void userSets() {
        testUser.setId(5);
        try {
            assertThat(testUser.getId(), equalTo(5));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testUser.setName("Jane Doe");
        try {
            assertThat(testUser.getName(), equalTo("Jane Doe"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testUser.setTitle("Administrator");
        try {
            assertThat(testUser.getTitle(), equalTo("Administrator"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testUser.setEmail("test@email.com");
        try {
            assertThat(testUser.getEmail(), equalTo("test@email.com"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testUser.setUsername("janed1");
        try {
            assertThat(testUser.getUsername(), equalTo("janed1"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void recipeGets() {
        try {
            assertThat(testRecipe.getName(), equalTo("Test Recipe"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testRecipe.getPplServed(), equalTo(4));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testRecipe.getTimeEstimate(), equalTo(30));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testRecipe.getInstructions(), equalTo("Test Instructions"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void recipeSets() {
        testRecipe.setId(1);
        try {
            assertThat(testRecipe.getId(), equalTo(1));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testRecipe.setName("Tester");
        try {
            assertThat(testRecipe.getName(), equalTo("Tester"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testRecipe.setPplServed(25);
        try {
            assertThat(testRecipe.getPplServed(), equalTo(25));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testRecipe.setTimeEstimate(800);
        try {
            assertThat(testRecipe.getTimeEstimate(), equalTo(800));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testRecipe.setInstructions("Instructions Testing");
        try {
            assertThat(testRecipe.getInstructions(), equalTo("Instructions Testing"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void ingredientGets() {
        try {
            assertThat(testIngredient.getName(), equalTo("Test Ingredient"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testIngredient.getAmt(), equalTo("1/4"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testIngredient.getMeasurementType(), equalTo("Weight"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testIngredient.getMeasurement(), equalTo("Cups"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        try {
            assertThat(testIngredient.getRecipeId(), equalTo(1));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void ingredientSets() {
        testIngredient.setId(80);
        try {
            assertThat(testIngredient.getId(), equalTo(80));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testIngredient.setName("New Test Name");
        try {
            assertThat(testIngredient.getName(), equalTo("New Test Name"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testIngredient.setAmt("500");
        try {
            assertThat(Integer.parseInt(testIngredient.getAmt()), equalTo(500));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testIngredient.setMeasurementType("Volume");
        try {
            assertThat(testIngredient.getMeasurementType(), equalTo("Volume"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testIngredient.setMeasurement("Milliliters");
        try {
            assertThat(testIngredient.getMeasurement(), equalTo("Milliliters"));
        } catch (Throwable t) {
            collector.addError(t);
        }
        testIngredient.setRecipeId(90);
        try {
            assertThat(testIngredient.getRecipeId(), equalTo(90));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }
}