# ./README.txt or executive following commands:
#----------------------------------------------


# delete the target dir
rm -rf target

# create new target dir
mkdir target

# compile class files into the target dir
javac -d target src/java/edu/school21/printer/*/*.java

#copy resource to src target dir
cp -R src/resources target/.

# create Jar file:
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# launch jar application:
chmod u+x target/images-to-chars-printer.jar
java -jar target/images-to-chars-printer.jar . o
