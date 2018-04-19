from pymongo import MongoClient
from datetime import datetime

def readdb(filename):
	connection = MongoClient("127.0.0.1",27017)
	db = connection.test.diniraw

	fp=open(filename,'r').read().strip().split("\n")
	for l in fp:
		record = l.strip().split(",")
		print record
		record = [int(e) for e in record]
		post={"ph_no":record[0],"time":str(datetime.now()),"temp":record[1]}
		db.insert_one(post)

readdb("myrawdata.txt") 