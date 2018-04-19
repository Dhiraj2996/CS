
def cipher(ptext,key = None):
	ctext = ""
	if key is not None:
		
		for symbol in ptext:
			if symbol.isalpha():
				num = ord(symbol)
				num += key
				if symbol.isupper():
					if(num > ord('Z')):
						num -= 26
					elif(num < ord('A')):
						num += 26
				elif symbol.islower():
					if(num > ord('z')):
						num -= 26
					elif(num < ord('a')):
						num += 26
				ctext += chr(num)

			else:
				ctext += symbol

	else:
		arr = "abcdefghijklmnopqrstuvwxyz"
		key = "qwertyuiopsdfghjklmnbvcxz"

		for symbol in ptext:
			if symbol.isalpha():
				if symbol.islower():
					index = arr.find(symbol)
					ctext += key[index]
				else:
					index = arr.find(symbol.lower)
					ctext += key[index].upper
			else:
				ctext += symbol
	return ctext



if __name__ == '__main__':
	ptext = raw_input("Enter msg: ")
	ctext = ""
	case = int(raw_input("\n1.Caesar\n2.Monoalphabetic\nEnter your choice: "))

	if case==1:
		key = int(raw_input("Enter key 1-26: "))
		ctext = cipher(ptext,key)
		print "Cipher text is: ",ctext
	elif case==2:
		ctext = cipher(ptext)
		print "Cipher text is: ",ctext
	else:
		print "Invalid choice"