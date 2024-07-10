package com.smileydev.recipez.database;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.smileydev.recipez.dao.IngredientDAO;
import com.smileydev.recipez.dao.RecipeDAO;
import com.smileydev.recipez.dao.UserDAO;
import com.smileydev.recipez.dao.database.RecipeDatabase;
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
public class RecipeDatabaseTest {
    private UserDAO uDao;
    private IngredientDAO iDao;
    private RecipeDAO rDao;
    private RecipeDatabase db;
    Instant i = Instant.now();
    Date now = Date.from(i);

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void createDatabase() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, RecipeDatabase.class).build();
        uDao = db.mUDao();
        iDao = db.mIDao();
        rDao = db.mRDao();
    }

    @After
    public void closeDatabase() {
        db.close();
    }

    @Test
    public void writeUserAndReadfromDb() throws Exception {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        testUser.setId(1);
        uDao.insert(testUser);
        String name = uDao.getUser(1).getName();
        try {
            assertThat(name, equalTo("John Doe"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void writeRecipeAndReadfromDb() throws Exception {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        Recipe testRecipe = new Recipe("Tomatoes", now, now, 6, 60, testUser, 1, "Test");
        testRecipe.setId(1);
        rDao.insert(testRecipe);
        String createdBy = rDao.getRecipe(1, 1).getCreatedBy().getName();
        try {
            assertThat(createdBy, equalTo("John Doe"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void writeIngredientAndReadfromDb() throws Exception {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        testUser.setId(5);
        Recipe testRecipe = new Recipe("Tomatoes", now, now, 6, 60, testUser, 5, "Test");
        testRecipe.setId(1);
        Ingredient testIngredient = new Ingredient("Tomato", now, now, "60", "Weight", "Grams", 1);
        testIngredient.setId(3);
        iDao.insert(testIngredient);
        String ingredientAmt = iDao.getIngredient(3).getAmt();
        try {
            assertThat(ingredientAmt, equalTo("60"));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void autoIdTest() throws Exception {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        User testUser2 = new User("John Doe", now, now, "Admin", "email@email.com", "admin2", "password");
        uDao.insert(testUser);
        uDao.insert(testUser2);
        try {
            assertThat(uDao.getUser(1).getId(), equalTo(1));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }

    @Test
    public void passwordHashingTest() {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        String password = testUser.getPassword();
        String encryptedPassword = null;
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(password.getBytes());
        byte[] bytes = digest.digest();
        StringBuilder passwordBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            passwordBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        encryptedPassword = passwordBuilder.toString();
        testUser.setPassword(encryptedPassword);
        String newPassword = "password";

        String encryptedNewPassword = null;
        MessageDigest newDigest = null;
        try {
            newDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        newDigest.update(password.getBytes());
        byte[] newBytes = newDigest.digest();
        StringBuilder newPasswordBuilder = new StringBuilder();
        for (int i = 0; i < newBytes.length; i++) {
            passwordBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        encryptedNewPassword = passwordBuilder.toString().substring(0, 32);
        try {
            assertThat(testUser.getPassword(), equalTo(encryptedNewPassword));
        } catch (Throwable t) {
            collector.addError(t);
        }
    }
}