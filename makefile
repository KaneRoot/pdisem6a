REP = kitties

all : compile 

compile :
	javac $(REP)/*.java

serveur :

client :

clean :
	@-rm */*.class
