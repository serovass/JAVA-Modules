# add it.bmp file to ImagesToChar dir
# ./README.txt or executive following commands:
#----------------------------------------------


# delete the target dir
rm -rf target

# create new target dir
mkdir target

# compile class files into the target dir
javac -d target src/java/edu/school21/printer/*/*.java

# launch Java program Application:
# java Application <whiteSymbol> <blackSymbol> <image.bmp>"
java -classpath ./target edu.school21.printer.app.Application . o ./it.bmp
