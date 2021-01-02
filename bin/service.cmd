
start "Node" java -Xmx256m ^
	-Dlog4j.configurationFile=log4j2.xml ^
	-Dlog4j.shutdownHookEnabled=false ^
	-jar service.jar
