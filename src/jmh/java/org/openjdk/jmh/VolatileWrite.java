package org.openjdk.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
public class VolatileWrite {
    int v;
    volatile int vv;

    @Param({"0", "20"})
    int tokens;

    @Benchmark
    public int baseline() {
        Blackhole.consumeCPU(tokens);
        return 42;
    }

    @Benchmark
    public int incrPlain() {
        Blackhole.consumeCPU(tokens);
        return v++;
    }


    @Benchmark
    public int incrVolatile() {
        Blackhole.consumeCPU(tokens);
        return vv++;
    }

}

