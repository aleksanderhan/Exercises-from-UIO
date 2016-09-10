from scitools.all import *
import random

k = 1.38e-23  # Boltzmann constant [m^2 kg s^-2 K^-1]
T = 6000      # Temperature [K]
m = 1.67e-27  # Weight of hydrogen atom [kg]
N = 10000     # Number of particles

sigma = sqrt(k*T/m)

# Random distribution of velocities for each particle
v = zeros((N,3))
for i in range(N):
    for j in range(3):
        v[i,j] = random.gauss(0,sigma)

# Calculating the kinetic energy of the particles and taking the mean
KE = zeros(N)
for i in range(N):
    KE[i] = 0.5*m*(v[i,0]**2 + v[i,1]**2 + v[i,2]**2)
mean_KE = sum(KE)/N

print 'Mean kinetic energy: %g J, with %d particles' % (mean_KE, N)

# Result
'''
Mean kinetic energy: 1.23652e-19 J, with 10000 particles
Mean kinetic energy: 1.24281e-19 J, with 100000 particles
'''
