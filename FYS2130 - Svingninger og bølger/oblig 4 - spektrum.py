from pylab import *

file = open('spectrum.txt', 'r')
freq = []
amp = []
for line in file:
    data = line.split()
    f = data[0].split(',')
    f = str(f[0])+'.'+str(f[1])
    f = float(f)
    freq.append(f)
    a = data[1].split(',')
    a = a[0]+'.'+a[1]
    a = float(a)
    amp.append(a)

plot(freq, amp)
xlabel('frequency')
ylabel('amplitude')
savefig('tubaL.png')
show()
