package com.cdu.lhj.bstest.util;

public class SimpleTimestampIdGenerator {
    private static final long START_EPOCH = 1609459200000L; // 设置一个开始时间（例如：2021年1月1日的毫秒时间戳）
    private static long lastTimestamp = -1L;
    private static long sequence = 0L;

    public synchronized static long nextId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp == lastTimestamp) {
            // 同一毫秒内，序列号自增
            sequence++;
        } else {
            // 不同毫秒内，重置序列号
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        // 移除开始时间偏差，以缩短ID长度，然后左移一定位数加上序列号
        return (timestamp - START_EPOCH) << 8 | sequence;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(nextId());
        }
    }
}

