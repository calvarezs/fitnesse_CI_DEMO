// Copyright (C) 2003-2009 by Object Mentor, Inc. All rights reserved.
// Released under the terms of the CPL Common Public License version 1.0.
package fitnesse.fixtures;

import static fitnesse.fixtures.FitnesseFixtureContext.baseDir;
import static fitnesse.fixtures.FitnesseFixtureContext.context;
import static fitnesse.fixtures.FitnesseFixtureContext.fitnesse;
import static fitnesse.fixtures.FitnesseFixtureContext.root;

import java.io.File;

import util.FileUtil;
import fit.Fixture;
import fitnesse.FitNesse;
import fitnesse.components.SaveRecorder;
import fitnesse.responders.ResponderFactory;
import fitnesse.responders.WikiImportTestEventListener;
import fitnesse.testutil.FitNesseUtil;
import fitnesse.wiki.InMemoryPage;

public class SetUp extends Fixture {
  public SetUp() throws Exception {
    //TODO - MdM - There's got to be a better way.
    WikiImportTestEventListener.register();

    root = InMemoryPage.makeRoot("RooT");
    context = FitNesseUtil.makeTestContext(root);
    context.port = 9123;
    context.responderFactory = new ResponderFactory(baseDir);
    fitnesse = new FitNesse(context, false);
    File historyDirectory = context.getTestHistoryDirectory();
    if (historyDirectory.exists())
      FileUtil.deleteFileSystemDirectory(historyDirectory);
    historyDirectory.mkdirs();
    SaveRecorder.clear();
    fitnesse.start();
  }
}
