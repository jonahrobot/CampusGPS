run:compile
	java Frontend
	
compile:
	javac Backend.java BackendInterface.java CS400Graph.java EdgeData.java Frontend.java GraphADT.java MapDataReader.java MapDataReaderInterface.java NodeData.java

test: BackendDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath

BackendDeveloperTests.class: BackendDeveloperTests.java
	javac -cp .:junit5.jar BackendDeveloperTests.java

clean:
	$(RM) *.class
