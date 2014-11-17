import subprocess
import os


def testForFail(filepath):
    L = [line.strip() for line in open(filepath+"/config")]

    for item in L:
        os.system("./boolz.sh")
        proc = subprocess.Popen(["java", "Parser",filepath+"/"+item], stdout=subprocess.PIPE)
        (out, err) = proc.communicate()
        if(out.find("Completed") != -1):
            f = open("mismatchResults","a+")
            f.write("Mismatch found in test case: "+item+" at the location "+filepath+"\n")
            f.close()
            print ("MISMATCH!!!!!!!!!! in "+filepath+" in the test case "+item)


def testForPass(filepath):
    L = [line.strip() for line in open(filepath+"/config")]

    for item in L:
        os.system("./boolz.sh")
        proc = subprocess.Popen(["java", "Parser",filepath+"/"+item], stdout=subprocess.PIPE)
        (out, err) = proc.communicate()
        if(out.find("Completed") == -1):
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




