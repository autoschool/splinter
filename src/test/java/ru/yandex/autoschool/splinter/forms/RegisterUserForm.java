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
public class RegisterUserForm extends HtmlElement{

  @FindBy(id = "login")
  private TextInput login;

  @FindBy(id = "email")
  private TextInput email;

  @FindBy(id = "name")
  private TextInput name;

  @FindBy(id = "sirname")
  private TextInput sirname;

  @FindBy(id = "password")
  private TextInput password;

  @FindBy(id = "register")
  private Button submitBtn;

  public TextInput getLogin() {
    return this.login;
  }

  public TextInput getEmail() {
    return this.email;
  }

  public TextInput getUserName() {
    return this.name;
  }

  public TextInput getSirname() {
    return this.sirname;
  }

  public TextInput getPassword() {
    return this.password;
  }

  public Button getSubmitBtn() {
    return this.submitBtn;
  }

  public void register(String login, String email, String name, String sirname, String password) {
    getLogin().sendKeys(login);
    getEmail().sendKeys(email);
    getUserName().sendKeys(name);
    getSirname().sendKeys(sirname);
    getPassword().sendKeys(password);
    getSubmitBtn().click();
  }
}
