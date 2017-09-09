/* 
 * Copyright (c) 2000 by Matt Welsh and The Regents of the University of 
 * California. All rights reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for any purpose, without fee, and without written agreement is
 * hereby granted, provided that the above copyright notice and the following
 * two paragraphs appear in all copies of this software.
 * 
 * IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
 * DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
 * OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
 * CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
 * ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
 * PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 * Author: Matt Welsh <mdw@cs.berkeley.edu>
 * 
 */

package seda.sandStorm.lib.aDisk;

import seda.sandStorm.api.*;
import seda.sandStorm.core.*;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * The event handler used by the AFileTPImpl stage.
 *
 * @author Matt Welsh
 */
class AFileTPEventHandler implements EventHandlerIF {

  private static final boolean DEBUG = false;

  protected SinkIF eventQ;

  public void init(ConfigDataIF config) {
  }

  public void destroy() {
  }

  public void handleEvent(QueueElementIF qel) {
    // XXX Implement
  }

  public void handleEvents(QueueElementIF qelarr[]) {
    for (int i = 0; i < qelarr.length; i++) {
      handleEvent(qelarr[i]);
    }
  }

}

