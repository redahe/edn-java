package us.bpsm.edn.protocols.c3;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class C3Test {

    @Test
    public void testMroExample1() {
        assertEquals(
                Arrays.asList(
                            X1.A.class,
                            X1.B.class,
                            X1.C.class,
                            X1.D.class,
                            X1.E.class,
                            X1.F.class,
                            X1.O.class),
                C3.methodResolutionOrder(X1.A.class));
    }

    interface X1 {
        interface O {}
        interface F extends O {}
        interface E extends O {}
        interface D extends O {}
        interface C extends D, F {}
        interface B extends D, E {}
        interface A extends B, C {}
    }

    @Test
    public void testMroExample2() {
        assertEquals(
                Arrays.asList(
                            X2.A.class,
                            X2.B.class,
                            X2.E.class,
                            X2.C.class,
                            X2.D.class,
                            X2.F.class,
                            X2.O.class),
                C3.methodResolutionOrder(X2.A.class));
    }

    interface X2 {
        interface O {}
        interface F extends O {}
        interface E extends O {}
        interface D extends O {}
        interface C extends D, F {}
        // X2 has B extend "E, D", while X1 extends "D, E"
        interface B extends E, D {}
        interface A extends B, C {}
    }

    /*
        L[A] = A O
        L[B] = B O
        L[C] = C O
        L[D] = D O
        L[E] = E O
        L[K1]= K1 A B C O
        L[K2]= K2 D B E O
        L[K3]= K3 D A O
        L[Z] = Z K1 K2 K3 D A B C E O
     */

    @Test
    public void testMroExample3() {
         assertEquals(
                Arrays.<Class<?>>asList(X3.A.class, X3.O.class),
                C3.methodResolutionOrder(X3.A.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.B.class, X3.O.class),
                C3.methodResolutionOrder(X3.B.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.C.class, X3.O.class),
                C3.methodResolutionOrder(X3.C.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.D.class, X3.O.class),
                C3.methodResolutionOrder(X3.D.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.E.class, X3.O.class),
                C3.methodResolutionOrder(X3.E.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.K1.class, X3.A.class, X3.B.class,
                                        X3.C.class, X3.O.class),
                C3.methodResolutionOrder(X3.K1.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.K2.class, X3.D.class, X3.B.class,
                                        X3.E.class, X3.O.class),
                C3.methodResolutionOrder(X3.K2.class));
         assertEquals(
                Arrays.<Class<?>>asList(X3.K3.class, X3.D.class, X3.A.class,
                                        X3.O.class),
                C3.methodResolutionOrder(X3.K3.class));
         assertEquals(
                Arrays.asList(X3.Z.class, X3.K1.class, X3.K2.class,
                              X3.K3.class, X3.D.class, X3.A.class,
                              X3.B.class, X3.C.class, X3.E.class,
                              X3.O.class),
                C3.methodResolutionOrder(X3.Z.class));
    }

    interface X3 {
        interface O {}
        interface A extends O {}
        interface B extends O {}
        interface C extends O {}
        interface D extends O {}
        interface E extends O {}
        interface K1 extends A,B,C {}
        interface K2 extends D, B, E {}
        interface K3 extends D, A {}
        interface Z extends K1, K2, K3 {}
    }

    @Test(expected=RuntimeException.class)
    public void testMroExample4OrderDisagreement() {
        C3.methodResolutionOrder(X4.Z.class);
    }

    /** order disagreement */
    interface X4 {
        interface O {}
        interface X extends O {}
        interface Y extends O {}
        interface A extends X, Y {}
        interface B extends Y, X {}
        interface Z extends A, B {}
    }
}
