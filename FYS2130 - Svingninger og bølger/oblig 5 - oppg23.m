k = 3;
omega = 8;
N = 1000;
x = linspace(0, 20, N);
y = linspace(0, 20, N);
p = plot(x, y, '-', 'EraseMode' ,'xor');
axis([0 20 -2.5 2.5])
for i=1:200
    t = i*0.01;
    y = 2.0*sin(k*x - omega*t) + 2.0*sin(k*x + omega*t);
    set(p, 'XData', x, 'YData', y)
    drawnow
    pause(0.02)
end
