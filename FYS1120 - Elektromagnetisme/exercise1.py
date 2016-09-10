from scitools.all import *
from mayavi import mlab
from FYS1120_oblig_lib import path


###### Part a #####
m = 2
q = 3
E_a = array([5, 0, 0])
B_a = array([0, 0, 0])
r0_a = array([0, 0, 0])
v0_a = array([0, 0, 0])
r_a, v_a, t_a = path(m, q, E_a, B_a, 0, 1, r0_a, v0_a)

figure(1)
plot(r_a[:,0], t_a)
legend('x(t)')
xlabel('position')
ylabel('time')
hardcopy('1a.png')


##### Part b #####
t = linspace(0, 1, 1000)
x_exact = q*E_a[0]*(t**2)/float(2*m) + v0_a[0]*t + r0_a[0]

figure(2)
plot(x_exact, t)
hold('on')
plot(r_a[:,0], t_a)
legend('x(t)_exact', 'x(t)_numerical')
xlabel('position')
ylabel('time')
hardcopy('1b.png')


##### Part c #####
E_c = array([1, 2, -5])
B_c = B_a
r0_c = r0_a
v0_c = v0_a
r_c, v_c, t_c = path(m, q, E_c, B_c, 0, 1, r0_c, v0_c)

figure(3)
subplot(121)
plot(r_c[:,0], t_c)
hold('on')
plot(r_c[:,1], t_c)
plot(r_c[:,2], t_c)
legend('x(t)', 'y(t)', 'z(t)')
xlabel('position')
ylabel('time')

subplot(122)
plot(v_c[:,0], t_c)
hold('on')
plot(v_c[:,1], t_c)
plot(v_c[:,2], t_c)
legend('v(t)_x', 'v(t)_y', 'v(t)_z')
xlabel('velocity')
ylabel('time')
hardcopy('1c.png')


###### Part d #####
mlab.figure(figure = '1d3D', bgcolor = (1,1,1), fgcolor = (0,0,0))
mlab.plot3d(r_c[:,0], r_c[:,1], r_c[:,2], t_c)
mlab.axes()
raw_input('Fix the plot manually, then press any key')
mlab.savefig('1d-3D.png')
mlab.close('1d3D')
