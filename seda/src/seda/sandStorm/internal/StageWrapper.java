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

package seda.sandStorm.internal;

import seda.sandStorm.api.*;
import seda.sandStorm.api.internal.*;
import seda.sandStorm.core.*;
import java.util.*;

/**
 * A StageWrapper is a basic implementation of StageWrapperIF for 
 * application-level stages.
 * 
 * @author   Matt Welsh
 */

class StageWrapper implements StageWrapperIF {

  private String name;
  private StageIF stage;
  private EventHandlerIF handler;
  private ConfigDataIF config;
  private FiniteQueue eventQ;
  private ThreadManagerIF threadmgr;
  private StageStatsIF stats;
  private ResponseTimeControllerIF rtcon;

  /**
   * Create a StageWrapper with the given name, handler, config data, and 
   * thread manager.
   */
  StageWrapper(ManagerIF mgr, String name, EventHandlerIF handler, ConfigDataIF config, ThreadManagerIF threadmgr) {
    this.name = name;
    this.handler = handler;
    this.config = config;
    this.threadmgr = threadmgr;
    eventQ = new FiniteQueue(name);
    this.stats = new StageStats(this);
    this.stage = new Stage(name, this, (SinkIF)eventQ, config);
    config.setStage(this.stage);
    createRTController(mgr);
  }

  /**
   * Create a StageWrapper with the given name, handler, config data, thread 
   * manager, and queue threshold.
   */
  StageWrapper(ManagerIF mgr, String name, EventHandlerIF handler, ConfigDataIF config, ThreadManagerIF threadmgr, int queueThreshold) {
    this.name = name;
    this.handler = handler;
    this.config = config;
    this.threadmgr = threadmgr;
    this.stats = new StageStats(this);
    this.rtcon = null;

    eventQ = new FiniteQueue(name);
    QueueThresholdPredicate pred = new QueueThresholdPredicate(eventQ, queueThreshold);
    eventQ.setEnqueuePredicate(pred);

    this.stage = new Stage(name, this, (SinkIF)eventQ, config);
    config.setStage(this.stage);
    createRTController(mgr);
  }

  private void createRTController(ManagerIF mgr) {
    boolean rtControllerEnabled = mgr.getConfig().getBoolean("global.rtController.enable");
    String deftype = mgr.getConfig().getString("global.rtController.type");
    if (mgr.getConfig().getBoolean("stages."+name+".rtController.enable", rtControllerEnabled)) {
      String contype = mgr.getConfig().getString("stages."+name+".rtController.type", deftype);
      if (contype == null) {
	this.rtcon = new ResponseTimeControllerDirect(mgr, this);
      } else if (contype.equals("direct")) {
	this.rtcon = new ResponseTimeControllerDirect(mgr, this);
      } else if (contype.equals("mm1")) {
	this.rtcon = new ResponseTimeControllerMM1(mgr, this);
      } else if (contype.equals("pid")) {
	this.rtcon = new ResponseTimeControllerPID(mgr, this);
      } else if (contype.equals("multiclass")) {
	this.rtcon = new ResponseTimeControllerMulticlass(mgr, this);
      } else {
	throw new RuntimeException("StageWrapper <"+name+">: Bad response time controller type "+contype);
      }
    }
  }

  /**
   * Initialize this stage.
   */
  public void init() throws Exception {
    handler.init(config);
    threadmgr.register(this);
  }

  /**
   * Destroy this stage.
   */
  public void destroy() throws Exception {
    threadmgr.deregister(this);
    handler.destroy();
  }

  /**
   * Return the event handler associated with this stage.
   */
  public EventHandlerIF getEventHandler() {
    return handler;
  }

  /**
   * Return the stage handle for this stage.
   */
  public StageIF getStage() {
    return stage;
  }

  /**
   * Return the set of sources from which events should be pulled to 
   * pass to this EventHandlerIF.
   */
  public SourceIF getSource() {
    return (SourceIF)eventQ;
  }

  /**
   * Return the thread manager which will run this stage.
   */
  public ThreadManagerIF getThreadManager() {
    return threadmgr;
  }

  /**
   * Return execution statistics for this stage.
   */
  public StageStatsIF getStats() {
    return stats;
  }

  /**
   * Return the response time controller, if any.
   */
  public ResponseTimeControllerIF getResponseTimeController() {
    return rtcon;
  }

  public String toString() {
    return "SW["+stage.getName()+"]";
  }

}

