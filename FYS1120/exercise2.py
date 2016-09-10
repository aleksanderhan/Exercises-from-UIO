from scitools.all import *
from mayavi import mlab
from FYS1120_oblig_lib import path


##### Part a #####
m = 2
q = 3
E_a = array([0, 0, 0])
B_a = array([0, 0, 3])
r0_a = array([0, 0, 0])
v0_a = array([5, 0, 0])
r_a, v_a, t_a = path(m, q, E_a, B_a, 0, 5, r0_a, v0_a)

figure(1)
subplot(121)
plot(r_a[:,0], t_a)
hold('on')
plot(r_a[:,1], t_a)
plot(r_a[:,2], t_a)
legend('x(t)', 'y(t)', 'z(t)')
xlabel('position')
ylabel('time')

subplot(122)
plot(v_a[:,0], t_a)
hold('on')
plot(v_a[:,1], t_a)
plot(v_a[:,2], t_a)
legend('v(t)_x', 'v(t)_y', 'v(t)_z')
xlabel('velocity')
ylabel('time')
hardcopy('2a.png')

mlab.figure(figure = '2a3D', bgcolor = (1,1,1), fgcolor = (0,0,0))
mlab.plot3d(r_a[:,0], r_a[:,1], r_a[:,2], t_a)
mlab.axes()
raw_input('Fix the plot manually, then press any key')
mlab.savefig('2a-3D.png')
mlab.close('2a3D')

'''
Doesn't work..
##### Part b #####
# Finding the difference between two times corresponding to a sign change of
# the velocity, and then calculating the period. (ignoring the first change)
t = zeros(3)
k, i = 0, 0
while k != 3:
    if (sign(v_a[i+1, 0]) != sign(v_a[i, 0])):
        t[k] = t_a[i]
        k += 1
    i += 1
T = (t[3]-t[2])*2
print 'The period of the motion is: ' + str(T)
'''

##### Part d #####
E_d = E_a
B_d = B_a
r0_d = r0_a
v0_d = array([5, 0, 2])
r_d, v_d, t_d = path(m, q, E_d, B_d, 0, 5, r0_d, v0_d)

mlab.figure(figure = '2d3D', bgcolor = (1,1,1), fgcolor = (0,0,0))
mlab.plot3d(r_d[:,0], r_d[:,1], r_d[:,2], t_d)
mlab.axes()
raw_input('Fix the plot manually, then press any key')
mlab.savefig('2d-3D.png')
mlab.close('2d3D')

