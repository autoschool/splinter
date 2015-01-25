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
import ru.yandex.autoschool.splinter.steps.NewPostSteps;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class CreatePostTest {

  private WebDriver driver;
  private NewPostSteps newpost;

  private static final String Title = "My First Post";
  private static final String Body = "Hi!";

  @Before
  public void createDriver() {
    driver = new PhantomJSDriver();
    newpost = new NewPostSteps(driver);
  }

  @Test
  public void userIsRegister() {
    newpost.register(Title, Body);
  }

  @After
  public void quitNewUser() {
    newpost.quit();
  }
}
