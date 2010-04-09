
CLASSPATH=lib/specs_2.8.0.Beta1-1.6.4-SNAPSHOT.jar:lib/junit-4.5.jar:lib/embedded-interpreter-0.1-2.8.0.Beta1-SNAPSHOT.jar

all: clean compile test jar

clean:
	rm -rf build

compile: build_dir
	scalac -d build -classpath ${CLASSPATH} -unchecked -deprecation $$(find src spec -name '*.scala')

test:
	scala -classpath build:${CLASSPATH} bin/specrunner.scala

jar: lib_dir remove_jar
	(cd build; \
	jar cf ../lib/sake.jar -C . `find . -type f`)
	jar cf lib/sake-src.jar src spec
    
build_dir:
	mkdir -p build

lib_dir:
	mkdir -p lib

remove_jar:
	rm -f lib/sake.jar
	rm -f lib/sake-src.jar
