filnavn = 'piccoloH.wav'
N = 2^16;
Nstart = 1;
[y, Fs, type] = wavread(filnavn, [Nstart, Nstart+N+1]);

wavplay(y, Fs);
g = y(:,1);

Y = fft(g, N)/N;
f = (Fs/2)*linspace(0, 1, N/2 + 1);

plot(f, 2*abs(Y(1:N/2 + 1)))
xlim([0, 2000])
xlabel('frekvens')
ylabel('amplitude')
title(filnavn)
