import subprocess
import os


def testForFail(filepath):
    L = [line.strip() for line in open(filepath+"/config")]

    for item in L:
        os.system("./boolz.sh")
	print "Checking test case "+filepath+"/"+item
        proc = subprocess.Popen(["java", "Parser",filepath+"/"+item], stdout=subprocess.PIPE)
        (out, err) = proc.communicate()
	print "Find value is "+(str(out.find("Error")))
        if(out.find("Error") == -1):
	    try:
	        print "Compiling the java file"
    	        subprocess.check_output("javac Boolzebra.java", shell=True)
	    except subprocess.CalledProcessError, e:
    	        print "Java Compile time error"
	        continue

	    try:
    	        subprocess.check_output("java Boolzebra" , shell=True)
            except subprocess.CalledProcessError , e:
    	        print "Java runtime error"
	        continue

	    print "No error found!!!! What the hell!! It is a fail test case!!!"
	    f = open("mismatchResults","a+")
            f.write("Mismatch found in test case: "+item+" at the location "+filepath+"\n")
            f.close()
            print ("MISMATCH!!!!!!!!!! in "+filepath+" in the test case "+item)

	else:
	    continue


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

failPath = []
passPath = []

failPath = [line.strip() for line in open("failTestPaths")]
passPath = [line.strip() for line in open("passTestPaths")]


for item in failPath:
    testForFail(item)


for item in passPath:
    testForPass(item)



