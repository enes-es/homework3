JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

CLASSES = \
		Main.java \
		Inventory.java \
		Device.java \
		DeviceObj.java \
		SmartPhone.java \
		Laptop.java \
		Smartwatch.java \
		TV.java \
		Headphones.java \


all : Main.class

run : 
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) *.class 

cleanreport:
	$(RM) report(?:\(\d+\))?\.txt
cleandoc:
	$(RM) -r ./javadoc