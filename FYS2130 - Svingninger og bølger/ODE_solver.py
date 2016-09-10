from scitools.StringFunction import StringFunction
from scitools.all import zeros

class RK4:
    def __init__(self, ODE, dt):
        self.f = StringFunction(ODE)
        self.dt = dt
        
    def initial_conditions(self, z0, v0, t0):
        self.Z = zeros(n+1)
        Z[0] = z0
        self.V = zeros(n+1)
        V[0] = v0
        self.T = zeros(n+1)
        T[0] = t0

    def solve(self):
        for i in xrange(n):
            Z[i+1], V[i+1], T[i+1] = RK4(Z[i], V[i], T[i], dt)
        return Z, V, T




    # Runge-Kutta maskineriet:
    def RK4(zStart,vStart,tStart,dt):
            a1 = f(zStart,vStart,tStart)
            v1 = vStart
            zHalv1 = zStart + v1*(dt/2.0)
            vHalv1 = vStart + a1*(dt/2.0)
            a2 = f(zHalv1, vHalv1, tStart + dt/2.0)
            v2 = vHalv1
            zHalv2 = zStart + v2*(dt/2.0)
            vHalv2 = vStart + a2*(dt/2.0)
            a3 = f(zHalv2, vHalv2, tStart + dt/2.0)
            v3 = vHalv2
            zSlutt = zStart + v3*dt
            vSlutt = vStart + a3*dt
            a4 = f(zSlutt, vSlutt, tStart + dt)
            v4 = vSlutt
            aMidt = (1/6.0) * (a1 + 2*a2 + 2*a3 + a4)
            vMidt = (1/6.0) * (v1 + 2*v2 + 2*v3 + v4)
            zSlutt = zStart + vMidt*dt
            vSlutt = vStart + aMidt*dt
            tSlutt = tStart + dt
            return zSlutt, vSlutt, tSlutt
            
