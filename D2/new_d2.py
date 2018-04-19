import hashlib
from Crypto.Cipher import AES
import base64

def PRNG(seed,itr):
	for i in range(0,itr):
		seed = (1103515245*seed + 12345) & 0x7fffffff
		i+=1
	return seed

password=raw_input("Enter Password\n")
seed=raw_input("Enter the seed value\n")
seed=int(seed)
itr=raw_input("Enter Number of Iterations.\n")
itr=int(itr)

k=PRNG(seed,itr)

while len(password)%16!=0:
	password+="{"
	
print "Psuedo random number: "+str(k)+" generated with seed: " +str(seed)
m=hashlib.md5()
print m
m.update(str(k))
print m
dig=m.digest()
print m.hexdigest()
obj=AES.new(dig)
encr=base64.b64encode(obj.encrypt(password))

print encr

ret=obj.decrypt(base64.b64decode(encr)).rstrip("{")

print ret
