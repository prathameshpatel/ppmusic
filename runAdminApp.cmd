rem for Windows, execute AdminApp
rem be sure to start and load hsqldb, also use ant config-hsqldb  before using this
java -javaagent:lib/eclipselink.jar -cp lib/eclipselink.jar;lib/javax.persistence_2.1.1.v201509150925.jar;lib/hsqldb.jar;bin cs636.music.presentation.AdminApp
