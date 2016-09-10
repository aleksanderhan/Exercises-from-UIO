from scitools.all import *
from FYS1120_oblig_lib import cyclo_path


##### Part a #####
m = 1
q = 1
B_a = array([0, 0, 1])
r0_a = array([0, 0, 0])
v0_a = array([0, 0, 0])
r_a, v_a, t_a = cyclo_path(m, q, B_a, 0, 50, r0_a, v0_a)

figure(1)
plot(r_a[:,0], r_a[:,1])
xlabel('x(t)')
ylabel('y(t)')
hardcopy('3a.png')


##### Part b #####
rd = 2.6
idx = 0

# Finding the index where the 2-norm >= rd.
while (sqrt(sum(r_a[idx]**2)) < rd):  
    idx += 1
# Replacing v(t) for t >= t[idx] with v(t[idx]). 
# And calculating the new positions.
n = len(v_a)
r_b = zeros((n, 3))
v_b = zeros((n, 3))
t_b = t_a
dt = t_b[1] - t_b[0]
for i in xrange(n):
    if i <= idx:
        v_b[i] = v_a[i]
        r_b[i] = r_a[i]
    else:
        v_b[i] = v_a[idx]
        r_b[i] = r_b[i-1] + v_a[idx]*dt
 

figure(2)
subplot(121)
plot(r_b[:,0], t_b)
hold('on')
plot(r_b[:,1], t_b)
plot(r_b[:,2], t_b)
legend('x(t)', 'y(t)', 'z(t)')
xlabel('position')
ylabel('time')

subplot(122)
plot(v_b[:,0], t_b)
hold('on')
plot(v_b[:,1], t_b)
plot(v_b[:,2], t_b)
legend('v(t)_x', 'v(t)_y', 'v(t)_z')
xlabel('velocity')
ylabel('time')
hardcopy('3b.png')


##### Part C #####
esc_spd = str(sqrt(sum(v_b[idx]**2)))
print 'The particle\'s speed as it leaves the cyclotron is: ' + esc_spd[:5]

