import glob, os
from scitools.all import *
from pylab import *

A = 5.0
B = A
w1 = 12.0
w2 = 14.0
k1 = 6.0
k2 = 8.0
N = 200
dt = 0.01

t = zeros(N)
t[0] = 0.0
x = linspace(0,2*pi,N)

for i in range(N-1):
    y1 = A*cos(w1*t[i] - k1*x)
    y2 = B*cos(w2*t[i] - k2*x)
    Y = y1 + y2
    #omhyllingskurve
    y_om = 2*A*cos(((k1 - k2)/2.0)*x - ((w1 - w2)/2)*t[i])
    t[i+1] = t[i] + dt
    figure()
    plot(x,Y)
    hold('on')
    plot(x,y_om)
    hold('off')    
    axis([0,2*pi,-abs(A+B),abs(A+B)])
    savefig('tmp%04d.png' % i)
    print i

movie('tmp*.png')

for filename in glob.glob('tmp*.png'):  
    os.remove(filename)


