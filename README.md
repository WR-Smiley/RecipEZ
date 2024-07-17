

# RecipeEZ Recipe Book — Software Engineering Capstone Android Application by WR Smiley

## User Guide

The following guide provides step-by-step instructions for utilizing each of the application’s features. This streamlined guide will help answer any questions about how the application functions during regular use.

### Creating New Users and Logging In

Launching the app will bring you to the main login screen. You must create login credentials to use the application. 

Tap the “New User” button on the login page to add a new user. This will take you to the new user screen, where you can create a new user to utilize the application (Figure 20). The new user screen features several fields with various validation protections to protect user data and ensure that required data is included. All fields are required. The fields contain hints to help you complete each of them. 

Emails must be formatted as a typical email, or they will be rejected; usernames must be unique, and the user will be informed if their selected username is already taken and passwords may not contain any characters or phrases that are characteristic of SQL queries (e.g., *, \\, DROP, DELETE, etc.). Passwords are hashed and stored in the local database. If all the fields are complete and validated, a new user will be created, and you will be directed back to the login screen.

You will be taken to the homepage after logging in with your new user credentials. This page will be mostly blank when using a new user account, but as you add Recipes they will appear here. Featured on this page, along with a Recipe list, is a search function that allows you to search for Recipes by name (partial matches included, in case you forget the exact name of one of your favorite Recipes) and a button that will allow you to add a new Recipe.

### Adding Recipes

Tapping the “Add New Recipe” button on the homepage will take you to a screen where you can add Recipes, including adding new Ingredients for your Recipes. The currently logged-in user will automatically be assigned as the creator of the recipe, and their name will accompany it whenever it is viewed or shared in the future.

To create a recipe, you will give it a name and fill out the fields for how many servings the recipe prepares and the estimated time to make the recipe, in minutes. You will then create the ingredients that make up the recipe and fill in the instructions for preparing the recipe. Each field is required, and you must add at least one ingredient associated with the recipe. Once you have completed filling out the form, tap “Save Recipe” to save your recipe to the app’s database, and it will show up on the homepage.

You can also delete ingredients once they have been added using the “Delete” button next to each ingredient. When tapping “Delete,” a message will tell you that the ingredient has been deleted. To update the ingredient list, use the “Refresh” button.

### Adding Ingredients

When you tap the “Add Ingredient” button, you will be taken to a new screen where you can create the ingredient. Here, you will give the ingredient a name, input the amount of the ingredient the recipe calls for (including fractions, if necessary), and choose whether the amount is a weight, which provides you the measurement unit options of grams or ounces, or volume, which provides you with the measurement unit options of cups, tablespoons, teaspoons, milliliters, or fluid ounces. Finally, you will decide which measurement unit you want to use for the ingredient. Saving the ingredient will add it to the app’s database, and it will now show up on the associated recipe.

### Viewing, Editing, and Sharing Recipes

Of course, after creating the recipes, you may want to view them later as a reference! After adding recipes, they will appear on the homepage. To view a recipe, tap on its entry in the list. If the recipe isn’t readily available on the list, you can search for it from the homepage. The search functionality is covered in greater detail in the following section. 

After tapping on the recipe, you will be taken to a view screen. The recipe cannot be edited, nor can ingredients be deleted from this screen. It is merely a reference screen for viewing the information associated with the recipe. However, you can share the recipe from this screen or tap “Edit Recipe” to edit it. Tapping on “Edit Recipe” will take you to the editing screen, which looks nearly identical to the screen for adding recipes. Here, you can change all of the recipe info, including adding and deleting ingredients as needed. Tapping “Update Recipe” will save your changes to the recipe and take you back to an updated view of the saved recipe.

Tapping “Share Recipe” on the viewing screen will automatically bundle the recipe in shareable format and present you with several options for sharing it with others. Choose your sharing method of choice and it will automatically populate a message for you to send.

### Searching for a Recipe

Once you’ve added many recipes to the app, it may become difficult to locate a specific recipe you want by scrolling the list on the homepage. To assist you in finding the recipe you are looking for, RecipEZ includes a simple search function directly on the homepage. This search box enables you to search for recipes by name; even if you’re not sure of the whole name of the recipe, you can search for partial matches (such as searching “Tomato” for all dishes with “Tomato” anywhere in the name). 

Inputting a query and pressing search will take you to a report showing the recipe's name, its creator's name, the date it was created, and the date it was last updated. You can view any recipe on the report by tapping its name. Tapping “Home” will take you back to the homepage.
