JFLAGS = -g
JC = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	MySqlDatabase.java \
	Purchase.java\
	Item.java\
	Shipment.java\
	Project.java\

default: classes

classes: $(CLASSES:.java=.class)


run: classes .run
.run:
	
	java Project CreateItem item "Item description" 10.00

setup: .setup
.setup:
	@mkdir -p ~/opt
	@wget https://downloads.mysql.com/archives/get/file/mysql-connector-java-5.1.47.tar.gz -O ~/opt/mysql-connector-java-5.1.47.tar.gz
	@cd ~/opt && tar -xf mysql-connector-java-5.1.47.tar.gz
	@echo "\n\n\n"
	@echo "-------------------------------------------------------------"
	@echo "Add the following to your bashrc:"
	@echo 'export CLASSPATH=~/opt/mysql-connector-java-5.1.47/mysql-connector-java-5.1.47-bin.jar:$$CLASSPATH'

clean:
	$(RM) *.class
