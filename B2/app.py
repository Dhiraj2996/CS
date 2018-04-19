from flask import *

app = Flask(__name__)

@app.route('/')
def home():
	return render_template('index.html', msg=None)

@app.route('/check', methods=['POST','GET'])
def chk():
	a = checker(request.form['var'])
	return render_template('index.html', msg = a)

def checker(strr):
	fdata = ""
	with open("data.txt",'rt') as f:
		for l in f:
			fdata +=l

	a=fdata.replace(',',' ')
	b=fdata.split()
	print b

	c=strr.replace(',',' ')
	d=strr.split()
	print d

	z=list(set(b) & set(d))
	print z
	print len(z)
	print len(set(d))

	p = str(float(len(z))/(len(set(d)))*100)
	return p


	

if __name__ == '__main__':
	app.run(debug=True)