#  Notes about Bonus Tasks

Bonus Task 1:
a) Todo list is empty after returning from other screen to TodoList screen
   1. Open app
      ER/AR: Todo list filled with items
   2. Click on any item to open TodoDetailsFragment
   3. Press 'back' button to return to TodoListFragment
      ER: Todo list is filled with items
      AR: Todo list is empty

b) TodoDetails/TodoEdit overlap after rotation
   1. Open TodoDetails or TodoEdit screen
   2. Rotate device
   3. Press 'back' button
      ER: TodoDetails/TodoEdit is closed
      AR: TodoDetails/TodoEdit still showing on screen

c) SplashScreen: Retry button still not shown after error occurred
   1. Turn off the internet
   2. Open the app
   3. Receive toast about error on Splash screen
      ER: 'Retry' button is shown
      AR: Retry button still not visible

-------------------------------------------------------------------------------------------------

Bonus Task 2:
a) App crashed because in release version, code is being obfuscated. The problem is that minification affectes also classes used as argType via 'navigation.safeargs' (in navigation graph).
There was one such class in the project: Todo. Additional source and other solutions: https://developer.android.com/guide/navigation/navigation-pass-data#proguard_considerations

b) TodoList was empty because TodoListResponse and TodoResponse were obfuscated too.
Fixed mentioned issues by adding rule to proguard-rules.pro to keep classes in 'data' fodler for each module and moved mentioned classes there.
Also classes could be just anotated with @Keep (Since for example MetaReponse is not used), but then it is easy to forget to add mentioned @Keep annotation for newly created data objects.

-------------------------------------------------------------------------------------------------

Bonus Task 3:
a) MigrationsTest: Added validateMigration_1_3 / validateMigration_2_3 tests
b) UpdateTodoUseCaseTest: added update_itemUpdated test
c) TodoListViewModelTest: added load_itemsSortedProperly test. Refactored TodoListViewModel--GetTodoListUseCase composition to dependency inversion
d) TodoEditViewModelTest: implemented tests for title validation function

# Android Tech Task

The app fetches todo list from backend api, stores it to local database and shows the list to the
user.

## Tech stack:

* Network - <a href="https://square.github.io/retrofit/">Retrofit</a>
* Database - <a href="https://developer.android.com/training/data-storage/room">Room</a>
* Concurrency - <a href="https://github.com/ReactiveX/RxJava">RxJava2</a>
* Dependency injection - <a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt</a>
* UI - <a href="https://developer.android.com/topic/libraries/architecture/viewmodel">MVVM</a>, <a href="https://developer.android.com/topic/libraries/data-binding">DataBinding</a>, <a href="https://developer.android.com/guide/navigation/navigation-getting-started">NavComponents</a>.
* Unit tests - JUnit4, <a href="https://site.mockito.org/">Mockito</a>

## Structure:
App is modularized. Modules used in the app:
* api - network requests
* app - app and ui
* storage - local database
* usecase - business logic

## Screens and whats going on:
1. Splash - todo items are synced
2. TodoList - todo list is shown to user. Completed todo items are shown like this: <strike>This todo is completed</strike>
3. TodoDetails - details of the todo item.

## Main tasks
1. Our backend api just changed. `dueOn` properties was added to `TodoResonse`. Your task is to
   update the response, `Todo` item and local database.
   (p.s. Don't forget to handle database migration).

2. Users would like to see pending items with least amount of time left to complete at the top 
   of the screen. Your task is to implement items sorting. 
   Items in the `TodoList` screen should be sorted by the`dueOn` and `isCompleted`.

3. In the TodoDetails screen, when `Finished` or `Todo` button is clicked, app should update
   item's `isCompleted` and `updateAt` values and update the database. Changes should be reflected
   in`TodoDetails` and `TodoList` screens. If needed, you can use Time.now() to get current datetime 
   as a String.

4. When user clicks on the `Edit` button in the `TodoDetails` screen, a new, `UpdateTodo` screen
   should be shown (design is
   in  <a href="https://github.com/NordPass/android-tech-task/tree/master/uiSamples/editScreen.jpg">
   uiSamples/editScreen</a>). Here, user should be able to edit `Todo` item's title and save it. 
   Your task is to create a new screen, called `UpdateTodo`. It should have`EditText` and a save 
   button. `EditText` should be prefilled with the title of `Todo` item. Once save is clicked item's
   `title` and `updatedAt` values should be updated and saved in the local database. 
   `Title can not be empty` error should be shown if title is empty when user clicks save button and
   hidden when text is entered to the input field. 
   See UI in<a href="https://github.com/NordPass/android-tech-task/tree/master/uiSamples/editScreenError.jpg">
   uiSamples/editScreenError</a>. Changes should be reflected in `TodoDetails` and `TodoList`
   screens.

## Bonus tasks
* There are some bugs in the app. If you notice some, please fix.
* App does not work properly on release version. Could you figure out what is wrong and maybe fix it?
* Write some tests