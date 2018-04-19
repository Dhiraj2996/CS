def d2b(x):
	ans = [0] * 8
	i = 7
	while x and i >= 0:
		ans[i] = x % 2
		x /= 2
		i -= 1
	return ans

def rightshift(x):
	i = 16
	while i >= 1:
		x[i] = x[i-1]
		i -= 1
	return x

def add(x, y):
	z = [0] * 17
	c = 0
	i = 16
	while i >= 0:
		temp = x[i] + y[i] + c
		z[i] = temp % 2
		c = temp / 2
		i -= 1
	return z

def sub(x, y):
	z = [0] * 16
	c = 0
	i = 15
	while i >= 1:
		temp = x[i] - y[i] - c
		z[i] = temp % 2
		c = abs(temp / 2)
		i -= 1
	return z


def b2d(x):
	if x[0] == 1:
		one = [0] * 15 + [1]
		x = sub(x, one)
		for i in range(len(x)):
			x[i] = 1 - x[i] #Invert

	i = 15
	p = 0
	ans = 0
	while i >= 1:
		if x[i] == 1:
			ans += 2**p
		p += 1
		i -= 1
	if x[0]:
		return -ans
	return ans

def prod(x, y):
	M = d2b(x)
	Q = d2b(y)
	minus_M = d2b(-x)
	print "M",M
	print "-m",minus_M
	p = [0] * 8
	print "p  ",p
	p.extend(Q)
	print "p.extend(Q)",p
	p.append(0)
	print p

	M += [0] * 9
	minus_M += [0] * 9

	for i in range(8):
		if(p[-2] == 1 and p[-1] == 0):
			p = add(p, minus_M)
		elif(p[-2] == 0 and p[-1] == 1):
			p = add(p, M)
		p = rightshift(p)

	p = p[:-1]
	return b2d(p)