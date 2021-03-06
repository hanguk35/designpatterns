/*
 * @(#)EnchantedRoom.java   2011-11-01
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



package gamma.designpatterns.creational.maze;

/**
 *  <p>
 *
 *  Title: Design Patterns</p> <p>
 *
 *  Description: </p> <p>
 *
 *  Copyright: Copyright (c) 2002</p> <p>
 *
 *  Company: GioPerLab</p>
 *
 * @author     giorgio_peron@libero.it
 * @created    8 giugno 2002
 * @version    1.0
 */
public class EnchantedRoom extends Room {
    private Spell spell;

    /**
     *  Constructor for the EnchantedRoom object
     *
     * @param  roomNumber  Description of the Parameter
     * @param  spell       Description of the Parameter
     */
    public EnchantedRoom(int roomNumber, Spell spell) {
        super(roomNumber);
        this.spell = spell;
    }

    /**
     *  Constructor for the EnchantedRoom object
     *
     * @param  room  Description of the Parameter
     */
    protected EnchantedRoom(EnchantedRoom room) {
        super(room);
        this.spell = room.spell;
    }

    /**
     *  Description of the Method
     *
     * @return    Description of the Return Value
     */
    public String toString() {
        return super.toString() + " spell " + spell;
    }

    /**
     *  Description of the Method
     *
     * @return                                           Description of the
     *      Return Value
     * @exception  java.lang.CloneNotSupportedException  Description of the
     *      Exception
     */
    public Object clone() throws java.lang.CloneNotSupportedException {
        return new EnchantedRoom(this);
    }
}
