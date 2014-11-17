status = subprocess.call(["./boolz.sh"])
print "Status is : "+status
if(status == 0):
	print "Error detected"

