/*
 * @(#)VisitorRectangle.java   2011-11-01
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



package cooper.designpatterns.behavioral.state;

import java.awt.*;

/**
 * Class description
 *
 *
 * @version        0.1.1, 2011-11-01
 * @author         <a href="mailto:giorgio.peron@gmail.com">Giorgio Peron</a>
 */
public class VisitorRectangle extends Drawing {

    /**
     * Constructs ...
     *
     *
     * @param xpt
     * @param ypt
     */
    public VisitorRectangle(int xpt, int ypt) {
        x = xpt;
        y = ypt;
        w = 40;
        h = 30;
        saveAsRect();
    }

    /**
     * Method description
     *
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.drawRect(x, y, w, h);
        if (filled) {
            g.setColor(fillColor);
            g.fillRect(x, y, w, h);
        }
        if (selected) {
            g.setColor(Color.black);
            g.fillRect(x + w / 2, y - 2, 4, 4);
            g.fillRect(x - 2, y + h / 2, 4, 4);
            g.fillRect(x + w / 2, y + h - 2, 4, 4);
            g.fillRect(x + w - 2, y + h / 2, 4, 4);
        }
    }
}


class RectangleMemento extends Memento {
    VisitorRectangle rect;
    // saved fields- remember internal fields
    // of the specified visual rectangle
    int x, y, w, h;

    /**
     * Constructs ...
     *
     *
     * @param r
     */
    public RectangleMemento(VisitorRectangle r) {
        super(r);
        rect = r;
        x = rect.x;
        y = rect.y;
        w = rect.w;
        h = rect.h;
    }

    /**
     * Method description
     *
     */
    public void restore() {
        // restore the internal state of
        // the specified rectangle
        rect.x = x;
        rect.y = y;
        rect.h = h;
        rect.w = w;
    }
}
