# par défaut
MACHINE1 = localhost
MACHINE2 = localhost
CLASS_SERVER_PORT = 10000
RMIREGISTRY_PORT = 10001
NAME_TO_ADD = coin
NUM_TO_ADD = "06 66 66 66 66"
#@echo -ne "1. make compile\n2. make class_serveur\n3. (autre terminal) make serveur\n4. (autre terminal) make (client|ajoute|supprime)"

all : compile 

compile :
	javac *.java

serveur :

client :
#java Lance $(MACHINE1) $(CLASS_SERVER_PORT) Client $(MACHINE2) $(RMIREGISTRY_PORT)

clean :
	-rm *.class */*.class
