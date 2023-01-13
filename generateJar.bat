del build
cd src
javac -d ../build *.java
cd ../build
jar --create --verbose --file ../archive.jar  --main-class App -c .

