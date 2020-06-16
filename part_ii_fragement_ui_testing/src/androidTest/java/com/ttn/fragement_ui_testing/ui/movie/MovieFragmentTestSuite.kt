package com.ttn.fragement_ui_testing.ui.movie

import org.junit.runner.RunWith
import org.junit.runners.Suite


/*
 * Created by Naveen Verma on 16/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MovieDetailFragmentTest::class,
        DirectorsFragmentTest::class,
        StarActorsFragmentTest::class,
        MovieNavigationTest::class
)
class MovieFragmentTestSuite