from scitools.all import *
import random

def Pressure(T,N,dx,dt):
    k = 1.38e-23  # Boltzmann constant [m^2 kg s^-2 K^-1]
    m = 1.67e-27  # Weight of hydrogen atom [kg]

    sigma = sqrt(k*T/m)

    # Random distribution of the positions and velocities of each particle
    x = zeros(N)
    v = zeros(N)
    for i in range(N):
        x[i] = random.uniform(0,dx)
        v[i] = random.gauss(0,sigma)

    # Calculating the positions of the particles dt in the past and the future
    # and determining if they hit the wall
    F = 0   # Sum of the force
    for i in range(N):
        x_pluss_vdt = x[i] + v[i]*dt
        x_minus_vdt = x[i] - v[i]*dt
        if x_pluss_vdt > 0.1:
            F += 2*m*abs(v[i])/(2*dt)
        if x_minus_vdt > 0.1:
            F += 2*m*abs(v[i])/(2*dt)
    P = F/dx**2   # The pressure on one of the walls
    print 'The pressure on the wall is %g Pa, at T = %d K' % (P, T)
    return P


# Main
N = int(1e7)               # Number of particles
dx = 0.1                   # Size of the cubed box [m]
dt = 1e-9                  # Time step [s]
T = [6e3, 5e4, 15e6, 1e9]  # Temperatures [K]
P = []                     # Pressures corresponding to temperatures in T
for i in T:
    P.append(Pressure(i,N,dx,dt))

# The analytic expression
k = 1.38e-23  # Boltzmann constant [m^2 kg s^-2 K^-1]
n = N/dx**3   # Density = number of particles/volum
T_analytic = linspace(6e3,1e9,100)
P_analytic = zeros(100)
for i in range(100):
    P_analytic[i] = n*k*T_analytic[i]

# Log-log plot of the analytic solution and the simulated values
loglog(T_analytic,P_analytic)
hold('on')
loglog(T,P,'o')
xlabel('Temperature in kelvin')
ylabel('Pressure in Pascal')
hardcopy('loglog.png')




