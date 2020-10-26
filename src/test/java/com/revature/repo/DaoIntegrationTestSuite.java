package com.revature.repo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ReimbursementDaoTest.class, UserDaoTest.class })
public class DaoIntegrationTestSuite {

}
