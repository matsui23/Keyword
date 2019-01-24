Overview
Keyword is an app that promotes the use of unique secure passwords across a users various accounts. Users would use this platform to link and save passwords to various accounts across any platforms for storage. Instead of needing to recall all of their passwords across various platforms they can use this service to store secure passwords in an organized and user friendly interface.

This also assists users with creating new and unique passwords for each account they register instead of reusing existing ones or repeating patterns.

The primary feature I want to build in this project is a process that takes in user information, that they decide to share. This info could be personal or random. This info could be strings of digits, characters, special characters, combinations of either to produce unique passwords based on the the users “modifiers” randomly.

For example, as a user, I go in and add the following modifiers to my profile. “Dog”, “Cat”, “44”, “1980”, “Southwest”, “Add”, “!”, “@”, “#”, “&”, “Option”, “a”, “b”, “c”, “1”, “2”, “3”. With all of these options the system would then ask the user how they want to formulate the password breaking it into chunks. So I want a password that combines starts with a digit mod, combines two string mods, takes a special mod, and ends with a digit mod. The system would then scrub their mod table and randomly select an element to plug in. So an example of the above combo could be “44CatSouthwest#3”.

Features
User login: Users will be able to register and login to store passwords and mods Modification creation: Users will be able to create a series of lists containing words, special characters, and digits and save them to a private table for use in another feature. Custom random password creation: This feature will use data pulled from the mod feature above to generate random passwords that are tailored to the specific users mod tables contents. Account linking: Users will be able to save new passwords and existing ones setting them as passwords for whatever accounts they want to inlcude.

Technologies
MYSQL Database Java Spring web framework Thymeleaf template engine Built in Intellij IDE
