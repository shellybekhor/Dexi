import os, sys

def say(name, w):
	os.system('say -v "Carmit" {w} -o {name}.aiff'.format(w=w, name=name))

if __name__ == "__main__":
	with open(sys.argv[1], 'r') as file:
		words = (line.split() for line in file)
		# say = lambda w,x: print(w,x)
		x = map(say, *zip(*words))
		list(x)
