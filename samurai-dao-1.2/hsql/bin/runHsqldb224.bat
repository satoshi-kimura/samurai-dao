pushd ..\..\src\test\resources\data-hsqldb
rem java -classpath ../../../../lib/hsqldb-2.2.4.jar org.hsqldb.server.Server --database.0 file:. --dbname.0 demo
rem java -classpath ../../../../lib/hsqldb-2.2.4.jar org.hsqldb.server.Server --database.0 file:hsqldb/hemrajdb --dbname.0 testdb
java -classpath ../../../../lib/hsqldb-2.2.4.jar org.hsqldb.server.Server %1 %2 %3 %4 %5 %6 %7 %8 %9
popd
