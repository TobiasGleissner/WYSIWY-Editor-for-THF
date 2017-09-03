JAVAC ?= javac
JFLAGS ?=
JAR ?= jar

SRCDIR = $(patsubst %Makefile,./%,$(firstword $(MAKEFILE_LIST)))

SRC = $(wildcard $(SRCDIR)/src/*/*.java)
OBJ = $(patsubst $(SRCDIR)/src/%.java,obj/%.class,$(SRC))
OBJ_REL = $(patsubst obj/%,%,$(OBJ))

BIN = out/thfedit.jar

all: $(BIN)

out obj:
	mkdir -p $@

obj/%.class: $(SRCDIR)/src/%.java | obj
	$(JAVAC) $(JFLAGS) -d obj -cp src $<

$(BIN): src/manifest.txt | out $(OBJ)
	$(JAR) cmf src/manifest.txt $(BIN) -C obj .

clean:
	rm -rf obj out

.PHONY: clean $(BIN)
