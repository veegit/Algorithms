package com.vee.algorithms.others;

import java.math.BigDecimal;

class Sqrt {
    public int mySqrt(int x) {
        BigDecimal root = new BigDecimal(x).divide(new BigDecimal(2));//x0
        BigDecimal newroot = step(root, new BigDecimal(x));//x1
        while (root.intValue() != newroot.intValue()) {
            System.out.println(root);
            root = newroot;
            newroot = step(root, new BigDecimal(x));//x1
        }
        return root.intValue();
    }
    
    public BigDecimal step(BigDecimal root, BigDecimal num) {
        return root
                .subtract(
                    root.multiply(root)
                        .subtract(num)
                        .divide(
                            root.multiply(new BigDecimal(2)),4, 3));
    }

    public static void main(String args[]) {
        System.out.println(new Sqrt().mySqrt(4));
    }
}