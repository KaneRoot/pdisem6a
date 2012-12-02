CARNAGE_ADDRESS = "localhost"
PORT = "9001"
# it means N * N * block_size (32 * 32 kitties presently)
CARNAGE_FIELD_SIZE = "60"

all : compilation

lancer_serveur : destroy compilation
	rmiregistry $(PORT) &
	@sleep 1
	java Serveur $(CARNAGE_FIELD_SIZE) $(CARNAGE_ADDRESS) $(PORT)

lancer_client : compilation
	java Client $(CARNAGE_ADDRESS) $(PORT)

watch_kitties : compilation
	java WatchTheKittiesDie $(CARNAGE_FIELD_SIZE) $(CARNAGE_ADDRESS) $(PORT)

compilation :
	javac *.java

destroy :
	@-pkill rmiregistry
	@sleep 1

clean :
	rm *.class
