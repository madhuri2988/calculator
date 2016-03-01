steps to build using command line

mvn package

java -cp target/calculator-0.0.1-SNAPSHOT.jar com.synopsys.calc.App "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))" --debug --info --error

used log4j logging and logging levels ca be specified using command lines

Travis CI build history https://travis-ci.org/madhuri2988/calculator/builds/112737625