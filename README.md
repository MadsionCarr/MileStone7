# MileStone7
CST‑239 – Weekly Milestone Update  
Madison’s Game Store Application

This week’s work focused on completing the JUnit testing requirements for the project and finalizing the core functionality of the StoreFront application. Below is a summary of what was implemented, tested, and verified.

1. JUnit Testing Overview
I created a full JUnit 4 test suite to validate the behavior of the major classes in the project.
All tests were organized under the app.test package.

Test Classes Included
SalableProductTest

WeaponTest

ArmorTest

HealthTest

InventoryManagerTest

AllTestsSuite (JUnit Test Suite)

Test Suite
AllTestsSuite.java runs all test classes together.
This suite was required for the milestone and ensures everything executes in one run.

Test Results
24 tests run

0 errors

0 failures

All tests passed successfully.

2. What Was Tested
SalableProduct & Subclasses
Getter/setter behavior

Price, quantity, and description updates

Comparable sorting logic (name → price)

toString formatting

InventoryManager
Adding and removing products

Finding products by name

Purchasing and canceling purchases

Sorting ascending and descending

JSON load/save behavior (mocked through FileService)

ShoppingCart
Adding items

Removing items

Clearing the cart

Calculating totals

3. Project Functionality Completed This Week
StoreFront Application
Fully working menu system

Product browsing with sorting

Purchasing and canceling purchases

Cart viewing and checkout

Inventory loads from JSON on startup

Inventory saves to JSON on exit

JSON Persistence
Implemented polymorphic JSON using Jackson annotations

Supports Weapon, Armor, and Health subclasses

FileService handles reading/writing inventory.json

Admin Server (Bonus Feature)
Added admin networking support

AdminServerThread, AdminNetworkServer, and AdminCommandHandler included

4. How to Run the Program
Run StoreFront.java

Inventory loads automatically from inventory.json

Use the menu to browse, purchase, cancel, and checkout

On exit, the updated inventory is saved

To run the JUnit tests:

Right‑click AllTestsSuite.java → Run As → JUnit Test

5. Notes
All major features are complete and tested

No known bugs at this time

Code is ready for screencast recording and final submission
