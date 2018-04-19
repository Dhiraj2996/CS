import os
import unittest

def read(filename):
	a=open(filename,'r')
	array=[]
	for i in a:
		array.append(int(i))
	return array

def sort(inp):
	inp.sort()
	return inp

def binarysearch(key,arr,left,right):
	if(left<=right):
		mid=(left+right)/2
		if(arr[mid]==key):
			return mid
		elif(key<arr[mid]):
			return binarysearch(key,arr,left,mid-1)
		elif(key>arr[mid]):
			return binarysearch(key,arr,mid+1,right)

class Test(unittest.TestCase):
	def test_positive(self):
		self.assertEqual(binarysearch(1,[0,1,2,3],0,3),1)
	def test_negative(self):
		self.assertEqual(binarysearch(6,[0,1,2,3],0,3),None)

some=[]
some=read("input.txt")
print "Array befor sorting is: ",some
print ""
some=sort(some)
print "Sorted Array is:",some
print ""
print len(some)
x=input("Enter Key to be Searched \t") 
pos=0
pos=binarysearch(x,some,0,len(some)-1)
print "Key found at position",pos

print "\n Unit testing"
unittest.main()

