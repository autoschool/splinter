/*
 * Copyright 2015 Ksenia Nenasheva <ks.nenasheva@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.yandex.autoschool.splinter.cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import ru.yandex.autoschool.splinter.steps.LoginSteps;
import ru.yandex.autoschool.splinter.steps.RegisterUserSteps;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class CreateUserTest {

  private WebDriver driver;
  private RegisterUserSteps newuser;
  private LoginSteps user;

  private static final String Login = "puss in boots";
  private static final String Pass = "123";
  private static final String Name = "Puss";
  private static final String Sirname = "in Boots";
  private static final String Email = "puss@in.boots";

  @Before
  public void createDriver() {
    driver = new PhantomJSDriver();
    newuser = new RegisterUserSteps(driver);
  }

  @Test
  public void userIsRegister() {
    newuser.register(Login, Email, Name, Sirname, Pass);
  }

  @Test
  public void checkThisUser() {
    user = new LoginSteps(driver);
    user.login(Login, Pass);
  }

  @After
  public void quitNewUser() {
    newuser.quit();
  }
}
