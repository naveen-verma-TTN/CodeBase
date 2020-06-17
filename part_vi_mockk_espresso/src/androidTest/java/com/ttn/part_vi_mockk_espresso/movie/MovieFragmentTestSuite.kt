package com.ttn.part_vi_mockk_espresso.movie

import org.junit.runner.RunWith
import org.junit.runners.Suite


/*
 * Created by Naveen Verma on 17/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

@RunWith(Suite::class)
@Suite.SuiteClasses(
        DirectorsFragmentTest::class,
        StarActorsFragmentTest::class,
        MovieListFragmentTest::class,
        MovieDetailFragmentTest::class
)
class MovieFragmentTestSuite