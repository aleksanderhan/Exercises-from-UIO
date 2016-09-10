filnavn = 'tempBlindern10aar.txt';
fileID = fopen(filnavn, 'r');
A = fscanf(fileID, '%d %d %f %f %f', [5, inf]);
minT = A(4,:);
maxT = A(5,:);
figure(1)
plot(minT, '-r');
hold on;
plot(maxT, '-b');
xlabel('tid [dager]')
ylabel('temp [Celsius]')
legend('min temp', 'max temp')

Fs = 29
N = length(maxT);

Xmax = fft(maxT,N)/N;
Xmin = fft(minT,N)/N;
f = (Fs/2)*linspace(0, 1, N/2+1);

figure(2)
plot(f, 2*abs(Xmin(1:N/2+1)),'r-');
hold on;
plot(f, 2*abs(Xmax(1:N/2+1)),'b-');
xlabel('frekvens')
ylabel('|frekvens komp.|')
legend('min temp','max temp')
xlim([0,20])