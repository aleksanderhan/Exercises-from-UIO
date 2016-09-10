from scitools.all import *

# Reading galaxy data
file = open('galaxies.txt', 'r')
x = []          # angular distance along the x-axis [arcminutes]
y = []          # angular distance along the y-axis [arcminutes]
r = []          # radial distance from earth [Mpc]
lambda1 = []    # position of spectral line [meters]
for line in file:
    line = line.split()
    x.append(float(line[0]))
    y.append(float(line[1]))
    r.append(float(line[2]))
    lambda1.append(float(line[3]))
file.close()

# making arrays out of the lists
x = array(x)
y = array(y)
r = array(r)
lambda1 = array(lambda1)

# Peculiar velocity
c = 299792458     # speed of light in vacuum [m/s]
lambda0 = 0.212   # wavelength of the spectral line in the laboratory frame [meters]
rad_vel_g = [((l - lambda0)/lambda0)*c for l in lambda1]     # radial velocity of each galaxy
rad_vel_c = sum(rad_vel_g)/len(rad_vel_g)                    # radial velocity of the cluster
print 'radial velocity of the cluser = %.3g m/s' % rad_vel_c

# Plot of the angular position
plot(x,y,'o')
xlabel('angular distance along the x-axis [arcminutes]')
ylabel('angular distance along the y-axis [arcminutes]')
hardcopy('angular_position.png')

# mass of a galaxy
G = 6.67e-11                         # the gravitational constant
r = r*(3.0857e22)                    # 1 Mpc = 3.0857 meters
X = 
rel_vel_g = rad_vel_g - rad_vel_c    # velocity of the galaxies relative to the CM of the cluster
R = []    # 1/r_ij
for i in range(rel_vel_g):
    for j in range():
        rirj = sqrt((x[j]-x[i])**2 + (y[j]-y[i])**2 + (r[j]-r[i])**2)
        R.append(1/rirj)

V = 
m = (sum
