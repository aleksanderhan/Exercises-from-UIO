from scitools.all import *

'''
PROBLEM 17.2.1
***
Defining variables
'''
Lm = 38.0    # L/m
Em = 8.03    # E/m
rM = 20.0    # r/M
phi0 = 0.0   # initial angle
n = 1000     # number of steps
dtau = 0.01  # proper time step

'''
PROBLEM 17.2.2
***
Defining arrays and initial conditions
'''
r = zeros(n)    # radial-position array
r[0] = rM       # initial radial position
phi = zeros(n)  # angular-position array
phi[0] = phi0   # initial angular position

'''
PROBLEM 17.2.3
***
Looping over all steps, updating r and phi until r<2M
'''
for i in range(n):
    if r[i]<2: # position of no return
        n = i 
        break
    else:
        '''
        updating the radial and angular position using the relations derived
        in the text and in problem 17.1.6
        '''
        dt = Em/(1 - 2/r[i])*dtau 
        dr = - (1 - 2/r[i])*sqrt(1 - (Em**(-2))*(1 - 2/r[i]))*dt
        dphi = Lm/(r[i]**2)*dtau
        r[i+1] = r[i] + dr
        phi[i+1] = phi[i] + dphi

'''
PROBLEM 17.2.4
***
Converting from polar- to cartesian-coordinates using simple trigonometry,
and plotting the path
'''
x = zeros(n)
y = zeros(n)
for i in range(n):
    x[i] = r[i]*cos(phi[i])
    y[i] = r[i]*sin(phi[i])
plot(x,y,'.')

'''
PROBLEM 17.2.5
***
Plotting the Schwarchild radius in the same plot
'''
t = linspace(0,2*pi,100)
X = 2*cos(t)
Y = 2*sin(t)
hold('on')
plot(X,Y)
axis([-5,20,-6,8])
hardcopy('oblig9_plot.png')

'''
PROBLEM 17.2.4
***
Printing the final angular coordinate
'''
print 'Final angular coordinate: %f rad' % phi[n]

# Result:
'''
python oblig9.py 
Final angular coordinate: 2.140281 rad
'''
