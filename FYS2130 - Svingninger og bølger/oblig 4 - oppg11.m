Fs = 512;
dt = 1/Fs;
N = 1024;
t = (0:N-1)*dt;

% rent sinussignal
y = 2.0*square(16*2*pi*t);

figure
plot(t, y);

Y = fft(y, N)/N;
f = (Fs/2)*linspace(0, 1, N/2+1);

figure
plot(f, 2*abs(Y(1:N/2+1)));
xlabel('frekvens')
ylabel('|frekvens komp.|')