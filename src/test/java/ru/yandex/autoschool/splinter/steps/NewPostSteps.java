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
import ru.yandex.autoschool.splinter.pages.NewPostPage;
import ru.yandex.autoschool.splinter.pages.RegisterUserPage;
import ru.yandex.qatools.allure.annotations.Step;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class NewPostSteps {

  private static WebDriver driver;
  private NewPostPage newPostPage;

  protected static String baseUrl = "http://localhost:8080/";

  public NewPostSteps(WebDriver driver) {
    NewPostSteps.driver = driver;
    newPostPage = new NewPostPage(driver);
  }

  @Step("Create new post")
  public void register(String title, String body) {
    newPostPage.open();
    newPostPage.createPost(title, body);
  }

  public void quit() {
    driver.quit();
  }

}
