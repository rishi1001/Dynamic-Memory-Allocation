import random
print("Enter testcase name")
name = input()
f = open(name,'w')
print("Enter number of testcases: ")
t = int(input())
f.write(str(t) + "\n")
for i in range(t):
    print("Processing " + str(i+1) + "test case")
    print("Enter size: ")
    size = int(input())
    f.write(str(size) + "\n")
    print("Enter number of commands: ")
    comm = int(input())
    f.write(str(comm) + "\n")
    for j in range(comm):
        r = random.randrange(1,11)
        if r <= 5:
            k = random.randrange(0,size)
            f.write("Allocate " + str(k) + "\n")
        elif r <= 9:
            k = random.randrange(0,size)
            f.write("Free " + str(k) + "\n")
        else :
            f.write("Defragment 0\n")
f.close()