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

package seda.sandStorm.lib.aSocket;

import seda.sandStorm.api.*;
import seda.sandStorm.core.*;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Internal class used to represent a server socket listening on a 
 * given port.
 */
public abstract class ListenSockState {

  private static final boolean DEBUG = false;

  protected static int num_connections = 0;

  protected ATcpServerSocket servsock;
  protected int port;
  protected SinkIF compQ;
  protected int writeClogThreshold;

  protected abstract int getLocalPort();
  protected abstract Socket accept() throws IOException;
  protected abstract void suspend();
  protected abstract void resume();
  protected abstract void close();
  protected abstract void complete(ATcpConnection conn);
}

