# ToDoing

## Introduction

In this project, we will learn how to create a Todo webpage named as ToDoing. This webpage can be used to make Todo lists, which are highly beneficial to prioritise and store daily tasks which need to be done. Unlimited number of new tasks can be added and removed as per user convenience. Once you complete a task, you can check it off as completed.

<hr>

## HTML Layout

The _index.html_ file gives the basic framework for the webpage. In the head tag, we have linked the _style.css_ file which provides the styling for the page and also imported fonts from fontawesome.

In the body tag, firstly we have used _wrapper_ to center the contents of the webpage. Now, we have created a class named _inputField_ with text type input to "Add Your New Todo List". Beside that, we have added a **"plus"** button to add in more fields.
Now, we add an _unordered list_ tag with class as _todoList_.

In the footer class, we have added a span element showing the number of pending tasks. At the end, we have added a button **"Delete All"** to clear all the tasks from the list.

Lastly, we have linked the _script.js_ file in the body tag.

<hr>

## CSS Styling

In the _style.css_ file, we have styled the various elements of the webpage according to our will. We have specified the height, width, padding and background colour for the body of the page and also given different colours for user selections.

In the _wrapper_ class, we have styled the _header_ and _inputField_ differently. The input field box has been given a _flex_ display.

Further, we have styled the input in the input field. We have also added the hover feature to the _inputField button_.

Now, we have styled the various list items present in the unordered list.

<hr>

## JavaScript Interaction

Now, we'll talk about the JavaScript interaction which makes our page functional.

First, we have created variables for _inputBox_, _addBtn_, _todoList_, _deleteAllBtn_, and _input_. Then, we have added functioning for taking user input in the input field.

Further, we have created a _showTasks_ function to show all the entered tasks in the list. Then, we have created a variable _pendingTasksNumb_ to store the number of pending tasks for the user.

Following this, we have added an event listener for the input field to enable adding of new tasks through the ENTER key in the text field.

We have also added a functioning delete button beside each stored task to remove it from the list.

At the end, we have added a _deleteAllBtn_ to delete all the stored tasks from the list.

<hr>

## Conclusion

We have successfully created a fully-functional Todo webpage using HTML, CSS and JavaScript.

<hr>

## Author

[Nishant Giri](https://github.com/nishant-giri "View Profile")