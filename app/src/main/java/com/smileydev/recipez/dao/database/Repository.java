package com.smileydev.recipez.dao.database;

import android.app.Application;

import com.smileydev.recipez.dao.IngredientDAO;
import com.smileydev.recipez.dao.RecipeDAO;
import com.smileydev.recipez.dao.UserDAO;
import com.smileydev.recipez.entities.Ingredient;
import com.smileydev.recipez.entities.Recipe;
import com.smileydev.recipez.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private RecipeDAO mRDao;
    private IngredientDAO mIDao;

    private UserDAO mUDao;

    private List<Recipe> mRecipes;
    private List<Ingredient> mIngredients;
    private List<User> mUsers;

    private Recipe mRecipe;
    private User mUser;

    private static int THREADS = 5;
    static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public Repository(Application application) {
        RecipeDatabase db = RecipeDatabase.getDatabase(application);
        mRDao = db.mRDao();
        mIDao = db.mIDao();
        mUDao = db.mUDao();
    }

    public Recipe getRecipe(int id) {
        dbExecutor.execute(() -> {
            mRecipe = mRDao.getRecipe(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mRecipe;
    }
    public User getUser(int id) {
        dbExecutor.execute(() -> {
            mUser = mUDao.getUser(id);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mUser;
    }
    public List<Recipe> getAllRecipes() {
        dbExecutor.execute(() -> {
            mRecipes = mRDao.getAllRecipes();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mRecipes;
    }
    public List<Ingredient> getAllIngredients() {
        dbExecutor.execute(() -> {
            mIngredients = mIDao.getAllIngredients();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mIngredients;
    }
    public List<User> getAllUsers() {
        dbExecutor.execute(() -> {
            mUsers = mUDao.getAllUsers();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return mUsers;
    }

    public void insert(Recipe recipe) {
        dbExecutor.execute(() -> {
            mRDao.insert(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Recipe recipe) {
        dbExecutor.execute(() -> {
            mRDao.update(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Recipe recipe) {
        dbExecutor.execute(() -> {
            mRDao.delete(recipe);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Ingredient ingredient) {
        dbExecutor.execute(() -> {
            mIDao.insert(ingredient);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Ingredient ingredient) {
        dbExecutor.execute(() -> {
            mIDao.update(ingredient);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Ingredient ingredient) {
        dbExecutor.execute(() -> {
            mIDao.delete(ingredient);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void insert(User user) {
        dbExecutor.execute(() -> {
            mUDao.insert(user);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(User user) {
        dbExecutor.execute(() -> {
            mUDao.update(user);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(User user) {
        dbExecutor.execute(() -> {
            mUDao.delete(user);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
