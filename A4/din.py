import threading,time,random
from pymongo import MongoClient

class Philosopher(threading.Thread):

	running = True
	connection = MongoClient("127.0.0.1",27017)
	count = 0
	@staticmethod
	def readdb(self,index):
		print "reading from db"
		db = Philosopher.connection.test.diniraw
		limit = self.count
		cursor = db.find({"ph_no": index})[limit:limit+1]
		print cursor[0]
		self.count+=1
		self.count%=9

	def __init__(self,i,xname,left,right):
		threading.Thread.__init__(self)
		self.index=i
		self.name=xname
		self.forkOnLeft = left
		self.forkOnRight = right

	def run(self):
		while(self.running):
			time.sleep(random.uniform(3,13))
			print "%s is hungry" % self.name
			self.dine()

	def dine(self):
		fork1,fork2 = self.forkOnLeft,self.forkOnRight

		while(self.running):
			fork1.acquire(True)
			locked = fork2.acquire(False)
			if locked: break
			fork1.release()
			fork1,fork2 = fork2,fork1
		else:
			return

		self.dining()
		fork1.release()
		fork2.release()

	def dining(self):
		print "%s starts eating" % self.name
		Philosopher.readdb(self,self.index)
		time.sleep(random.uniform(1,10))
		print "%s finishes eating" % self.name

def diningp():
	forks = [threading.Lock() for i in range(5)]
	names = ['A','B','C','D','E']

	philosophers = [Philosopher(i,names[i],forks[i%5],forks[(i+1)%5]) for i in range(5)]

	Philosopher.running = True
	for p in philosophers: p.start()
	time.sleep(50)
	Philosopher.running = False
	print "Finishing up..."

diningp()