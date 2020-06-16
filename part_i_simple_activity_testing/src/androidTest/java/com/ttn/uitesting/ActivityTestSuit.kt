package com.ttn.uitesting

import org.junit.runner.RunWith
import org.junit.runners.Suite


/*
 * Created by Naveen Verma on 15/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainActivityTest::class,
        SecondaryActivityTest::class
)
class ActivityTestSuit