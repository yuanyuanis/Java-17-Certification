package com.yuanyuanis.concurrency.uni.u1.b_multihilo.d_threadProblems;

public class Interbloqueo {

    // R1
    // R2

    // P1
    // P2

    //P1 -->  consumirR1yR2
                    // consumir R1
                        // espere
                    // consumir R2
    //P2 -->  consumirR2yR1
                    // consumir R2
                        // espere
                    // consumir R1

}
