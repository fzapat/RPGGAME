default: RPG.java Area.java Map.java Inventory.java
	javac RPG.java Area.java Map.java Inventory.java

run: RPG.class Map.class Area.class Inventory.class
	java RPG

clean:
	rm -f *.class
