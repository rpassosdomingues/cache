SRC_DIR=src
BIN_DIR=bin

CLASSES=$(BIN_DIR)/Main.class

JFLAGS = -d $(BIN_DIR) -cp $(BIN_DIR)
JC = javac

$(CLASSES): $(SRC_DIR)/Main.java
	$(JC) $(JFLAGS) $(SRC_DIR)/Main.java

run: $(CLASSES)
	java -cp $(BIN_DIR) Main

clean:
	rm -rf $(BIN_DIR)/*.class
