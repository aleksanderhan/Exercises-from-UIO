dx = 0.1;
x = -20:dx:20;
n = length(x);

A = 2.0;
k = 3.0;
u = A*cos(k*x)

v = 0.5;
dt = 0.1;
faktor = 1;
dudt = A*v*sin(k*x);

dudt2 = ((v^2)/(4*sigma^4))*x.*x.*u - ((v^2)/(2*sigma^2)).*u;

u_jminus1 = u - dt*dudt;
u_j = u ;

for t = 1:1000
    u_jplus1(2:n-1) = u_j(2:n-1) - u_jminus1(2:n-1) + (u_j(3:n)+u_j(1:n-2));
    
    u_jplus1(1) = (2*(1-faktor)).*u_j(1) - u_jminus1(1) + faktor.*u_j(2);
    u_jplus1(n) = (2*(1-faktor)).*u_j(n) - u_jminus1(n) + faktor.*u_j(n-1);
    
    plot(u_j);
    %axis([0 n+1 -10.2 10.2])
    drawnow;
    
    u_jminus1 = u_j;
    u_j = u_jplus1;
end
