#CARNAGE_ADDRESS = "192.168.0.3"
CARNAGE_ADDRESS = "localhost"
PORT = "9001"
# it means N * N * block_size (32 * 32 kitties presently)
CARNAGE_FIELD_SIZE_X = "12"
CARNAGE_FIELD_SIZE_Y = "5"

all : compilation 
	@-echo -e "Now :\nmake lancer_serveur\nmake lancer_client\nmake lancer_x_clients\nmake watch_kitties"

lancer_serveur : destroy
	rmiregistry $(PORT) &
	@sleep 1
	java Serveur $(CARNAGE_FIELD_SIZE_X) $(CARNAGE_FIELD_SIZE_Y) $(CARNAGE_ADDRESS) $(PORT)

lancer_client : 
	java Client $(CARNAGE_ADDRESS) $(PORT)

lancer_x_clients : 
	./runXClient.sh $(CARNAGE_ADDRESS) $(PORT)

watch_kitties :
	java WatchTheKittiesDie $(CARNAGE_FIELD_SIZE_X) $(CARNAGE_FIELD_SIZE_Y) $(CARNAGE_ADDRESS) $(PORT)

compilation :
	javac *.java

destroy :
	@-pkill rmiregistry
	@sleep 1

clean :
	@-rm *.class *.log
