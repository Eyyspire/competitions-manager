JFLAGS := -g
JC := javac
JVM := java 
JVM_FLAGS := -ea
JDOC := javadoc
JARFILE := jar
FILE=

SRC := src
TEST := test
OUT := classes
DOCS := docs


JAR	 := jar/competition.jar
MASTERJAR := jar/master.jar
OBSERVERJAR := jar/observer.jar

JUNIT := jar/junit-1-9-1.jar
JUNIT_FLAGS = --details=summary --disable-changes 
        
SOURCES := $(shell find $(SRC) -name '*.java')
TESTS := $(shell find $(TEST) -name '*.java')
SRC_CLASSES := $(SOURCES:%.java=$(OUT)/%.class)
TEST_CLASSES := $(TESTS:%.java=$(OUT)/%.class)

PKG := competitions
MAIN := $(PKG).Main
MASTERMAIN := $(PKG).MasterMain
OBSERVERMAIN := $(PKG).ObserverMain

.SILENT:
all :
	make cls
	make docs
	make tests

cls :
	$(JC) -sourcepath $(SRC) -d $(OUT) $(SOURCES)
	$(JC) -sourcepath $(TEST) -classpath $(JUNIT):$(OUT) -d $(OUT) $(TESTS)
	
tests : cls
	$(JVM) -jar $(JUNIT) -classpath $(OUT) --select-package=$(PKG)

docs:
	$(JDOC) -sourcepath src -d $(DOCS) $(SOURCES)

competition: cls
	$(JVM) $(JVM_FLAGS) -classpath $(OUT) $(MAIN)

master : cls
	$(JVM) $(JVM_FLAGS) -classpath $(OUT) $(MASTERMAIN)
	
observer : cls
	$(JVM) $(JVM_FLAGS) -classpath $(OUT) $(OBSERVERMAIN)

jarfile: cls
	$(JARFILE) cvfe $(JAR) $(MAIN) -C $(OUT) .
	$(JARFILE) cvfe $(MASTERJAR) $(MASTERMAIN) -C $(OUT) .
	$(JARFILE) cvfe $(OBSERVERJAR) $(OBSERVERMAIN) -C $(OUT) .

runjar: runcompetition runmaster runobserver

runcompetition : jarfile
	$(JVM) -jar $(JAR)

runmaster : jarfile
	$(JVM) -jar $(MASTERJAR)

runobserver : jarfile
	$(JVM) -jar $(OBSERVERJAR)

clean:
	rm -rf $(OUT)
	rm -rf $(DOCS)
	rm -rf $(JAR)
	rm -rf $(MASTERJAR)
	rm -rf $(OBSERVERJAR)
