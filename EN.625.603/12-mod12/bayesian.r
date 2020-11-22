# init prior & posterior
prior = function(r, s, theta){
    (gamma(r+s)/(gamma(r)*gamma(s)))*theta^(r-1)*(1-theta)^(s-1)
}

posterior = function(r, s, n, k, theta){
    (gamma(n+r+s)/(gamma(r+k)*gamma(s+n-k)))*theta^(r+k-1)*(1-theta)^(n+s-k-1)
}

# init theta
theta = seq(0,1,length=100)

# (r,s) = (1,1) & (n,k) = (4,3)
r = 1
s = 1
n = 4
k = 3
prior_1 = prior(r,s,theta)
post_1 = posterior(r,s,n,k,theta)
plot(theta, post_1,"l")
lines(theta, prior_1)

# (r,s) = (1,1) & (n,k) = (20,11)
r = 1
s = 1
n = 20
k = 11
prior_2 = prior(r,s,theta)
post_2 = posterior(r,s,n,k,theta)
plot(theta, post_2,"l")
lines(theta, prior_2)

# (r,s) = (4,4) & (n,k) = (4,3)
r = 4
s = 4
n = 4
k = 3
prior_3 = prior(r,s,theta)
post_3 = posterior(r,s,n,k,theta)
plot(theta, post_3,"l")
lines(theta, prior_3)

# (r,s) = (4,4) & (n,k) = (20,11)
r = 4
s = 4
n = 20
k = 11
prior_4 = prior(r,s,theta)
post_4 = posterior(r,s,n,k,theta)
plot(theta, post_4,"l")
lines(theta, prior_4)



# (r,s) = (1,1) & (n,k) = (4,3)
r = 1
s = 1
n = 4
k = 3
a = r + k
b = n + s - k

p1 = 1 - pbeta(0.5, a,b)

# (r,s) = (1,1) & (n,k) = (20,11)
r = 1
s = 1
n = 20
k = 11
a = r + k
b = n + s - k

p2 = 1 - pbeta(0.5, a,b)

# (r,s) = (4,4) & (n,k) = (4,3)
r = 4
s = 4
n = 4
k = 3
a = r + k
b = n + s - k

p3 = 1 - pbeta(0.5, a,b)

# (r,s) = (4,4) & (n,k) = (20,11)
r = 4
s = 4
n = 20
k = 11
a = r + k
b = n + s - k

p4 = 1 - pbeta(0.5, a,b)



