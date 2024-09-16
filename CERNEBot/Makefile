# Diretórios
SRC_DIR = src
OUT_DIR = out

# Busca todos os arquivos .java em src/
SOURCES := $(shell find $(SRC_DIR) -name '*.java')

# Compilar: transforma os .java em .class, mas no diretório out/
CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

# Nome da classe principal
MAIN_CLASS = Main

# Comando Java
JAVAC = javac
JAVA = java

# Parâmetro para o compilador e executor
JFLAGS = -d $(OUT_DIR) -sourcepath $(SRC_DIR)
RFLAGS = -cp $(OUT_DIR)

# Regra padrão: compilar e rodar
all: $(CLASSES)
	$(JAVA) $(RFLAGS) $(MAIN_CLASS)

# Regra para compilar os arquivos .java em .class na pasta out/
$(OUT_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(dir $@) # Cria diretórios necessários em out/
	$(JAVAC) $(JFLAGS) $<

# Regra para limpar os arquivos .class (limpar o projeto)
clean:
	rm -rf $(OUT_DIR)

# Regra para executar o programa sem recompilar
run: all
	$(JAVA) $(RFLAGS) $(MAIN_CLASS)
