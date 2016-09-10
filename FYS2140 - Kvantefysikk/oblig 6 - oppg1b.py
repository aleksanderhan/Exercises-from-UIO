from scitools.all import *

x = linspace(-4,4,1000)

p0 = ((1/pi)**0.25)*e**(-0.5*x**2)
p1 = ((1/pi)**0.25)*sqrt(2)*x*e**(-0.5*x**2)
p2 = ((1/pi)**0.25)*(2*x**2 - 1)*e**(-0.5*x**2)

plot(x,p0)
legend('psi_0')
hold('on')
grid('on')
plot(x,p1)
legend('psi_1')
plot(x,p2)
legend('psi_2')
hardcopy('oppg1b.png')
