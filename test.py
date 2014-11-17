import subprocess
import os


"""
status  = os.system("java Parser ../../Desktop/TestRepository/Declarations/test2")

print status

try:
    subprocess.check_output("java Parser ../../Desktop/TestRepository/Declarations/test2", shell=True)
except subprocess.CalledProcessError, e:
    print "Java Compile time error"

"""
try:
    subprocess.check_output("javac Boolzebra.java", shell=True)
except subprocess.CalledProcessError, e:
    print "Java Compile time error"
"""
try:
    subprocess.check_output("java Boolzebra". sheel = True)
except subprocess.CalledProcessError , e:
    print "Java runtime error"



#status = os.system("./boolz.sh")
status = subprocess.check_output(["javac" ,"Boolzebra.java"])
print "Status is : "+str(status)
if(status == 0):
	print "Error detected"
"""
