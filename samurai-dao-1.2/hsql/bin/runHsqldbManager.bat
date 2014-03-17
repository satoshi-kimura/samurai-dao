pushd ..\..\src\test\resources\data-hsqldb
@java -classpath ../../../../lib/hsqldb-2.2.8.jar org.hsqldb.util.DatabaseManager %1 %2 %3 %4 %5 %6 %7 %8 %9
popd
