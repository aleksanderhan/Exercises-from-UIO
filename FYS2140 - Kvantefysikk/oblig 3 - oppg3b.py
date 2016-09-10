from scitools.all import *

A = 1
m = 1
c = 1
h = 1
k1 = 0.6
k2 = 0.7

def omega(k):
    return c*sqrt(k*(m*c/h)**2)

#x = linspace(0,100,1000)
x=3

t = linspace(0,170,1000)

y1 = sin(k1*x - omega(k1)*t)
y2 = sin(k2*x - omega(k2)*t)
y = y1 + y2

figure(1)
plot(t,y1)
hold('on')
plot(t,y2)
plot(t,y)
xlabel('t')
ylabel('y')
hardcopy('oppg3b.png')
