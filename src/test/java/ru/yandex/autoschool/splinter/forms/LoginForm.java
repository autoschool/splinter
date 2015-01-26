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
package ru.yandex.autoschool.splinter.forms;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

/**
 *
 * @author Ksenia Nenasheva <ks.nenasheva@gmail.com>
 */
public class LoginForm extends HtmlElement {

  @FindBy(id = "login")
  private TextInput login;

  @FindBy(id = "pass")
  private TextInput password;

  @FindBy(id = "singIn")
  private Button signInBtn;

  public TextInput getLogin() {
    return this.login;
  }

  public TextInput getPassword() {
    return this.password;
  }

  public Button getSignInBtn() {
    return this.signInBtn;
  }

  public void login(String login, String password) {
    getLogin().sendKeys(login);
    getPassword().sendKeys(password);
    getSignInBtn().click();
  }

}
