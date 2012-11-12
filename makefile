all : compile 

compile :
	javac *.java

serveur :

client :
#java Lance $(MACHINE1) $(CLASS_SERVER_PORT) Client $(MACHINE2) $(RMIREGISTRY_PORT)

clean :
	@-rm *.class */*.class
