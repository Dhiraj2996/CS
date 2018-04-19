from Crypto.Cipher import AES
import hashlib
import base64

def prandom(seed,itr):
	for i in range(itr):
		seed = (1103515245 * seed + 12345) & 0xffffffff
	return seed

msg = raw_input("Enter msg: ")
seed = int(raw_input("Enter Seed: "))
i = int(raw_input("Enter iterations: "))

while len(msg)%16 != 0:
	msg += "{"

k = prandom(seed,i)

print "Random number is: ",k

m = hashlib.md5()
m.update(str(k))
dig = m.digest()

obj = AES.new(dig)

ctext = base64.b64encode(obj.encrypt(msg))

ptext = obj.decrypt(base64.b64decode(ctext)).rstrip("{")

print "Cipher text: ",ctext
print "Plain text : ",ptext