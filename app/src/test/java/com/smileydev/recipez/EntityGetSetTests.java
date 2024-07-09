package com.smileydev.recipez;

import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import org.junit.Test;

import java.time.Instant;
import java.util.Date;

public class EntityGetSetTests {
    private Instant i = Instant.now();
    private Date now = Date.from(i);
    private User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
    private Recipe testRecipe = new Recipe("Test Recipe", now, now, 4, 30, testUser, 1, "Test Instructions");
    private Ingredient testIngredient = new Ingredient("Test Ingredient", now, now, "1/4", "Weight", "Cups", 1);

    @Test
    public void userGets() {
        assert testUser.getName().equals("John Doe") : "Expected John Doe. User.getName() returned: " + testUser.getName();
        assert testUser.getTitle().equals("Admin") : "Expected Admin. User.getTitle() returned: " + testUser.getTitle();
        assert testUser.getEmail().equals("email@email.com") : "Expected email@email.com. User.getEmail() returned: " + testUser.getEmail();
        assert testUser.getUsername().equals("admin") : "Expected admin. User.getUsername() returned: " + testUser.getUsername();
    }

    @Test
    public void userSets() {
        testUser.setId(5);
        assert testUser.getId() == 5 : "Expected 5. User.getID() returned: " + testUser.getId();
        testUser.setName("Jane Doe");
        assert testUser.getName().equals("Jane Doe") : "Expected Jane Doe. User.getName() returned: " + testUser.getName();
        testUser.setTitle("Administrator");
        assert testUser.getTitle().equals("Administrator") : "Expected Administrator. User.getTitle() returned: " + testUser.getTitle();
        testUser.setEmail("test@email.com");
        assert testUser.getEmail().equals("test@email.com") : "Expected test@email.com. User.getEmail() returned: " + testUser.getEmail();
        testUser.setUsername("janed1");
        assert testUser.getUsername().equals("janed1") : "Expected janed1. User.getUsername() returned: " + testUser.getUsername();
    }

    @Test
    public void recipeGets() {
        assert testRecipe.getName().equals("Test Recipe") : "Expected Test Recipe. Recipe.getName() returned " + testRecipe.getName();
        assert testRecipe.getPplServed() == 4 : "Expected 4. Recipe.getPplServed returned " + testRecipe.getPplServed();
        assert testRecipe.getTimeEstimate() == 30 : "Expected 30. Recipe.getTimeEstimate() returned " + testRecipe.getTimeEstimate();
        assert testRecipe.getInstructions().equals("Test Instructions") : "Expected Test Instructions. Recipe.getInstructions() returned " + testRecipe.getInstructions();
    }

    @Test
    public void recipeSets() {
        testRecipe.setId(1);
        assert testRecipe.getId() == 1 : "Expected 1. Recipe.getId() returned " + testRecipe.getId();
        testRecipe.setName("Tester");
        assert testRecipe.getName().equals("Tester") : "Expected Tester. Recipe.getName() returned " + testRecipe.getName();
        testRecipe.setPplServed(25);
        assert testRecipe.getPplServed() == 25 : "Expected 25. Recipe.getPplServed() returned " + testRecipe.getPplServed();
        testRecipe.setTimeEstimate(800);
        assert testRecipe.getTimeEstimate() == 800 : "Expected 800. Recipe.getTimeEstimate() returned " + testRecipe.getTimeEstimate();
        testRecipe.setInstructions("Instructions Testing");
        assert testRecipe.getInstructions().equals("Instructions Testing") : "Expected Instructions Testing. Recipe.getInstructions returned " + testRecipe.getInstructions();
    }

    @Test
    public void ingredientGets() {
        assert testIngredient.getName().equals("Test Ingredient") : "Expected Test Ingredient. Ingredient.getName() returned " + testIngredient.getName();
        assert testIngredient.getAmt().equals("1/4") : "Expected 1/4. Ingredient.getAmt() returned " + testIngredient.getAmt();
        assert testIngredient.getMeasurementType().equals("Weight") : "Expected Weight. Ingredient.getMeasurementType() returned " + testIngredient.getMeasurementType();
        assert testIngredient.getMeasurement().equals("Cups") : "Expected Cups. Ingredient.getMeasurement() returned " + testIngredient.getMeasurement();
        assert testIngredient.getRecipeId() == 1 : "Expected 1. Ingredient.getRecipeId() returned " + testIngredient.getRecipeId();
    }

    @Test
    public void ingredientSets() {
        testIngredient.setId(80);
        assert testIngredient.getId() == 80 : "Expected 80. Ingredient.getId() returned " + testIngredient.getId();
        testIngredient.setName("New Test Name");
        assert testIngredient.getName().equals("New Test Name") : "Expected New Test Name. Ingredient.getName() returned " + testIngredient.getName();
        testIngredient.setAmt("500");
        assert Integer.parseInt(testIngredient.getAmt()) == 500 : "Expected 500. Ingredient.getAmt() returned " + testIngredient.getAmt();
        testIngredient.setMeasurementType("Volume");
        assert testIngredient.getMeasurementType().equals("Volume") : "Expected Volume. Ingredient.getMeasurementType() returned " + testIngredient.getMeasurementType();
        testIngredient.setMeasurement("Milliliters");
        assert testIngredient.getMeasurement().equals("Milliliters") : "Expected Milliliters. Ingredient.getMeasurement() returned " + testIngredient.getMeasurement();
        testIngredient.setRecipeId(90);
        assert testIngredient.getRecipeId() == 90 : "Expected 90. Ingredient.getRecipeId() returned " + testIngredient.getRecipeId();
    }
}