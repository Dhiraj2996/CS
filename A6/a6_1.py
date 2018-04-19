import hashlib
import re
from Crypto.Cipher import AES
import base64

class passw:
	
	def hash(self,var=None):
		password=raw_input("Enter the Password\n")
		while len(password)%16!=0:
			password+="0"
		rsec=re.compile(r'([A-Z]*[a-z]*[0-9]*[!@#$%^&_]*)+')
		if var is None:
			if(len(password)>=8 and rsec.match(password)):
				print "MD5"
				m=hashlib.md5()
				m.update(password)
				dig=m.digest()
				obj=AES.new(dig)
				encr=obj.encrypt(password)
				print base64.b64encode(encr)
			else:
				print "SHA512"
				m=hashlib.sha512()
				m.update(password)
				dig=m.digest()
				obj=AES.new(dig)
				encr=obj.encrypt(password)
				print base64.b64encode(encr)
		else:
			print "SHA256"
			m=hashlib.sha256()
			m.update(password)
			dig=m.digest()
			obj=AES.new(dig)
			encr=obj.encrypt(password)
			print base64.b64encode(encr)

w=passw()
w.hash()
w.hash(256)
