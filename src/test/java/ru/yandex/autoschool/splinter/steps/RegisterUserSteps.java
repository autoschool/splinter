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
package ru.yandex.autoschool.splinter.steps;

import org.openqa.selenium.WebDriver;
import ru.yandex.autoschool.splinter.pages.RegisterUserPage;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class RegisterUserSteps {

  private static WebDriver driver;
  private RegisterUserPage registerPage;

  protected static String baseUrl = "http://localhost:8080/";

  public RegisterUserSteps(WebDriver driver) {
    RegisterUserSteps.driver = driver;
    registerPage = new RegisterUserPage(driver);
  }

  @Step("Registration in system")
  public void register(String login, String email, String name, String sirname, String password) {
    registerPage.open();
    registerPage.register(login, email, name, sirname, password);
  }

  public void quit() {
    driver.quit();
  }

}
