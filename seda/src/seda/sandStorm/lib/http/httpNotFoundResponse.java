/*
 * Copyright (c) 2001 by Matt Welsh and The Regents of the University of 
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

package seda.sandStorm.lib.http;

import seda.sandStorm.api.*;
import seda.sandStorm.lib.aSocket.*;
import seda.sandStorm.core.*;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * An httpResponse corresponding to a '404 Not Found' error.
 * 
 * @author Matt Welsh
 * @see httpResponse
 */
public class httpNotFoundResponse extends httpResponse implements httpConst, QueueElementIF {

  private static final boolean DEBUG = false;

  /**
   * Create an httpNotFoundResponse corresponding to the given request
   * with the given reason.
   */
  public httpNotFoundResponse(httpRequest request, String reason) {
    super(httpResponse.RESPONSE_NOT_FOUND, "text/html");

    String str = "<html><head><title>404 Not Found</title></head><body bgcolor=white><font face=\"helvetica\"><big><big><b>404 Not Found</b></big></big><p>The URL you requested:<p><blockquote><tt>"+request.getURL()+"</tt></blockquote><p>could not be found. The reason given by the server was:<p><blockquote><tt>"+reason+"</tt></blockquote></body></html>\n";
    BufferElement mypayload = new BufferElement(str.getBytes());
    setPayload(mypayload);
  }

  protected String getEntityHeader() {
    return null;
  }

}
