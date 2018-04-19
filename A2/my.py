import threading
import xml.etree.ElementTree as x
import unittest

def qhandler(arr,left,right):
	main_t=threading.Thread(target=qsort,args=(arr,left,right))
	main_t.start()
	main_t.join()
	return arr

def qsort(arr,left,right):
	if(left<right):
		print threading.current_thread()
		p=partition(arr,left,right)
		t1=threading.Thread(target=qsort,args=(arr,left,p-1))
		t2=threading.Thread(target=qsort,args=(arr,p+1,right))
		t1.start()
		t2.start()
		t1.join()
		t2.join()

def partition(arr,left,right):
	pivot=arr[right]
	i=left-1
	for j in range(left,right):
		if(arr[j]<=pivot):
			i=i+1
			arr[i],arr[j]=arr[j],arr[i]

	arr[i+1],arr[right]=arr[right],arr[i+1]
	return i+1


class Test(unittest.TestCase):
	def test_positive(self):
		a=[1,4,3,2]
		b=[1,2,3,4]
		self.assertEqual(qhandler(a,0,3),b)
	def test_negative(self):
		a=[1,2,3,4]
		self.assertEqual(qhandler(a,0,3),a)




r=x.parse("input.xml").getroot()
a=map(int,r.text.split())
print "Unsorted array is:"
print a

a=qhandler(a,0,len(a)-1)
print "Sorted array: "
print a

print "Unit testing"
unittest.main()
