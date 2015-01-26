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
package ru.yandex.autoschool.splinter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.autoschool.splinter.steps.Navigation;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class TemplatePage {

  public WebDriver driver;
  public String baseUrl = "http://localhost:8080";

  @FindBy(id = "navbar-main")
  private Navigation navigation;

  public Navigation getNavigationBar() {
    return this.navigation;
  }

  public TemplatePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new HtmlElementDecorator(driver), this);
  }
}
