''' 
This file is a module intended to be imported in the solution-scripts solving 
the various exercises in "FYS1120 Oblig: Cyclotron". It contains the function 
"path" which is an ODE solver, utilizing the euler-cromer method to solve the 
equations of motion for an electrically charged particle, in a constant 
electric and magnetic field. And it includes the function "cyclo_path" which 
is a slightly modified version of "path", custom made to solve the cyclotron 
problem in exercise 3, with the oscillating E-field.
'''

from numpy import *



def path(m, q, E, B, t_start, t_stop, r0, v0): 

    '''
    The function takes the arguments,
    m - mass of the particle
    q - electric charge of the particle
    E - the electric field
    B - the magnetic field
    t_start - time at the start of the integration 
    t_stop - time at the end of the integration
    r0 - array of the components of the initial position
    v0 - array of the components of the initial velocity
    
    The function returns,
    r - 2D-array of the positions
    v - 2D-array of the velocities
    t - array of the times
    '''

    DT = t_stop - t_start
    dt = 1e-4                # Time step
    n = int(DT/dt)           # Number of steps.

    r = zeros((n+1, 3))
    r[0] = r0
    v = zeros((n+1, 3))
    v[0] = v0
    t = zeros(n+1)
    t[0] = t_start
    for i in xrange(n):
        F = q*E + q*cross(v[i], B)   # Lorentz force.
        a = F/float(m) 
        v[i+1] = v[i] + a*dt
        r[i+1] = r[i] + v[i+1]*dt
        t[i+1] = t[i] + dt
    return r, v, t



def cyclo_path(m, q, B, t_start, t_stop, r0, v0):

    '''
    This function is essentially the same as "path". The difference is that 
    this function does not take an E-field as an argument, but instead 
    calculates one based on the cyclotron frequency.
    '''

    B_norm = sqrt(sum(B**2))       # Magnitude of the vector B.
    omega = q*B_norm/float(m)      # The cyclotron frequency.

    DT = t_stop - t_start
    dt = 1e-4               
    n = int(DT/dt)         

    r = zeros((n+1, 3))
    r[0] = r0
    v = zeros((n+1, 3))
    v[0] = v0
    t = zeros(n+1)
    t[0] = t_start
    for i in xrange(n):

        if r[i,0] > -0.1 and r[i,0] < 0.1:      # Conditional statement  
            E = array([cos(omega*t[i]), 0, 0])  # ensuring that the E-field  
        else:                                   # oscillates with the 
            E = array([0, 0, 0])                # cyclotron frequency.

        F = q*E + q*cross(v[i], B)  
        a = F/float(m) 
        v[i+1] = v[i] + a*dt
        r[i+1] = r[i] + v[i+1]*dt
        t[i+1] = t[i] + dt
    return r, v, t




