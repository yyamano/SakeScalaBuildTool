
CLASSPATH=/Library/tools/scala/scala-specs/specs-1.4.1.jar:.

all: clean compile test
    
test:
	cd build; \
	for f in $$(find sake -name '*Spec.class'); do ff=$$(echo $${f%.class} | tr / .); echo $$ff; \
		scala -cp ${CLASSPATH} $$ff; done

compile: build_dir
	scalac -d build -cp ${CLASSPATH} -unchecked $$(find src spec -name '*.scala')

clean:
	rm -rf build

build_dir:
	mkdir -p build
