README

04/03/2025
STEPS/MODIFICATIONS:

1.	I renamed level to grid.
2.	Used throws for ArrayIndexOutOfBoundsException & NullPointerException

Feedback:
Monde 2 niveau 2 : non validé
- Où est le fichier de log ? la notice ? ()
- C'est une grille de caractères qui est demandé et non de chaîne de caractères (DONE)
- Il faut afficher la position du joueur dans la grille en plus de son score (DONE)
- Documentation getNextPlayer absente (i don't know what to do here)
- Documentation equals à revoir (Done probably)
- Documentation Niveau incomplète au niveau des tags (I think it's done)

README

This code is constructed in JAVA, when executed it runs a game, the player spawns at the position(1,1) and the game consists of trying to get the
player to walk through the mission area till he reaches the finish line.

NOTE: There is no getNextPlayer() so i can't add a documentation to it.

Monde 2 niveau 3 :

The player can move in all 4 directions, the program will stop working when he touches the wall or gets beyond it 


MONDE 2 NIVEAU 3 et 4:

The player can move in all 4 directions and can't get out of the grid or get in the wall
The game will ask the user to enter the direction in which he wishes to move the player and it'll stop working once the player types stop

MONDE 2 NIVEAU 5 ET 6:


The game can now read a text file and integrate the level from it instead of ot being created with the given data (rows, columns) at the creation of it.
The game can now run as an executable as a jar file has been created (Tried to run it through a terminal)

THE JAR FILE IS LOCATED IN THE 'dist' FOLDER 