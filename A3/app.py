from b import prod
from flask import *

app = Flask(__name__)

@app.route('/')
def home():
	return render_template('index.html', prod = None)

@app.route('/multiply', methods=['POST','GET'])
def mult():
	return render_template('index.html', prod = prod(int(request.form['num1']), int(request.form['num2'])))		

if __name__ == '__main__':
	app.run(debug=True)