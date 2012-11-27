CARNAGE_ADDRESS = "localhost"
PORT = "9001"

all : compilation

lancer_serveur : destroy compilation
	rmiregistry $(PORT) &
	@sleep 1
	java Serveur $(PORT)

lancer_client : compilation
	java Client $(MACHINE) $(PORT)

compilation :
	javac *.java

destroy :
	@-pkill rmiregistry
	@sleep 1

clean :
	rm *.class
