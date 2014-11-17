import subprocess
import os


def testForPass(filepath):
    L = [line.strip() for line in open(filepath+"/config")]

    for item in L:
        os.system("./boolz.sh")
        proc = subprocess.Popen(["java", "Parser",filepath+"/"+item], stdout=subprocess.PIPE)
        (out, err) = proc.communicate()
        if(out.find("Error") == -1):
	    try:
                print "Compiling the java file"
                subprocess.check_output("javac Boolzebra.java", shell=True)
            except subprocess.CalledProcessError, e:
                print "Java Compile time error"
		f = open("mismatchResults","a+")
                f.write("Mismatch found in test case: "+item+" at the location "+filepath+"\n")
                f.close()
                print ("MISMATCH!!!!!!!!!! in "+filepath+" in the test case "+item)
                continue

            try:
                subprocess.check_output("java Boolzebra" , shell=True)
            except subprocess.CalledProcessError , e:
                print "Java runtime error"
		f = open("mismatchResults","a+")
                f.write("Mismatch found in test case: "+item+" at the location "+filepath+"\n")
                f.close()
                print ("MISMATCH!!!!!!!!!! in "+filepath+" in the test case "+item)
                continue
		
	    print "The test passed successfully!!!"	

        else:
            f = open("mismatchResults","a+")
            f.write("Mismatch found in test case: "+item+" at the location "+filepath+"\n")
            f.close()
            print ("MISMATCH!!!!!!!!!! in "+filepath+" in the test case "+item)


passPath = []


passPath = [line.strip() for line in open("phase2TestPaths")]


for item in passPath:
    testForPass(item)



