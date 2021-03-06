/*
 * @(#)Scale.java   2011-11-01
 * 
 * Copyright (c) 2011 Giorgio Peron giorgio.peron@gmail.com
 * All Rights Reserved. 
 *
 * Redistribution and use of this script, with or without modification, is
 * permitted provided that the following conditions are met:
 *
 * 1. Redistributions of this script must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */



package metsker.designpatterns.structural.decorator;

/*
* Copyright (c) 2001, 2005. Steven J. Metsker.
*
* Steve Metsker makes no representations or warranties about
* the fitness of this software for any particular purpose,
* including the implied warranty of merchantability.
*
* Please use this software as you wish with the sole
* restriction that you may not claim that you wrote it.
 */

/**
 * The Scale function represents a linear interpolation. For example, Fahrenheit
 * temperature goes 32 to 212 as Celsius goes 0 to 100. If we have a Frapper c
 * that tells Celsius as a function of time, we can calculate Fahrenheit with:
 * Frapper f = new Scale( new Constant(0), c, new Constant(100), new
 * Constant(32), new Constant(212));
 */
public class Scale extends Function {

    /**
     * Construct a Scale that goes from "from" to "to" as time goes 0 to 1.
     *
     * @param from
     *            the "from" number
     * @param to
     *            the "to" number
     */
    public Scale(double from, double to) {
        this(new Constant(from), new Constant(to));
    }

    /**
     *
     * Construct a Scale that goes from "aFrom" to "aTo" as function b goes from
     * "bFrom" to "bTo".
     *
     * @param aFrom
     *            the "from" value on the "a" scale (usually constant)
     * @param a
     *            the "a" function that typical varies with time
     * @param aTo
     *            the "to" value ont the "a" scale
     * @param bFrom
     *            the "from" value on the "b" scale
     * @param bTo
     *            the "to" value on the "b" scale
     */
    public Scale(double aFrom, Function a, double aTo, double bFrom, double bTo) {
        this(new Constant(aFrom), a, new Constant(aTo), new Constant(bFrom), new Constant(bTo));
    }

    /**
     * Construct a Scale that goes from "from" to "to" as time goes 0 to 1.
     *
     * @param f1
     *            the "from" function
     * @param f2
     *            the "to" function
     */
    public Scale(Function f1, Function f2) {
        this(new Constant(0), new T(), new Constant(1), f1, f2);
    }

    /**
     * @param aFrom
     *            the "from" value on the "a" scale (usually constant)
     * @param a
     *            the "a" function that typical varies with time
     * @param aTo
     *            the "to" value ont the "a" scale
     * @param bFrom
     *            the "from" value on the "b" scale
     * @param bTo
     *            the "to" value on the "b" scale
     */
    public Scale(Function aFrom, Function a, Function aTo, Function bFrom, Function bTo) {
        super(new Function[] { aFrom, a, aTo, bFrom, bTo });
    }

    /**
     * Return value as a linear function that goes from "bFrom" to "bTo"
     * as "a" goes from "aFrom" to "aTo".
     *
     * @param t
     *            a normalized "time" between 0 and 1
     *
     * @return
     */
    public double f(double t) {
        double aFrom = sources[0].f(t);
        double a = sources[1].f(t);
        double aTo = sources[2].f(t);
        double bFrom = sources[3].f(t);
        double bTo = sources[4].f(t);
        double denom = aTo - aFrom;
        if (denom == 0) {
            return (bTo + bFrom) / 2;
        }

        return (a - aFrom) / denom * (bTo - bFrom) + bFrom;
    }
}
