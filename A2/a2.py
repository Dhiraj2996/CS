import threading
import xml.etree.ElementTree as X
import unittest

def partition(arr,left,right):
	pivot=arr[left]
	i=left+1
	j=right
	while 1:
		while i<=j and arr[i]<pivot:
			i+=1
		while i<=j and arr[j]>pivot:
			j-=1
		if j<=i:
			break
		arr[i],arr[j]=arr[j],arr[i]
	arr[left]=arr[j]
	arr[j]=pivot
	return j

def quick_sort(arr,left,right):
	if left<right:
		print threading.current_thread()
		p=partition(arr,left,right)
		t1=threading.Thread(target=quick_sort, args=(arr,left,p-1))
		t2=threading.Thread(target=quick_sort, args=(arr,p+1,right))
		t1.start()
		t2.start()
		t1.join()
		t2.join()

def qHandler(arr,left,right):
	main_t=threading.Thread(target=quick_sort,args=(arr,left,right))
	main_t.start()
	main_t.join()
	return arr

r=X.parse("input.xml").getroot()
print r.text

a=map(int,r.text.split())
a=qHandler(a,0,len(a)-1)
print "Sorted Array::"
print a

class MyTestCases(unittest.TestCase):
	def test_quick(self):
		a=[3,8,6,2,9,7,1,5,4]
		a_out=[1,2,3,4,5,6,7,8,9]
		return self.assertEqual(qHandler(a,0,len(a)-1),a_out)


print "Running tests..."
unittest.main()
