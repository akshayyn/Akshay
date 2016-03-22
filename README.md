# Akshay Nakhawa
Turo Coding Assesment(Simple Database)

I have solved this problem using Memento Design pattern.  
Main database class contains two HashMaps
1. Map<String,Integer>For Holding name value pairs for main database functionality
2.Map<Integer,Set<String>> For holding all the unique Keys for a particular values(Set will avoid duplication) to give NUMEQUALTO functionality

Following are data operations and its aproach-
SET-
if key not present already - new entry to Database Map
if key not present- the value corresponding to key is updated/the key is removed from key set corresponding to value in value Counter Map
if value already present/absent in  valueCounter Map -key is added to the corresponding set(Set takes care of avoiding duplication)

UNSET-
key-pair value removed from databaseMap
the value corresponding to key is updated/the key is removed from key set corresponding to value in value Counter Map

NUMEQUALTO
Size of the key set corresponding to the value is returned.

GET
-If value corresponding to key is present in Database Map is returned /else: error

For transactions such as BEGIN,COMMIT, ROLLBACK and END i have implmented Memento patter with a Stack which will hold all the save points(i.e Memento objects) 
-BEGIN- new save point will be created and pushed to Stack
-COMMIT Stack will be cleared(
-ROLLBACK-Latest Save point Memento object popped from the stack and Database restored to original state before recent transaction
-END- IF the stack is non-empty(i.e some transactions are not commited yet , DB will be restored to original state by setting maps to first save point values)

To run the solution in interactive mode:
cd into SimpleDB folder 
cd into bin folder under SimpleDB folder
java DatabaseUI

To run the only test case come with the original problem:
cd into SimpleDB folder 
cd into bin folder under SimpleDB folder
java DatabaseUI < test.txt
