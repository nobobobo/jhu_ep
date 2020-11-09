tyf <- c(3.525, 3.625, 3.383, 3.625, 3.661, 3.791, 3.941, 3.781, 3.660, 3.733)
arm <- c(2.923, 3.385, 3.154, 3.363, 3.226, 3.283, 3.427, 3.437, 3.746, 3.438)
cmb <- c(tyf, arm)

# observed Test statistic
Tobs = mean(tyf) - mean(arm)

# Randomization Test
Ts = vector(,1000)

for (i in 1:1000){
    smpl = sample(cmb, 20)
    smpl1 = smpl[1:10]
    smpl2 = smpl[11:20]
    Ts[i] = mean(smpl1) - mean(smpl2)
}

# plot
hist(Ts)
plot(density(Ts))
abline(v=c(-1,1)*Tobs,lwd=2,col="red")

# calculate p-value proportion of samples have test statistics less than -1 * Tobs, or greater than Tobs

sum(Ts > Tobs | Ts < -1*Tobs)/1000
