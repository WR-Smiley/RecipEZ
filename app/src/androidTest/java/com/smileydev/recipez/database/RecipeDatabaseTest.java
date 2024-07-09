package com.smileydev.recipez.database;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.smileydev.recipez.dao.IngredientDAO;
import com.smileydev.recipez.dao.RecipeDAO;
import com.smileydev.recipez.dao.UserDAO;
import com.smileydev.recipez.dao.database.RecipeDatabase;
import com.smileydev.recipez.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

public class RecipeDatabaseTest {
    private UserDAO uDao;
    private IngredientDAO iDao;
    private RecipeDAO rDao;
    private RecipeDatabase db;
    Instant i = Instant.now();
    Date now = Date.from(i);

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
    public void writeUserAndReadInList() throws Exception {
        User testUser = new User("John Doe", now, now, "Admin", "email@email.com", "admin", "password");
        testUser.setId(3);
        uDao.insert(testUser);
        String name = uDao.getUser(3).getName();
        assert name.equals("John Doe") : "Expected John Doe. User.getName() returned: " + name;
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
        assert testUser.getPassword().equals(encryptedNewPassword) : "Expected passwords to match. Results:\nUser password: " + testUser.getPassword() + "\nHashed Password: " + encryptedNewPassword;
    }
}