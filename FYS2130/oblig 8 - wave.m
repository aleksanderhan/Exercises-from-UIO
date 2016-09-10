N = 1000;
dt = 0.01;
E0 = 5.0;
k = 6;
w = 12;
y = linspace(0,2*pi,N);
t = 0.0;

for i=1:N
    i
    E = E0*cos(k*y - w*t);
    plot(E);
    drawnow;
    t = t + dt;
end