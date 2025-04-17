package org.napbad.software_test;

// 找出假球的类
public class FakeBallFinder {
    // 找出假球的方法，接收一个包含10个球重量的数组
    public int findFakeBall(int[] balls) {
        // 第一次称，比较前5个球和后5个球的重量总和
        int sumLeft = 0;
        int sumRight = 0;
        for (int i = 0; i < 5; i++) {
            sumLeft += balls[i];
            sumRight += balls[i + 5];
        }
        // 轻的一边有假球
        int[] lighterGroup;
        int startIndex;
        if (sumLeft < sumRight) {
            lighterGroup = new int[5];
            System.arraycopy(balls, 0, lighterGroup, 0, 5);
            startIndex = 0;
        } else {
            lighterGroup = new int[5];
            System.arraycopy(balls, 5, lighterGroup, 0, 5);
            startIndex = 5;
        }
        // 第二次称，比较前2个球和后2个球的重量总和
        int sumLeft2 = lighterGroup[0] + lighterGroup[1];
        int sumRight2 = lighterGroup[2] + lighterGroup[3];
        if (sumLeft2 == sumRight2) {
            return startIndex + 4;
        }
        // 轻的一边有假球
        int[] lighterPair;
        int pairStartIndex;
        if (sumLeft2 < sumRight2) {
            lighterPair = new int[]{lighterGroup[0], lighterGroup[1]};
            pairStartIndex = startIndex;
        } else {
            lighterPair = new int[]{lighterGroup[2], lighterGroup[3]};
            pairStartIndex = startIndex + 2;
        }
        // 第三次称，比较两个球的重量
        if (lighterPair[0] < lighterPair[1]) {
            return pairStartIndex;
        } else {
            return pairStartIndex + 1;
        }
    }
}